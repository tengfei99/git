class TestCharset {

	public static void main(String[] args) {
		new TestCharset().execute();
	}

	private void execute() {
		String s = "Hello!你好！";

		byte[] bytesISO8859 = null;
		byte[] bytesGBK = null;

		try {
			bytesISO8859 = s.getBytes("iso-8859-1");
			bytesGBK = s.getBytes("GBK");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("--------------\n 8859 bytes:");
		System.out.println("bytes is:" + arrayToString(bytesISO8859));
		System.out.println("hex format is:" + encodeHex(bytesISO8859));
		System.out.println();

		System.out.println("--------------\n GBK bytes:");
		System.out.println("bytes is: " + arrayToString(bytesGBK));
		System.out.println("hex format is:" + encodeHex(bytesGBK));

	}

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buff = new StringBuffer(bytes.length * 2);
		String b;
		for (int i = 0; i < bytes.length; i++) {
			b = Integer.toHexString(bytes[i]);
			// byte是两个字节的，而上面的Integer.toHexString会把字节扩展为4个字节
			buff.append(b.length() > 2 ? b.substring(6, 8) : b);
			buff.append(" ");
		}
		return buff.toString();
	}

	public static final String arrayToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buff.append(bytes[i] + " ");
		}
		return buff.toString();
	}

}