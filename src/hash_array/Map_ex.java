package hash_array;

import java.util.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;



/**
 * ��ʹ�õ�Map��������3�֣�<br>
 * <li>keyֵ�ļ��ϣ�����keySet()�����õ�����keyֵ��Set���ϡ�
 * <li>valueֵ�ļ��ϣ�����KeySet()�����õ�����valueֵ��Collection���ϡ�
 * <li>key-value��ӳ�䣺����put(key,value)��get(key)������<p>
 * map.values()�����õ���valueֵ���ϣ�HashMap���ܱ�֤�����˳�򣬶�TreeMap�����
 * ��֤valueֵ������Ԫ�ص�˳���ղ�������ʱ��˳������<p>
 * ����ʹ�õ�Map�ľ�������hashMap,LinkedHashMap��TreeMap��
 * 
 * @author ������
 *
 */
public class Map_ex {
	
	/**
	 * HashMap �ǻ���Hash table�Ľӿڣ������ܱ�֤������Ԫ�ص�˳�򣬵�ʹ��HashMapִ��һЩ��������ʱ��
	 * �����ĵ�ʱ���Ǻ��ȶ��ġ�
	 * 
	 * LinkedHashMap��HashMap�����࣬��������֪�ĵ���˳����ά����һ��˫��������Ҫע����ǣ���һ���ظ�
	 * ��Ԫ�ر�����ʱ��������Ӱ��ԭ�������˳��ʵ��������ظ���keyҲ���ᱻ���롣�����mapʱ��ʹ��value����
	 * �����ֵ�ᰴ�ղ����˳���������LinkedHashMap���ղ����˳���������
	 * 
	 * TreeMap ʵ����Map�ӿ���SortedMap�ӿڣ�Ĭ�ϵ����򷽷��ǲ���keyֵ�������ֵ�������
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
		
		System.out.println("������ͨ��value������һ���valueֵ��ע�������˳��.");
		
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
