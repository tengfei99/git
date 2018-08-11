package xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DOM4JXMLReader {

	public static void main(String[] args) {
		long lasting = System.currentTimeMillis();
		int j = 0;
		String[][] xmlArray = new String[10][10];

		try {
			File f = new File("data.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;

			for (Iterator i = root.elementIterator("VALUE"); i.hasNext();) {

				foo = (Element) i.next();

				xmlArray[j][0] = foo.elementText("NO");
				xmlArray[j][1] = foo.elementText("ADDR");

				System.out.print("���ƺ���:" + foo.elementText("NO"));
				System.out.println(" ������ַ:" + foo.elementText("ADDR"));

				j = j + 1;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		System.out.println("Read xml file cost:��"
				+ (System.currentTimeMillis() - lasting) + " milliseconds");

	}
}
