package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Aashutosh Mallik
 *
 */
public class SelectionSort extends SorterWithStatistics {
	
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
	
		int lengthCheck = 0;
		// the as long as j is smaller then the length of the array.
		while (lengthCheck < words.length) {

		int largestIndex = 0;
		//loop through the words.
		for(int i = 1; i < words.length-lengthCheck; ++i) {
			if (comp.compare(words[largestIndex], words[i]) < 0) {
				largestIndex = i;
			}
		}
		// create a tempString to swap the elements. 
		String tempString = words[words.length-lengthCheck-1];
		words[words.length-lengthCheck-1] = words[largestIndex];
		words[largestIndex] = tempString;
		++lengthCheck;
		}
	}
}
