package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Aashutosh Mallik
 *
 */
public class MergeSort extends SorterWithStatistics {

	//helper calls the sort method.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		
		sort(words, 0, words.length-1, comp);
		
	}
	
	/**
	 * Finds midpoint and splits arrays in half and then recursively calls itself
	 * @param words
	 * @param l
	 * @param r
	 * @param comp
	 * @return
	 */
	private String[] sort(String[] words, int l, int r, Comparator<String> comp) {
		if (l < r) {
			// Find the middle point and uses that to call recursion sort.
			int middle = l + (r-l) /2;
			
			//divide and conquor method.

			sort(words, l, middle, comp);
			sort(words, middle+1, r, comp);
			
			merge(words, l, middle, r, comp);
			
		}
		
		return words;
	}
	
	/**
	 * Takes two sorted arrays and merges them based on the character's value based the alphabet files
	 * @param words
	 * @param l
	 * @param m
	 * @param r
	 * @param comp
	 */
	private void merge(String[] words, int l, int m, int r, Comparator<String> comp) {

		//Find size of the arrays, to compare. 
		int lArray = m-l+1;
		int rArray = r-m;
		
		//Create two arrays to hold temp data,
		String[] lTemp = new String[lArray]; 
		String[] rTemp = new String[rArray];
		
		//Copy data in temp arrays
        for (int i=0; i<lArray; ++i) 
            lTemp[i] = words[l + i]; 
        for (int j=0; j<rArray; ++j) 
            rTemp[j] = words[m + 1+ j]; 
		
        int i = 0;
        int j = 0;
        
        int k = l;
        while (i < lArray && j < rArray) {
			
        	if(comp.compare(lTemp[i], rTemp[j]) >= 0) {
        		words[k] = rTemp[j];
        		++j;
        		++k;
        	}
        	else {
        		words[k] = lTemp[i];
        		++i;
        		++k;
        	}
        }
        
        //Copy all remaining elements back in to an array.
        while(i < lArray) {
        	words[k] = lTemp[i];
        	++k;
        	++i;
        }
        while(j < rArray) {
        	words[k] = rTemp[j];
        	++k;
        	++j;
        }
	}

}
