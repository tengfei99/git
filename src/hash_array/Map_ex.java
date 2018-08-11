package hash_array;

import java.util.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;



/**
 * 常使用的Map方法以下3种：<br>
 * <li>key值的集合：调用keySet()方法得到所有key值的Set集合。
 * <li>value值的集合：调用KeySet()方法得到所有value值的Collection集合。
 * <li>key-value的映射：调用put(key,value)或get(key)方法。<p>
 * map.values()方法得到的value值集合，HashMap不能保证对象的顺序，而TreeMap则可以
 * 保证value值集合中元素的顺序按照插入数据时的顺序排序。<p>
 * 经常使用的Map的具体类是hashMap,LinkedHashMap和TreeMap。
 * 
 * @author 李世存
 *
 */
public class Map_ex {
	
	/**
	 * HashMap 是基于Hash table的接口，它不能保证集合中元素的顺序，当使用HashMap执行一些基本操作时，
	 * 所消耗的时间是很稳定的。
	 * 
	 * LinkedHashMap是HashMap的子类，它具有已知的迭代顺序，它维护了一个双向链表。需要注意的是，当一个重复
	 * 的元素被插入时，并不会影响原来链表的顺序，实际上这个重复的key也不会被插入。用这个map时，使用value集合
	 * 输出的值会按照插入的顺序进行排序。LinkedHashMap按照插入的顺序进行排序。
	 * 
	 * TreeMap 实现了Map接口与SortedMap接口，默认的排序方法是采用key值的升序字典来排序。
	 */
	void hashmap()
	{
		Map map = new HashMap();
		//Map map = new LinkedHashMap();
		//Map map = new TreeMap();
		
		map.put("stu1", "tom");
		map.put("ttu2", "spark");
		map.put("stu3", "tomclus");
		map.put("stu3", "tomclusss5");
		
		System.out.println(map.get("stu1"));
		System.out.println(map.get("ttu2"));
		System.out.println(map.get("stu3"));
		
		System.out.println("下面是通过value集合逐一输出value值，注意排序的顺序.");
		
		Collection collection = map.values();
		Iterator iterator = collection.iterator();
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map_ex hm = new Map_ex();
		
		hm.hashmap();

	}

}
