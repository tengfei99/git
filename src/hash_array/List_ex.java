package hash_array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class List_ex {

	/**
	 * List ������Collection�ӿڵ��ӽӿڣ�����һ������ļ��ϣ������ڶ��С��û�����ͨ��
	 * List������ȡ��ĳһ�����󡣺�Set��һͬ���ǣ�List���������ظ���Ԫ�ش��ڡ�
	 * 
	 * ���õ�ʵ��List�Ľӿ�����ArrayList��LinkedList��
	 * 
	 * ArrayListʹ��һά������ʵ��List�ӿڣ����Զ��ڿ��ٵ����ȡ�ö�����˵��ʹ��ArrayList���Եõ��Ϻõ����ܣ�
	 * ���Ƴ���������ʱ��ArrayList�ͱȽ��������Զ��ڲ��᳣��ɾ���Ͳ���Ĳ�������ʹ��ArrayList.
	 * 
	 * LinkedList�����ھ�����������ɾ����������Ĳ���ʹ��LinkedList���ýϺõ����ܡ�
	 * LinkedList�ṩ��ɾ���Ͳ�������Щ�ض��ķ������磻addFirst(),addLast(),getFirst(),getLast()
	 * removeFirst(),removeLast().<br>
	 * addFirst()���б���ѹ�����,addLast()���ơ� getFirst()��ȡ�ñ��׵Ķ���getLast()���ơ�
	 * removeFirst()�������׵Ķ��󣬼�ɾ����removeLast()���ơ�
	 * 
	 */

	void List_example() {
		// List<String> list = new ArrayList<String>(); //����洢�Ķ���ΪString��J2SE
		// 5.0֮��ʹ�õķ��͹��ܡ�
		List<String> list = new LinkedList<String>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		


		System.out.println("==== ע���ӡ��˳��ͼ���Setʱ��˳�� === ");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		/**
		 * J2SE 5.0��ʹ�õ���ǿ��forѭ��ֱ�ӱ���List����Ԫ�ء� for(String s : list) {
		 * System.out.println(s); }
		 * 
		 */
	}

	public static void main(String[] args) {

		List_ex le = new List_ex();

		le.List_example();

	}

}
