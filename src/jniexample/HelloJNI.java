package jniexample;

public class HelloJNI {
	public native void displayHello();

	public native void showTime();

	private native String getLine(String prompt);

	static {
		System.loadLibrary("hello");
	}

	public static void main(String[] args) throws Exception {
		HelloJNI hj = new HelloJNI();

		System.out.println("==> Demo 1: hello");
		hj.displayHello();
		System.out.println("==> Demo 2: time");
		hj.showTime();

		System.out.println("==> Demo 3: input");
		String input = hj.getLine("Type a line: ");
		System.out.println("User typed: " + input);
	}
}
