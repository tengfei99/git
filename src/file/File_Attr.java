package file;

import java.io.File;

public class File_Attr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File file1 = new File("newFile1.jsp");

		System.out.println("目录newFile1.jsp的绝对路径是：" + file1.getAbsolutePath());
		
		System.out.println("目录newFile1.jsp是否可读：" + file1.canRead());

		System.out.println("目录newFile1.jsp是否可写： " + file1.canWrite());

		System.out.println("目录newFile1.jsp是否存在：" + file1.exists());

		System.out.println("目录newFile1.jsp的父目录是：" + file1.getParent());

		System.out.println("目录newFile1.jsp的文件长度是：" + file1.length());

		System.out.println("目录newFile1.jsp是否是目录：" + file1.isDirectory());

		System.out.println("目录newFile1.jsp是否是文件：" + file1.isFile());

		System.out.println("目录newFile1.jsp是否是隐藏文件：" + file1.isHidden());

		System.out.println("目录newFile1.jsp最后一次修改的时间是：" + file1.lastModified());

	}

}
