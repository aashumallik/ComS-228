package edu.iastate.cs228.hw07;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ListIterator;

/**
 * 
 * A class that implements a bag of objects by using an array.
 * The bag is limited by MAX_CAPACITY.
 * 
 * @author Frank M. Carrano
 * @author Aashutosh Mallik
 * 
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above empty author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 			If you are introducing your own helper methods those need to be
 *    private and properly documented as per Javadoc style. 
 * 2. No additional data fields can be introduced except where it is
 *    explicitly mentioned that it is allowed.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 *    (Except for the methods that are provided already for you, which
 *    do not need to be implemented as part of this HW, i.e. needs to be
 *    used as it is.)
 * 6. You are allowed to reuse any part of the provided source codes
 *    or shown under lecture notes section of Canvas, which do not 
 *    violate any of above.
 * 7. If you have any additional questions please ask on Piazza Q/A
 *    platform, but first PLEASE search and make sure that it was not
 *    already asked and answered. PLEASE setup your notifications for 
 *    both Canvas and Piazza so that you are updated whenever there
 *    are any changes immediately.
 * 
 */ 


public final class ResizableArrayBag<T> implements BagInterface<T>,Iterable<T>
{
	private T[] bag; 
	private int numberOfEntries;
 private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25; // Initial capacity of bag
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25. */
	public ResizableArrayBag() 
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/** Creates an empty bag having a given initial capacity.
	    @param initialCapacity  The integer capacity desired. */
	public ResizableArrayBag(int initialCapacity)
	{
  initialize(initialCapacity);    
	} // end constructor

	private void initialize(int n)
	{
		checkCapacity(n);
  
  // The cast is safe because the new array contains null entries
  @SuppressWarnings("unchecked")
  T[] tempBag = (T[])new Object[n]; // Unchecked cast
  bag = tempBag;
  numberOfEntries = 0;
  initialized = true;
	}
	
	/** Creates a bag containing given entries.
	    @param contents  An array of objects. */
   public ResizableArrayBag(T[] contents) 
   {
   	if(!Objects.isNull(contents))
   	{
   		numberOfEntries = 0;
   		for(int i=0; i<contents.length; i++)
   		{
   			if(!Objects.isNull(contents[i])) numberOfEntries+=1;
   		}
   		checkCapacity(numberOfEntries);
   		
   		if(numberOfEntries!=0)
   		{
   		 @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[numberOfEntries];
      bag = tempBag;
      int inc = 0;
      for(int i=0; i<contents.length; i++)
   		 {
   		 	if(!Objects.isNull(contents[i])) 
   		 	{ 
   		 		bag[inc]=contents[i];
   		 		inc++;
   		 	}
   		 }
    		initialized = true;
   		}
   		else
   		{
   			initialize(DEFAULT_CAPACITY);
   		}
   	}
   	else
   	{
   		initialize(DEFAULT_CAPACITY);
   	}    
   } // end constructor
       
	/** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True. */
	public boolean add(T newEntry)
	{
		if(Objects.isNull(newEntry)) return false;
		
		checkInitialization();
      if (isArrayFull())
      {
         doubleCapacity();
      } // end if
      
      bag[numberOfEntries] = newEntry;
      numberOfEntries++;
      
      return true;
	} // end add

	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
	public Object[] toArray() 
	{
		checkInitialization();
      
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = bag[index];
      } // end for
      
