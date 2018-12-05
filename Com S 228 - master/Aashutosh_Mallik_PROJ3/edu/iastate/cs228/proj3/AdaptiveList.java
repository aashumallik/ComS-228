package edu.iastate.cs228.proj3;

/*
 *  @author Aashutosh Mallik
 *
 *
 *  An implementation of List<E> based on a doubly-linked list 
 *  with an array for indexed reads/writes
 *
 */

import java.util.*;

public class AdaptiveList<E> implements List<E>
{
  public class ListNode 
  {                     
    public E data;        
    public ListNode next; 
    public ListNode prev; 
    
    public ListNode(E item)
    {
      data = item;
      next = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c)
  {
    // TODO
    clear(); //reset field values
    addAll(c); //add collection items to linked list
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node 
  // from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.next = toRemove.next;
    toRemove.next.prev = toRemove.prev;
    arrayUTD = false;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to chain after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.next = current.next;
    toAdd.next.prev = toAdd;
    toAdd.prev = current;
    current.next = toAdd;
    arrayUTD = false;
  }

  private void updateArray() // makes theArray up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");
    // TODO

    theArray = (E[])new Object[numItems]; //initialize array

    //Add items in linked list into array
    ListNode node = head.next;
    for(int i = 0; i < numItems; i++)
    {
      theArray[i] = node.data;
      node = node.next;
    }

    arrayUTD = true;
  }

  private void updateLinked() // makes the linked list up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");

    // TODO
    //Reset linked list
    int numArrayItems = numItems;
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    tail.prev = head;
    numItems = 0;

