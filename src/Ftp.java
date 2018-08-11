import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import sqlserver.DesEncrypt;
import sqlserver.Sqlserver;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class Ftp {
	static {
		
		System.out.print("sdfsdf");

}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String server = "";
		String user = "";
		String password = "";
		String path = "";
		String filename = "";
		

		
		try {
			FtpClient ftpClient = new FtpClient();
			ftpClient.openServer(server);
			ftpClient.login(user, password);
			if (path.length() != 0)
				ftpClient.cd(path);
			ftpClient.binary();
			TelnetInputStream is = ftpClient.get(filename);
			File file_out = new File(filename);
			FileOutputStream os = new FileOutputStream(file_out);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			is.close();
			os.close();
			ftpClient.closeServer();
		} catch (IOException ex) {
			;
		}

	}

}