      return result;
	} // end toArray
   
	/** Sees whether this bag is empty.
       @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
      return numberOfEntries == 0;
	} // end isEmpty
   
	/** Gets the current number of entries in this bag.
       @return  The integer number of entries currently in this bag. */
	public int getCurrentSize()
	{
      return numberOfEntries;
	} // end getCurrentSize
   
	/** Counts the number of times a given entry appears in this bag.
       @param anEntry  The entry to be counted.
       @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(T anEntry)
	{
		if(Objects.isNull(anEntry)) return 0;
		
		checkInitialization();
      int counter = 0;
      
      for (int index = 0; index < numberOfEntries; index++)
      {
         if (anEntry.equals(bag[index]))
         {
            counter++;
         } // end if
      } // end for
      
      return counter;
	} // end getFrequencyOf
   
	/** Tests whether this bag contains a given entry.
       @param anEntry  The entry to locate.
       @return  True if this bag contains anEntry, or false otherwise. */
   public boolean contains(T anEntry)
	{
   	if(Objects.isNull(anEntry)) return false;
   	
		checkInitialization();
      return getIndexOf(anEntry) > -1; // or >= 0
	} // end contains
   
	/** Removes all entries from this bag. */
	public void clear()
	{
      while (!isEmpty())
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
       was successful, or null. */
	public T remove()
	{
		checkInitialization();
      T result = removeEntry(numberOfEntries - 1);
      return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		if(Objects.isNull(anEntry)) return false;
		
		checkInitialization();
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);
      return anEntry.equals(result);
	} // end remove
   
 	// Locates a given entry within the array bag.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
   // Precondition: checkInitialization has been called.
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf
   
   // Removes and returns the entry at a given index within the array.
   // If no such entry exists, returns null.
   // Precondition: 0 <= givenIndex < numberOfEntries.
   // Precondition: checkInitialization has been called.
	private T removeEntry(int givenIndex)
	{
		T result = null;
      
		if (!isEmpty() && (givenIndex >= 0))
		{
         result = bag[givenIndex];          // Entry to remove
         int lastIndex = numberOfEntries - 1;
         bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
         bag[lastIndex] = null;             // Remove reference to last entry
         numberOfEntries--;
		} // end if
      
      return result;
	} // end removeEntry
   
   // Returns true if the array bag is full, or false if not.
	private boolean isArrayFull()
	{
		return numberOfEntries >= bag.length;
	} // end isArrayFull
   
   // Doubles the size of the array bag.
   // Precondition: checkInitialization has been called.
	private void doubleCapacity()
	{
      int newLength = 2 * bag.length;
      checkCapacity(newLength);
      bag = java.util.Arrays.copyOf(bag, newLength);
	} // end doubleCapacity
   
   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity
   
   // Throws an exception if receiving object is not initialized.
   private void checkInitialization()
   {
      if (!initialized)
         throw new SecurityException ("Uninitialized object used " +
                                      "to call an ArrayBag method.");
   } // end checkInitialization
   
   public ListIterator<T> listIterator()
   {
   	return new ListIteratorForResizableArrayBag();
   }
   
   public ListIterator<T> listIterator(int i)
   {
   	// TODO
    return new ListIteratorForResizableArrayBag(i);
   }
   
   public Iterator<T> iterator()
   {
   	return listIterator();
   }
   
   private class ListIteratorForResizableArrayBag implements ListIterator<T>
   {
   	/**
   	 * Data fields: You are allowed to introduce data fields,
   	 * however, restrict those only to primitive types. 
   	 * Usage of enum is not allowed.  
   	 * 
   	 */
	int index;
	boolean canRemove;
   	public ListIteratorForResizableArrayBag()
   	{
   	 //TODO 
	 	index = 0;
		canRemove = false;	
    }
   	
   	public ListIteratorForResizableArrayBag(int i)
   	{
   	 //TODO
		index = i;
    }
   	
				@Override
				public boolean hasNext()
				{
					// TODO
					canRemove = true;
					if (bag.length > index) {
						return true;
					}
					return false;
				}

				@Override
				public T next()
				{
					// TODO
					if (bag.length > index) {
						T elem = bag[index];
						index +=1;
						return elem;
					}
					throw new NoSuchElementException();
				}

				@Override
				public boolean hasPrevious()
				{
					// TODO
					return  index > 0;
				}

				@Override
				public T previous()
				{
					// TODO
					if (index > 0) {
						T elem = bag[index - 1];
						index -=1;
						return elem;
					}

					throw new NoSuchElementException();
					
				}

				@Override
				public int nextIndex()
				{
					// TODO
					return index;
				}

				@Override
				public int previousIndex()
				{
					// TODO
					return index - 1;
				}

				/**
				 * You are not allowed to throw UnsupportedOperationException.
				 */
				@Override
				public void remove()
				{
					// TODO
					if (canRemove) {
						ResizableArrayBag.this.remove(previous());
					}
					throw new IllegalStateException();
				}

				/**
				 * You are not allowed to throw UnsupportedOperationException.
				 * 
				 */
				@Override
				public void set(T e)
				{
					// TODO
					
					if (!canRemove) {
						bag[previousIndex()] = e;
					}
				}

				/**
				 * You are not allowed to throw UnsupportedOperationException.
				 * 
				 */
				@Override
				public void add(T e)
				{
					
					// TODO
					Class h = e.getClass();
					Class cls = bag.getClass().getComponentType();
					if (h.getName() != cls.getName()) {
						throw new ClassCastException();
					}
					if (index == 0) {
						bag[0] = e;
					}
					T nextElem = bag[index];
					bag[index] = e;
					bag[index+1] = nextElem;
					index+=1;

					return;
				}
   }
} // end ResizableArrayBag