    //Add array items into linked list
    for(int i = 0; i < numArrayItems; i++)
    {
      add(theArray[i]);
    }
    linkedUTD = true;
  }

  @Override
  public int size()
  {
    // TODO
    return numItems;
  }

  @Override
  public boolean isEmpty()
  {
    // TODO
    return (numItems < 1);
  }

  @Override
  public boolean add(E obj)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Add new item to the linked list
    ListNode node = new ListNode(obj);
    link(tail.prev, node);
    numItems++;

    arrayUTD = false; //set the array as not updated.

    return true; 
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Add items in collection into the linked list
    for(E item : c)
    {
      add(item);
    }

    return true; 
  }

  @Override
  public boolean remove(Object obj)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //return if linked list is empty because there is nothing to remove.
    if(isEmpty()) return false;

    //Search and remove item from linked list.
    ListNode node = head.next;
    while(node.next != null)
    {
      if(node.data.equals(obj))
      {
        unlink(node);
      }

      node = node.next;
    }

    return true;
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.next;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj)
  {
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Find the node at position pos
    ListNode node = findNode(pos);
    if(node == null) return;

    //Add new node relative to the node at position pos.
    ListNode newNode = new ListNode(obj);
    link(node.prev, newNode);

    arrayUTD = false; //Set array as not updated.
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    // TODO
    //Add collection items to linked list relative to node in position pos.
    for(E item : c)
    {
      add(pos, item);
      pos++;
    }

    return true; 
  }

  @Override
  public E remove(int pos)
  {
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    // TODO
    ListNode node = findNode(pos); //Find node at position pos
    unlink(node); //remove the node from linked list
    arrayUTD = false; //set array as not updated.

    return node.data;
  }

  @Override
  public E get(int pos)
  {
    // TODO
    //if array is not updated, update the array.
    if(!arrayUTD) updateArray();

    return theArray[pos];
  }

  @Override
  public E set(int pos, E obj)
  {
    // TODO
    //if array is not updated, update the array.
    if(!arrayUTD) updateArray();

    E prevElement = theArray[pos]; // get current item at position pos
    theArray[pos] = obj; //Set new item to position pos
    linkedUTD = false; //Set the linked list as not updated.

    return prevElement;
  } 

  /**
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  reverses the order of the elements in the 
   *  array without using any additional array, 
   *  and returns true. Note that if the array 
   *  is modified, then linkedUTD needs to be set 
   *  to false.
   */
  public boolean reverse()
  {
    // TODO
    //if array is not updated, update the array.
    if(!arrayUTD) updateArray();

    //If number of items in array is less than 2, it is not enough to perform reverse.
    if(numItems <= 1) return false;

    int mid = (numItems/2); //get position of middle number in array.

    //Reverse list by in-place swapping of items position.
    for(int i = 0, j = numItems - 1; i < mid; i++, j--)
    {
      E tmp = theArray[i];
      theArray[i] = theArray[j];
      theArray[j] = tmp;
    }
    linkedUTD = false; //Set linked list as not updated

    return true;
  }

  
  /** 
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  swaps the items positioned at even index 
   *  with the subsequent one in odd index without 
   *  using any additional array, and returns true.
   *  Note that if the array is modified, then 
   *  linkedUTD needs to be set to false. 
   */
  public boolean reorderOddEven()
  {
    // TODO
    //if array is not updated, update the array.
    if(!arrayUTD) updateArray();

    //If number of items in array is less than 2, it is not enough to perform reorder.
    if(numItems <= 1) return false;

    //Reorder list by in-place swapping of items position.
    for(int i = 0; i < numItems; i = i + 2)
    {
      int j = i + 1;
      E tmp = theArray[i];
      theArray[i] = theArray[j];
      theArray[j] = tmp;
    }
    linkedUTD = false; //Set linked list as not updated

    return true;
  }
  
  @Override
  public boolean contains(Object obj)
  {
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    int index = indexOf(obj); //get index if item.

   return (index > -1);
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    boolean result = true;

    //Check if index exist for every item in the collection.
    for(Object item : c)
    {
      if(!contains(item))
      {
        result = false;
        break;
      }
    }

    return result;
  }


  @Override
  public int indexOf(Object obj)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    if(isEmpty()) return -1;

    int i = 0;
    int index = -1;

    //Search for item and return index.
    ListNode node = head.next;
    while(node.next != null)
    {
      if(node.data.equals(obj))
      {
        index = i;
        break;
      }

      i++;
      node = node.next;
    }

    return index;
  }

  @Override
  public int lastIndexOf(Object obj)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    if(isEmpty()) return -1;

    int i = 0;
    int index = -1;

    //Search for item and return last index.
    ListNode node = head.next;
    while(node.next != null)
    {
      if(node.data.equals(obj))
      {
        index = i;
      }

      i++;
      node = node.next;
    }

    return index;
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Remove items in collection from linked list
    for(Object item : c)
    {
      remove(item);
    }

    return true; 
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Remove all linked list items except those in collection c.
    ListNode node = head.next;
    while(node.next != null)
    {
      if(c.contains(node.data)) continue;
      remove(node.data);

      node = node.next;
    }

    return true; 
  }

  @Override
  public Object[] toArray()
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //Create array and return linked list data as an array.
    Object[] result = new Object[numItems];
    ListNode node = head.next;
    int i = 0;
    while(node.next != null)
    {
      result[i] = node.data;

      i++;
      node = node.next;
    }

    return result;
  }
  
  
  /**
   * In here you are allowed to use only 
   * java.util.Arrays.copyOf method.
   */
  @Override
  public <T> T[] toArray(T[] arr)
  {
    // TODO
    //if linked list is not updated, update the linked list.
    if(!linkedUTD) updateLinked();

    //if array arr is not up to size numitems, create new array of size numitems.
    T[] copy = Arrays.copyOf(arr, numItems);

    //Put linked list data into the array.
    ListNode node = head.next;
    int i = 0;
    while(node.next != null)
    {
      copy[i] = (T)node.data;

      i++;
      node = node.next;
    }

    return copy;
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    public AdaptiveListIterator()
    {
      if ( ! linkedUTD ) updateLinked();
      // TODO
      index = (numItems > 1) ? 1 : -1;
      last = cur = (numItems > 1) ? head.next : null;
    }

    public AdaptiveListIterator(int pos)
    {
      if ( ! linkedUTD ) updateLinked();
      // TODO
      index = pos + 1;
      last = cur = findNode(index);
    }

    @Override
    public boolean hasNext()
    {
      // TODO
      return (last.next != null);
    }

    @Override
    public E next()
    {
      // TODO
      last = last.next;
      cur = last.prev;
      index++;

      return last.data;
    } 

    @Override
    public boolean hasPrevious()
    {
      // TODO
      return (last.prev != null);
    }

    @Override
    public E previous()
    {
      // TODO
      last = last.prev;
      cur = last.prev;
      index--;
      return last.data;
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
      return (index - 1);
    }

    @Override
    public void remove()
    {
      // TODO
      ListNode node = findNode(index--);
      unlink(node);
    }

    @Override
    public void add(E obj)
    {
      // TODO
      AdaptiveList.this.add(obj);
    }

    @Override
    public void set(E obj)
    {
      // TODO
      int k = index - 1;
      AdaptiveList.this.remove(k);
      AdaptiveList.this.add(k, obj);
    }
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.next; tmp != tail; tmp = tmp.next )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   // Other options System.lineSeparator or
   // String.format with %n token...
   // Not making data field.
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.next; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.next;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
