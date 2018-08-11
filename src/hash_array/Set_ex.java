package hash_array;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Set_ex {

	/**
	 * Set接口继承自Collection接口，Set容器中的对象都是惟一的，加入Set容器中的对象都必须重新定义equals()与hashCode()方法，
	 * 以作为惟一性的识别。
	 * 常用的Set的具体类是HashSet,LinkedHashSet和TreeSet.
	 * 
	 * HashSet 类现实了Set接口，它不能保证按照对象加入的顺序在集合中排序，也不能按照HashCode排序，它允许空元素存在。
	 * 
	 * LinkedHashSet是HashSet的子类，依照对象插入的顺序对输出进行排序。允许空对象且不插入重复的对象。
	 * 
	 * TreeSet不允许空元素的存在，按key进行升序排序，不插入重复的对象。
	 * 
	 * 以上这三个set都不允许插入重复的对象。
	 */
	void Set_example()
	{
		//Set set  = new HashSet();
		Set set  = new LinkedHashSet();
		//Set set  = new TreeSet();
		
		set.add("b");
		set.add("a");
		set.add("c");
		set.add(null);
		set.add("a");
		
		Iterator iterator = set.iterator();
		
		System.out.println("==== 注意打印的顺序和加入Set时的顺序 === ");
		while(iterator.hasNext())
		{
			String tem =(String)iterator.next();
			
			if(tem != null)
			{
				System.out.println(tem + tem.hashCode() + "");
			}
			else
			{
				System.out.println("null ");
			}
		}
	}
	public static void main(String[] args) {

		Set_ex  se = new Set_ex();
		
		se.Set_example();

	}

}
