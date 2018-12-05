package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Aashutosh Mallik
 *
 */
public class QuickSort extends SorterWithStatistics {

	///this helper method will call quick sort based upon the arguements.

	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		quickSort(words, 0, words.length-1, comp);
		
	}
	
	
	/**
     * This is swapping the elements, by creating a temp array and putting the value to arr[i]
	 * then making arr[i] = arr[j]
	 * and finally arr[j] becomes temp;
     * 
     * @param array
     * @param i
     * @param j
     */
    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Takes the String array and passes it through after testing to see if it is a valid list
     * @param list
     * @param comp
     */
    private void quickSort(String[] list, Comparator<String> comp)
	{
		if (list == null || list.length == 0)
			throw new RuntimeException("Null pointer or zero size");
		if (list.length == 1)
			return;


		quickSort(list, 0, list.length - 1, comp);
	}

    /**
     * Recursively calls itself, and finds a pivot index. Then splits the array in two.
     * @param list
     * @param first
     * @param last
     * @param comp
     */
	private void quickSort(String[] list, int first, int last, Comparator<String> comp)
	{
		if (last > first)
		{
			int pivot = partition(list, first, last, comp);

			
			quickSort(list, first, pivot - 1, comp);
			quickSort(list, pivot + 1, last, comp);
		}
	}

	
      /**
       * Partition the array list[first..last]
       * @param list
       * @param first
       * @param last
       * @param comp
       * @return
       */
	private int partition(String[] list, int first, int last, Comparator<String> comp)
	{
		/** 
		 * The first element is chosed as pivot, then the beginning is the first + 1 and the end is the last element.
		 * 
		 * **/
		String pivot = list[first];
		int begin = first + 1; 
		int end = last; 
		
		// while the end is bigger then the begin element.
		// loop through and begin the searches.
		while (end > begin)
		{
			// as long as the begin element is less than the end and comparsion is less than or equal to 0, add one to begin.
			while (begin <= end && comp.compare(list[begin], pivot) <= 0) 
			{
				begin++;
			}
			// as long as the begin element is less than the end and comparsion is greater than  0, take one from end.
			while (begin <= end && comp.compare(list[end], pivot) > 0){
				end--;
			} 

			// Swap if end is greater than begin.
			if (end > begin)
			{
				swap(list, end, begin);
			}
		}

		while (end > first && comp.compare(list[end], pivot) >= 0) end--;

		// At this point we need to swap the pivot with the end element.
		if (comp.compare(pivot, list[end]) > 0)
		{
			list[first] = list[end];
			list[end] = pivot;			
			return end;
		} 
		else
		{
			return first;
		}
	} // end partition
}
