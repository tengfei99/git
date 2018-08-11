import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * �����Ǽ��ܴ�����.��Ҫ���������ݽ��м���,���ܲ���.
 */
public class DESEncryptUtil {

	String FilePath = null;

	/**
	 * ��üӽ��ܵ���Կ
	 * 
	 * @return Key ���ضԳ���Կ
	 * @throws java.security.NoSuchAlgorithmException
	 * @see util.EncryptUtil ���а������ܺͽ��ܵķ���
	 */
	public static Key getKey(String strKey) throws NoSuchAlgorithmException {
		// String strKey = "cunqing168";// ��Կ
		Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom(strKey.getBytes()));
		Key key = generator.generateKey();
		return key;
	}

	/**
	 * ��ָ�������ݸ����ṩ����Կ���м���
	 * 
	 * @param key
	 *            ��Կ
	 * @param data
	 *            ��Ҫ���ܵ�����
	 * @return byte[] ���ܺ������
	 * @throws util.Exception
	 */
	public static byte[] doEncrypt(Key key, byte[] data) throws Exception {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

			// Encrypt
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(data);
			// BASE64Encoder encoder = new BASE64Encoder();
			// String base64 = encoder.encode(raw);
			return raw;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("�����ݼ����뷢������:" + e.getMessage());
		}
	}

	/**
	 * ���������Ѽ��ܵ�����ͨ��ָ������Կ���н���
	 * 
	 * @param key
	 *            ��Կ
	 * @param raw
	 *            �����ܵ�����
	 * @return byte[] ���ܺ������
	 * @throws util.Exception
	 */
	public static byte[] doDecrypt(Key key, byte[] raw) throws Exception {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// Decrypt
			cipher.init(Cipher.DECRYPT_MODE, key);
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] raw = decoder.decodeBuffer(data);
			byte[] data = cipher.doFinal(raw);
			// String result = new String(stringBytes, "UTF8");
			// System.out.println("the decrypted data is: " + result);
			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("���ܷ�������:[" + e.getMessage() + "]");
		}
	}

	/**
	 * �õ�һ����Կ������
	 * 
	 * @param key
	 *            ��Կ
	 * @param cipherMode
	 *            ���������
	 * @return Cipher
	 * @throws util.Exception
	 *             �����ܳ����쳣���ʱ,�����쳣��Ϣ
	 */
	public static Cipher getCipher(Key key, int cipherMode) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(cipherMode, key);
			return cipher;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("������Կ���������:" + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 3) {
			System.out
					.println("====================================================");
			System.out.println("�÷���java DesFile <enc|des> <key> <filepath>");
			System.out.println("");
			System.out.println("˵����enc ����ܣ�des�����.");
			System.out.println("");
			System.out.println("      key Ϊ�ӽ��ܵ���Կ.");
			System.out.println("");
			System.out.println("      filepath ��Ҫ���ܻ���ܵ��ļ�·��.");
			System.out
					.println("====================================================");
			return;
		}

		File file = null;
		FileInputStream in = null;

		try {
			file = new File(args[2]);
			in = new FileInputStream(file);
		} catch (FileNotFoundException ffe) {
			System.out
					.println("====================================================");
			System.out.println("��ָ�����ļ������ڣ����������룡");
			System.out
					.println("====================================================");
			return;
		}

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] tmpbuf = new byte[1024];
		int count = 0;
		while ((count = in.read(tmpbuf)) != -1) {
			bout.write(tmpbuf, 0, count);
			tmpbuf = new byte[1024];
		}
		in.close();
		byte[] orgData = bout.toByteArray();

		Key key = DESEncryptUtil.getKey(args[1]);// ������Կ

		if ("enc".equals(args[0])) {// �����ļ�
			byte[] raw = DESEncryptUtil.doEncrypt(key, orgData);
			file = new File(args[2]);
			OutputStream out = new FileOutputStream(file);
			out.write(raw);
			out.flush();
			out.close();
			System.out
					.println("====================================================");
			System.out.println("��Ϣ:���ļ�" + args[2] + "�������!");
			System.out
					.println("====================================================");
		} else if ("des".equals(args[0])) {// �����ļ�
			byte[] data = DESEncryptUtil.doDecrypt(key, orgData);
			file = new File(args[2]);
			OutputStream out = new FileOutputStream(file);
			out.write(data);
			out.flush();
			out.close();
			System.out
					.println("====================================================");
			System.out.println("��Ϣ:���ļ�" + args[2] + "�������!");
			System.out
					.println("====================================================");
		} else { // ��������,�������ʾ��Ϣ
			System.out
					.println("====================================================");
			System.out.println("������ĵ�һ����������ȷ����һ������ֻ��Ϊenc��des��");
			System.out.println("");
			System.out.println("enc����ܣ�des����ܣ����������룡");
			System.out
					.println("====================================================");
			return;
		}

	}
}
