package hash_array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class List_ex {

	/**
	 * List 按口是Collection接口的子接口，它是一个有序的集合，类似于队列。用户可以通过
	 * List的索引取得某一个对象。和Set不一同的是，List集合允许重复的元素存在。
	 * 
	 * 常用的实现List的接口类有ArrayList和LinkedList。
	 * 
	 * ArrayList使用一维数组来实现List接口，所以对于快速的随机取得对象来说，使用ArrayList可以得到较好的性能，
	 * 在移除或插入对象时，ArrayList就比较慢，所以对于不会常用删除和插入的操作建议使用ArrayList.
	 * 
	 * LinkedList，对于经常从容器中删除或插入对象的操作使用LinkedList会获得较好的性能。
	 * LinkedList提供了删除和插入对象后些特定的方法，如；addFirst(),addLast(),getFirst(),getLast()
	 * removeFirst(),removeLast().<br>
	 * addFirst()表有表首压入对象,addLast()类似。 getFirst()表取得表首的对象，getLast()类似。
	 * removeFirst()表弹出表首的对象，即删除。removeLast()类似。
	 * 
	 */

	void List_example() {
		// List<String> list = new ArrayList<String>(); //宣告存储的对象为String，J2SE
		// 5.0之后使用的泛型功能。
		List<String> list = new LinkedList<String>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		


		System.out.println("==== 注意打印的顺序和加入Set时的顺序 === ");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		/**
		 * J2SE 5.0后使用的增强的for循环直接遍历List所有元素。 for(String s : list) {
		 * System.out.println(s); }
		 * 
		 */
	}

	public static void main(String[] args) {

		List_ex le = new List_ex();

		le.List_example();

	}

}
