package edu.iastate.cs228.hw07;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A class of bags whose entries are stored in a chain of linked nodes.
 * The bag is never full (theoretically).
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Aashutosh Mallik
 * 
 * 
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above empty author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 			If you are introducing your own helper methods those need to be
 *    private and properly documented as per Javadoc style. 
 * 2. No additional data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 *    (Except for the methods that are provided already for you, which
 *    do not need to be implemented as part of this HW, i.e. needs to be
 *    used as it is.)
 * 6. You are allowed to reuse any part of the provided source codes
 *    or shown under lecture notes section of Canvas, which do not 
 *    violate any of above.
 * 7. If you have any additional questions PLEASE ask on Piazza Q/A
 *    platform, but first PLEASE search and make sure that it was not
 *    already asked and answered. PLEASE setup your notifications for 
 *    both Canvas and Piazza so that you are updated whenever there
 *    are any changes immediately.
 * 
 */ 

public final class LinkedBag<T> implements BagInterface<T>,Iterable<T>
{
	private Node firstNode;       
	private int numberOfEntries;

	public LinkedBag()
	{
		firstNode = null;
      numberOfEntries = 0;
	} // end default constructor

	/** Sees whether this bag is empty.
	    @return  True if this bag is empty, or false if not. */
	public boolean isEmpty() 
	{
		return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the number of entries currently in this bag.
	    @return  The integer number of entries currently in this bag. */
	public int getCurrentSize() 
	{
		return numberOfEntries;
	} // end getCurrentSize

	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry)  	      // OutOfMemoryError possible
	{
		if(Objects.isNull(newEntry)) return false;
		
      // Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
                                // (firstNode is null if chain is empty)        
        firstNode = newNode;      // New node is at beginning of chain
		numberOfEntries++;
      
		return true;
	} // end add

	/** Retrieves all entries that are in this bag.
	    @return  A newly allocated array of all the entries in this bag. */
	public Object[] toArray()
	{
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.data;
         index++;
         currentNode = currentNode.next;
      } // end while
      	
		return result;
	} // end toArray

	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in this bag. */
	public int getFrequencyOf(T anEntry) 
	{
		if(Objects.isNull(anEntry)) return 0;

		
		int frequency = 0;

      int counter = 0;
      Node currentNode = firstNode;
      while ((counter < numberOfEntries) && (currentNode != null))
      {
         if (anEntry.equals(currentNode.data))
         {
            frequency++;
         } // end if
         
         counter++;
         currentNode = currentNode.next;
      } // end while

		return frequency;
	} // end getFrequencyOf

	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false otherwise. */
	public boolean contains(T anEntry)
	{
		if(Objects.isNull(anEntry)) return false;

      boolean found = false;
      Node currentNode = firstNode;
      
      while (!found && (currentNode != null))
      {
         if (anEntry.equals(currentNode.data))
            found = true;
         else
            currentNode = currentNode.next;
      } // end while	
      
      return found;
   } // end contains
   
 	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while
     
		return currentNode;
	} // end getReferenceTo

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
		T result = null;
      if (firstNode != null)
      {
         result = firstNode.data; 
         firstNode = firstNode.next; // Remove first node from chain
         numberOfEntries--;
      } // end if

		return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag, if possible.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false otherwise. */
   public boolean remove(T anEntry) 
	{
  		if(Objects.isNull(anEntry)) return false;

   	
		boolean result = false;
      Node nodeN = getReferenceTo(anEntry);
      
      if (nodeN != null)
      {
         nodeN.data = firstNode.data; // Replace located entry with entry in first node
         
         firstNode = firstNode.next;  // Remove first node
         numberOfEntries--;
         
         result = true;
      } // end if
         
		return result;
	} // end remove

	private class Node 
	{
	  private T    data; // Entry in bag
	  private Node next; // Link to next node

		private Node(T dataPortion)
		{
			this(dataPortion, null);
		} // end constructor
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor
	} // end Node
	
	public Iterator<T> iterator() 
	{
		return new IteratorForLinkedBag();
	}
	
	private class IteratorForLinkedBag implements Iterator<T>
	{
		/**
 	 * Additional data fields cannot be introduced.
 	 */
		private Node nextNode;
		
		public IteratorForLinkedBag()
		{
			nextNode = firstNode;
		}
		
		@Override
		public boolean hasNext()
		{
			// TODO
			if (nextNode == null) {
				return false;
			} else  {
				return true;
			}

		}

		@Override
		public T next()
		{
			// TODO
			if (nextNode == null) {
				throw new NoSuchElementException();
			} else {
				T element = nextNode.data;
				nextNode = nextNode.next;
				return element;
			}
		}
		
		/**
		 * You are not allowed to throw UnsupportedOperationException.
		 */
		@Override
		public void remove()
		{
			// TODO
			if (!LinkedBag.this.remove(nextNode.data)) {
				throw new IllegalStateException();
			}
			
		}
	}	
} // end LinkedBag



