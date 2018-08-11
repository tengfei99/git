/*   
 *   ��ǰ�ļ���DesEncrypt.java   
 *   �������ڣ�2005-7-29   
 *   ��   ��   �ţ�1.0   
 *   ���ߣ�������  
 *     
 */

package sqlserver;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * ʹ��DES���������,�ɶ�byte[],String���ͽ��м�������� ���Ŀ�ʹ��String,byte[]�洢.
 * <p>
 * 
 * ����: void getKey(String strKey) ����strKey����������һ����ԿKey.
 * <p>
 * 
 * String getEncString(String strMing)��strMing���м���,����String���� <br>
 * String getDesString(String strMi)��strMin���н���,����String����
 * <p>
 * 
 * byte[] getEncCode(byte[] byteS) byte[]�͵ļ��� <br>
 * byte[] getDesCode(byte[] byteD)byte[]�͵Ľ���
 * <p>
 * 
 */

public class DesEncrypt {
	Key key;

	/**
	 * ���ݲ�������KEY
	 * 
	 * @param strKey
	 */
	public void getKey() {
		try {
			String strKey = "cunqing168";//��Կ
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����String��������,String�������
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF-8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * ���� ��String��������,String�������
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * ������byte[]��������,byte[]�������
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ������byte[]��������,��byte[]�������
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;

	}

	public static void main(String[] args) throws Exception {

		System.out.println("hello");
		DesEncrypt des = new DesEncrypt();// ʵ����һ������
		des.getKey();

		String strEnc = des.getEncString("struts");// �����ַ���,����String������
		System.out.println(strEnc);

		String strDes = des.getDesString(strEnc);// ��String ���͵����Ľ���
		System.out.println(strDes);

	}

}