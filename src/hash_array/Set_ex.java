package hash_array;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Set_ex {

	/**
	 * Set�ӿڼ̳���Collection�ӿڣ�Set�����еĶ�����Ωһ�ģ�����Set�����еĶ��󶼱������¶���equals()��hashCode()������
	 * ����ΪΩһ�Ե�ʶ��
	 * ���õ�Set�ľ�������HashSet,LinkedHashSet��TreeSet.
	 * 
	 * HashSet ����ʵ��Set�ӿڣ������ܱ�֤���ն�������˳���ڼ���������Ҳ���ܰ���HashCode�����������Ԫ�ش��ڡ�
	 * 
	 * LinkedHashSet��HashSet�����࣬���ն�������˳������������������ն����Ҳ������ظ��Ķ���
	 * 
	 * TreeSet�������Ԫ�صĴ��ڣ���key�����������򣬲������ظ��Ķ���
	 * 
	 * ����������set������������ظ��Ķ���
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
		
		System.out.println("==== ע���ӡ��˳��ͼ���Setʱ��˳�� === ");
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
