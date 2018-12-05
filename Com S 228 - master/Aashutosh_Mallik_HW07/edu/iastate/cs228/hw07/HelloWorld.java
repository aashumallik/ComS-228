package edu.iastate.cs228.hw07;

import java.util.ArrayList;
import java.util.Iterator;
/**
   An interface that includes the main method to run the program.
   @author Aashutosh Mallik
*/
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		ResizableArrayBag<Integer> bag = new ResizableArrayBag();
		bag.add(6);
		bag.add(7);
		bag.add(92);
		Iterator<Integer> it = bag.iterator();
		 // it.next();
		 //it.remove();
		
		
//		bag.add(4);
//		Integer[] obj = {};
//		Integer g = 7;
//		String b = "home";
//		Class cls = obj.getClass().getComponentType();
//		Class h = g.getClass();
//		System.out.println(cls.getName() == h.getName());
		for (int i = 0; it.hasNext();) {
			System.out.println(it.next());
		}
//		Integer[][] multi = new Integer[][]{
//			  { 0, 1, 2},
//			  { 3, 4, 5},
//			  { 6, 7, 8},
//			  { 9, 10, 11},
//			};
//		for (int m =0; m < multi.length; m++) {
//			for (int n = 0; n < multi[m].length; n++) {
//				if (n == 2) {
//					break;
//				} else {
//					System.out.println(multi[m][n]);
//				}
//			}
//		}
		
		ArrayList<Integer> a = new ArrayList();
		a.add(8);
		a.add(5);
		a.add(9);
		a.add(17);
		Integer[] b = {5, 8, 10, 13, 15, 20, 22, 26};
		// boolean g = find2D(multi, 8);
		// System.out.println(g);
		// System.out.println(g[1]);
		
	}
	
	public static Integer[] findMinInterval(
			Integer[] sortedArr, java.util.List<Integer> givenValues)
	{
		//TODO
		int minGivenVal = givenValues.get(0);
		int maxGivenVal = givenValues.get(0);
		int minVal = -2;
		int maxVal = -2;
		for (int i =1; i < givenValues.size(); i++) {
			if (givenValues.get(i) > maxGivenVal) {
				maxGivenVal = givenValues.get(i);
			}
			if (givenValues.get(i) <  minGivenVal) {
				 minGivenVal = givenValues.get(i);	
			}
		}
		
		if (minGivenVal <  sortedArr[0]) {
			minVal = -1;
		}
		if (maxGivenVal > sortedArr[sortedArr.length - 1]) {
			maxVal = sortedArr.length;
		}

		if (minVal == -1 && maxVal == sortedArr.length) {
			return new Integer[]{minVal, maxVal};
		}

		for (int j = 0; j < sortedArr.length; j++) {
			if (minVal == -2 && sortedArr[j] > minGivenVal) {
				minVal = j - 1;
			}
			if (maxVal == -2 && sortedArr[j] > maxGivenVal) {
				maxVal = j;
				break;
			}
		}
		 
		return new Integer[]{minVal, maxVal};
	}

	 public static <T extends Comparable<? super T>> boolean find2D(T[][] arr, T key)
	 {
	  //TODO
	  	for (int i = 0; i < arr.length; i++) {
			if (arr[i][0].compareTo(key) == -1) {
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j].compareTo(key) == 1) {
						break;
					}
					System.out.println(arr[i][j]);
					if (arr[i][j].compareTo(key) == 0) {
						return true;
					}
				}
			}
		}
		return false;
	  
	 }

}
