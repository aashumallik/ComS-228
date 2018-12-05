package edu.iastate.cs228.hw08;

import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SortedVectorDictionary<Integer, Integer> sv = new SortedVectorDictionary<Integer,Integer>();
		
		sv.add(5, 10);
		sv.add(10, 20);
		sv.add(1, 30);
		sv.add(100, 50);
		sv.add(5, 45);
		
		//System.out.println(sv.contains(5));//sv.contains(10)
		//System.out.println(sv.getValue(5));
		
		Iterator<Integer> k = sv.getKeyIterator();
		
		while(k.hasNext())
		{
			System.out.println(k.next());
		}

		Iterator<Integer> v = sv.getValueIterator();
		
		while(v.hasNext())
		{
			System.out.println(v.next());
		}

		System.out.println(sv.toString());
		
		
		LinkedDictionary<Integer,Integer> d = new LinkedDictionary<Integer,Integer>();
		
		d.add(1, 13);
		d.add(2, 1);
		
		d.clear();
		
		d.add(1, 13);
		d.add(1, 231);
		d.add(312, 324);
		d.add(2, 3);

		d.remove(312);
		//System.out.println(d.getValue(1));
		//System.out.println(d.getSize());
		
		Iterator<Integer> itr = d.getKeyIterator();
		
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}

		System.out.println(d.toString());
	}

}
