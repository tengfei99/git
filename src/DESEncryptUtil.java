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
 * 该类是加密处理类.主要用来对数据进行加密,解密操作.
 */
public class DESEncryptUtil {

	String FilePath = null;

	/**
	 * 获得加解密的密钥
	 * 
	 * @return Key 返回对称密钥
	 * @throws java.security.NoSuchAlgorithmException
	 * @see util.EncryptUtil 其中包括加密和解密的方法
	 */
	public static Key getKey(String strKey) throws NoSuchAlgorithmException {
		// String strKey = "cunqing168";// 密钥
		Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom(strKey.getBytes()));
		Key key = generator.generateKey();
		return key;
	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            需要加密的数据
	 * @return byte[] 加密后的数据
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
			throw new Exception("将数据加密码发生错误:" + e.getMessage());
		}
	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param key
	 *            密钥
	 * @param raw
	 *            待解密的数据
	 * @return byte[] 解密后的数据
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
			throw new Exception("解密发生错误:[" + e.getMessage() + "]");
		}
	}

	/**
	 * 得到一个密钥的密码
	 * 
	 * @param key
	 *            密钥
	 * @param cipherMode
	 *            密码的类型
	 * @return Cipher
	 * @throws util.Exception
	 *             当加密出现异常情况时,产生异常信息
	 */
	public static Cipher getCipher(Key key, int cipherMode) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(cipherMode, key);
			return cipher;
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("产生密钥的密码错误:" + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 3) {
			System.out
					.println("====================================================");
			System.out.println("用法：java DesFile <enc|des> <key> <filepath>");
			System.out.println("");
			System.out.println("说明：enc 表加密，des表解密.");
			System.out.println("");
			System.out.println("      key 为加解密的密钥.");
			System.out.println("");
			System.out.println("      filepath 表要加密或解密的文件路径.");
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
			System.out.println("您指定的文件不存在，请重新输入！");
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

		Key key = DESEncryptUtil.getKey(args[1]);// 生成密钥

		if ("enc".equals(args[0])) {// 加密文件
			byte[] raw = DESEncryptUtil.doEncrypt(key, orgData);
			file = new File(args[2]);
			OutputStream out = new FileOutputStream(file);
			out.write(raw);
			out.flush();
			out.close();
			System.out
					.println("====================================================");
			System.out.println("信息:将文件" + args[2] + "加密完成!");
			System.out
					.println("====================================================");
		} else if ("des".equals(args[0])) {// 解密文件
			byte[] data = DESEncryptUtil.doDecrypt(key, orgData);
			file = new File(args[2]);
			OutputStream out = new FileOutputStream(file);
			out.write(data);
			out.flush();
			out.close();
			System.out
					.println("====================================================");
			System.out.println("信息:将文件" + args[2] + "解密完成!");
			System.out
					.println("====================================================");
		} else { // 其它输入,则输出提示信息
			System.out
					.println("====================================================");
			System.out.println("您输入的第一个参数不正确，第一个参数只能为enc或des！");
			System.out.println("");
			System.out.println("enc表加密，des表解密，请重新输入！");
			System.out
					.println("====================================================");
			return;
		}

	}
}
