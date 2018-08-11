package LoadLogTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpLoginException;

/**
 * ���ܣ����ط�������ָ�����ļ�,��ѹ��
 * 
 * @author lishicun l00101203
 * 
 */
public class LoadLogTool {
	private ChannelSftp sftp = null;

	/**
	 * ���ܣ���FTP���ط�������־�ļ�
	 * 
	 * @param dm
	 */
	public void getFtpFile(DataModel dm) {
		String[] files = dm.getLogfilename().split(";");

		FtpClient ftpClient = new FtpClient();

		// FTP��������
		try {
			System.out.println("Connected to " + dm.getIP() + ".");
			System.out.println("get file(s) via FTP. Now begin download...");

			ftpClient.openServer(dm.getIP());
			ftpClient.login(dm.getUser(), dm.getPassword());

			if (dm.getUnixpath().length() != 0) {
				ftpClient.cd(dm.getUnixpath());
				System.out.println("Info��Enter directory-->" + ftpClient.pwd());
			}

			// TelnetInputStream���ö�����
			ftpClient.binary();
		} catch (FtpLoginException fle) {
			System.out
					.println("Info��Connect FTP Server failed��program terminate.");
			System.exit(0);
		} catch (FileNotFoundException ffe) {
			System.out.println("Info��No such directory��" + dm.getUnixpath());
			System.exit(0);
		} catch (IOException io) {
			io.printStackTrace();
		}

		for (int i = 0; i < files.length; i++) {
			try {

				File file_out = new File(dm.getLocalpath() + files[i]);
				// ��ɾ�����أ�����һЩ��־�ļ�����û�У������ػ�����
				file_out.delete();

				System.out.println("      Download " + files[i] + " Success!");
				TelnetInputStream is = ftpClient.get(files[i]);

				FileOutputStream os = new FileOutputStream(file_out);

				byte[] bytes = new byte[1024];
				int c;
				while ((c = is.read(bytes)) != -1) {
					os.write(bytes, 0, c);
				}
				os.flush();
				is.close();
				os.close();
			} catch (IOException io) {
				// io.printStackTrace();
			}
		}

		try {
			ftpClient.closeServer();// �ر�FTP����
			System.out.println("Info:Download the Server file(s) Finish!");
			System.out.println("Info:Local directory is -->"
					+ dm.getLocalpath());
		} catch (IOException io) {
			;
		}

		dm = null;
	}

	/**
	 * connect server via sftp
	 * 
	 * @param dm
	 */
	public void connect(DataModel dm) {
		try {
			if (sftp != null) {
				System.out.println("sftp is not null");
			}

			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(dm.getUser(), dm.getIP(), 22);
			// System.out.println("Session created.");
			sshSession.setPassword(dm.getPassword());
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			// System.out.println("Session connected.");
			// System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + dm.getIP() + ".");
			System.out.println("get file(s) via sftp. Now begin download...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Disconnect with server
	 */
	public void disconnect() {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if (this.sftp.isClosed()) {
				System.out.println("sftp is closed already");
			}
		}

	}

	/**
	 * download files via sftp
	 * 
	 * @param directory
	 * @param downloadFile
	 * @param sftp
	 * @param dm
	 */
	private void download(DataModel dm, ChannelSftp sftp) {
		try {
			sftp.cd(dm.getUnixpath());
			System.out.println("Info��Enter directory-->" + sftp.pwd());
		} catch (Exception e) {
			;
		}

		String[] files = dm.getLogfilename().split(";");

		for (int i = 0; i < files.length; i++) {
			File file_out = new File(dm.getLocalpath() + files[i]);
			// ��ɾ�����أ�����һЩ��־�ļ�����û�У������ػ�����
			file_out.delete();
		}

		for (int i = 0; i < files.length; i++) {
			System.out.println("      Download " + files[i] + " Success!");

			File file = new File(dm.getLocalpath() + files[i]);

			try {
				sftp.get(files[i], new FileOutputStream(file));
			} catch (Exception e) {
				;
			}

			System.out.println("Info:Download the Server file(s) Finish!");
			System.out.println("Info:Local directory is -->"
					+ dm.getLocalpath());

			dm = null;
		}
	}

	public static void main(String[] args) {
		LoadLogTool llt = new LoadLogTool();

		DataModel sv = new DataModel();

		try {
			Properties prop = new Properties();
			prop.load(LoadLogTool.class.getResourceAsStream("conf.ini"));

			sv.setIP(prop.getProperty("IP"));
			sv.setUser(prop.getProperty("user"));
			sv.setPassword(prop.getProperty("password"));
			sv.setUnixpath(prop.getProperty("unixpath"));
			sv.setLocalpath(prop.getProperty("localpath"));
			sv.setLogfilename(prop.getProperty("logfilename"));
			sv.setViewtime(prop.getProperty("viewtime"));
			sv.setZipfilename(prop.getProperty("zipfilename"));
			sv.setIscompress(prop.getProperty("iscompress"));
			sv.setIscompress(prop.getProperty("iscompress"));
			sv.setFtpmode(prop.getProperty("ftpmode"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		if ("ftp".equals(sv.getFtpmode())) {
			llt.getFtpFile(sv);
		} else {
			llt.connect(sv);
			llt.download(sv, llt.sftp);
			llt.disconnect();
		}

		// ѹ�����ص���־�ļ�
		if ("0".equals(sv.getIscompress())) {
			ZipTool zt = new ZipTool();

			String[] sourceFileName = sv.getLogfilename().split(";");

			zt.createFilesToZip(sv.getLocalpath(),
					sv.getZipfilename() + ".zip", sourceFileName);
		}

		// ִ��ҳ��ͣ��ʱ���趨
		try {
			int i = Integer.valueOf(sv.getViewtime()).intValue();

			if (i > 0) {
				Thread.sleep(i * 1000);
			}

		} catch (InterruptedException ie) {
			;
		}

		sv = null;

	}
}
