package edu.iastate.cs228.hw08;


import java.util.Vector;
import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;

/**
 * A class that implements a sorted dictionary by using a Vector.
 * Entries are sorted based on keys in nondecreasing order. 
 * The dictionary has distinct search keys, i.e., can have duplicate
 * values, however, those are differentiated based on their keys.
 * 
 * @author Aashutosh Mallik
 * 
 * 
 * 
 * NOTEs and REQUIREMENTs:
 *  
 * 0. Put your Firstname and Lastname after above empty author tag.
 * 	  Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 	  If you are introducing your own helper methods those need to be 
 *    private and properly documented as per Javadoc style. Already 
 *    existing methods declaration cannot be changed, too.     
 * 2. No additional data fields can be introduced in any of the 
 *    classes below. You are not allowed to change the case of the ones
 *    already existing, or rename those.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed, besides the ones that are
 *    already provided.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the provided source codes
 *    or shown under lecture notes section of Canvas, which do not 
 *    violate any of above.
 * 7. If you have any additional questions PLEASE ask on Piazza Q/A
 *    platform, but first PLEASE search and make sure that it was not
 *    already asked and answered. PLEASE setup your notifications for 
 *    both Canvas and Piazza so that you are updated whenever there
 *    are any changes immediately.
 * 8. You need to provide implementation to all methods and constructors
 *    which have a comment //TODO in their body. For all of these methods
 *    and constructors there is no need to provide comments.    
 * 
 *    
 *    
 * 
 */ 

public class SortedVectorDictionary<K extends Comparable<? super K>, V> 
       implements DictionaryInterface<K, V>
{
 private Vector<Entry> dict; 	

 public SortedVectorDictionary()
 {
  dict = new Vector<>();
 }

 public SortedVectorDictionary(int initialCapacity)
 {
  dict = new Vector<>(initialCapacity);
 }

 public V add(K key, V value)
 {
  if(Objects.isNull(key) || Objects.isNull(value))
	throw new IllegalArgumentException();
  
  Entry obj = new Entry(key, value);
  
  if(dict.size()==0)
  {
	  dict.insertElementAt(obj, 0);
	  return value;
  }
  
  Iterator<Entry> it = dict.iterator();
  Entry ent = null;
  int idx = 0;
  
  while(it.hasNext())
  {
	  ent = it.next();
	  if(ent.getKey().compareTo(key)>=0)
	  {
		  break;
	  }
	  idx++;
  }
  
  if(ent.getKey()==key)
  {
	  ent.setValue(value);
  }
  else
  {
	  dict.insertElementAt(obj, idx);
  }
  
  return value;
 }

 public V remove(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
  
  Iterator<Entry> it = dict.iterator();
  Entry ent = null;
  int idx = 0;
  while(it.hasNext())
  {
	  ent = it.next();
	  if(ent.getKey()==key)
	  {
		  break;
	  }
	  idx++;
  }
  
  if(!it.hasNext())
	  throw new NoSuchElementException();

  V val = ent.getValue();
  dict.remove(idx);
  
  return val;  
 }

 public V getValue(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
	   
  Iterator<Entry> it = dict.iterator();
  Entry ent = null;
  int idx=0;
  
  while(it.hasNext())
  {
	  ent = it.next();
	  if(ent.getKey().equals(key))
	  {
		  break;
	  }
	  idx++;
  }
  
  if(idx==dict.size())
	  throw new NoSuchElementException();
  
  
  return ent.getValue();
 }

 public boolean contains(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
		   
  Iterator<Entry> it = dict.iterator();
  Entry ent = null;
  
  while(it.hasNext())
  {
	  ent = it.next();
	  
	  if(ent.getKey().equals(key))
		  return true;
  }
	   
  return false; 
 }

 public boolean isEmpty()
 {
  if(dict.size()==0)
	  return true;
  
  return false;
 }
	
 public int getSize()
 {
  return dict.size();
 }

 public void clear()
 {
	 dict.clear();
 }

 public String toString()
 {
  return dict.toString();  
 }
 
 public Iterator<K> getKeyIterator()
 {
  return new KeyIterator();
 }
	
 public Iterator<V> getValueIterator()
 {
  return new ValueIterator();
 }

 private class KeyIterator implements Iterator<K>
 {
  private Iterator<K> iter;
		
  private KeyIterator()
  {
	  Vector<K> vec = new Vector<K>();
	  
	  Iterator<Entry> itr = dict.iterator();
	  
	  while(itr.hasNext())
	  {
		  Entry ent = itr.next();
		  
		  vec.add(ent.getKey());
	  }
	  
	  iter = vec.iterator();
  }
		
  public boolean hasNext() 
  {
   return iter.hasNext();
  }
		
  public K next()
  {
   return iter.next();	
  }
 } 
	
 private class ValueIterator implements Iterator<V>
 {
  private Iterator<V> iter;
		
  private ValueIterator()
  {
	  Vector<V> vec = new Vector<V>();
	  
	  Iterator<Entry> itr = dict.iterator();
	  
	  while(itr.hasNext())
	  {
		  Entry ent = itr.next();
		  
		  vec.add(ent.getValue());
	  }
	  
	  iter = vec.iterator();

  }
		
  public boolean hasNext() 
  {	 
   return iter.hasNext();
  }
		
  public V next()
  {
   return iter.next();
  }
 }
	
 private class Entry
 {
  private K key;
  private V value;
		
  private Entry(K searchKey, V dataValue)
  {
   key = searchKey;
   value = dataValue;
  }
  
  private K getKey()
  {
   return key;
  }
		
  private V getValue()
  {
   return value;
  }
		
  private void setValue(V dataValue)
  {
   value = dataValue;
  }
  
  public String toString()
  {
   return "("+key+":"+value+")";	
  }
 }
}
		
