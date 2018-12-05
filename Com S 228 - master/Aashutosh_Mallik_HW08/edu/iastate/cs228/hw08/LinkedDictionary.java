
package edu.iastate.cs228.hw08;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 
 * 
 * A class that implements the ADT dictionary by using a chain of nodes.
 * The dictionary is unsorted and has distinct search keys, i.e., can have 
 * duplicate values, however, those are differentiated based on their keys.
 * 
 * @author Aashutosh Mallik
 * 
 * NOTEs and REQUIREMENTs:
 * 
 * Exactly same as the ones listed for SortedVectorDictionary class.
 * 
 * In addition to above ANSWER the following 6 QUESTIONS, inside these 
 * comments right below each question. Figures needed to answer questions
 * 3, 4, and 5 are shown on Canvas under description of HW08.
 * 
 * =========================================================================
 * Q1. (a) What is the height of the shortest binary tree that contains 22
 *     nodes? (b) Is this tree full? (c) Is it balanced?
 *     
 * A1. (a) 4
 *     (b) No
 *     (c) No
 * =========================================================================
 * Q2. Consider a binary tree that has four levels.
 *     (a) What is the maximum number of nodes in this tree?
 *     (b) What is the maximum number of leaves in this tree?
 *     
 * A2. (a) 31
 *     (b) 16   
 * =========================================================================
 * Q3. Consider a traversal of a binary tree, which contains Integer data. 
 *     Suppose that visiting a node means to simply display the data in the 
 *     node. What are the results of each of the following traversals of the 
 *     binary tree shown in Figure 1.
 *     (a) Preorder
 *     (b) Postorder
 *     (c) Inorder
 *     (d) Level order
 *     
 * A3. (a) 6-4-2-1-3-5-8-7-9-10-11
 *     (b) 1-3-2-5-4-9-7-11-10-8-6
 *     (c) 1-2-3-4-5-6-9-7-8-11-10
 *     (d) 6-4-8-2-5-7-10-1-3-9-11
 *     
 * =========================================================================
 * Q4. Repeat Q3 but for the binary tree shwn in Figure 2. 
 * A4. (a)11-8-3-2-1-5-4-6-10-9-7
 *     (b)2-1-3-4-6-5-8-9-7-10-11
 *     (c)2-3-1-8-4-5-11-6-9-7-10
 *     (d)11-8-10-3-5-9-7-2-1-4-6
 *  
 * =========================================================================
 * Q5. The two binary trees shown in Figures 1 and 2 contain Integer data.
 *     (a) Is the tree in Figure 1 a binary search tree? Why or why not?
 *     (b) Is the tree in Figure 2 a maxheap? Why or why not?
 *  
 * A5. (a)It is not because node 9 lies in the left sub-tree of the parent node 8 which means that the
 *			data of parent node is not greater than all the data present in its lefft sub tree
 *     (b)It is not a Max Heap because node 6 is a child node of node 5 which means the data of
 *			parent node is less than all its child node
 *     
 * =========================================================================
 * Q6. Can a binary search tree ever be a maxheap? Explain.
 * A6. A binary search tree having only 2 nodes, with a child node, to the left of the root node,
 *		is always a max heap property                         
 *     
 *     
 *     
 *     
 *     
 *     
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
 private Node firstNode;   // Reference to first node of chain
 private int  numberOfEntries; 
	
 public LinkedDictionary()
 {
  firstNode = null;
  numberOfEntries = 0;
 }
	
 public V add(K key, V value)
 {
  if(Objects.isNull(key) || Objects.isNull(value))
	throw new IllegalArgumentException();
  
  Node temp = new Node(key, value);
  
  if(!contains(key))
  {
	  	numberOfEntries++;
	    
	    if(firstNode==null)
	    {
	  	  firstNode = temp;
	    }
	    
	    else
	    {
	  	  Node ptr = firstNode;
	  	  while(ptr.getNextNode()!=null)
	  	  {
	  		  ptr = ptr.getNextNode();
	  	  }
	  	  
	  	  ptr.setNextNode(temp);
	    }
  }
  else
  {
	  Node ptr = firstNode;
	  while(!ptr.getKey().equals(key))
	  {
		  ptr=ptr.next;
	  }
	  
	  ptr.setValue(value);
  }
  
  return value;
 }

 public V remove(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
  
  Node prev=null,curr=null;
  
  curr=firstNode;
  
  System.out.println("neeed to remove: " + key);
  
  while(curr!=null)
  {
	  if(curr.getKey().equals(key))
	  {
		  break;
	  }
	  
	  prev=curr;
	  curr=curr.next;
  }
  
  V val;
  
  if(curr==null)
	  throw new NoSuchElementException();
  
  if(prev==null)
  {
	  val = firstNode.getValue();
	  firstNode=firstNode.next;
  }
  else
  {
	  val = curr.getValue();
	  prev.next=curr.next;
  }
  return val;  
 }

 public V getValue(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
	   
  Node ptr = firstNode;
  while(ptr!=null && ptr.getKey()!=key)
  {
	  ptr = ptr.getNextNode();
  }
  
  if(ptr==null)
     throw new NoSuchElementException();
  
  return ptr.getValue();
 }

 public boolean contains(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();

  Node ptr = firstNode;
  while(ptr!=null && ptr.getKey()!=key)
  {
	  ptr = ptr.getNextNode();
  }

  if(ptr==null)
    return false; 
  
  return true;
 }

 public boolean isEmpty()
 {
  if(firstNode==null)
	  return true;
  
  return false;
 }
	
 public int getSize()
 {
  return numberOfEntries;
 }

 public void clear()
 {
	 firstNode = null;
	 numberOfEntries = 0;
 }

 // Needs to output String representation in exact same
 // format as the one done by SortedVectorDictionary.
 public String toString()
 {
	 String s = "[";
	 Node ptr = firstNode;
	 
	 while(ptr!=null)
	 {
		 s = s + ptr.toString();
		 if(ptr.next!=null)
		 {
			 s = s + ", ";
		 }
		 else
		 {
			 s = s + "]";
		 }
		 ptr = ptr.next;
	 }
  
  return s;
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
  private Node nextNode;
		
  private KeyIterator()
  {
	  Node nd=null,ptr=null;
	  Node itr = firstNode;
	  
	  while(itr!=null)
	  {		  
		  Node temp = new Node(itr.getKey(),itr.getValue());
		  
		  if(nd==null)
		  {
			  nd = temp;
			  ptr = nd;
		  }
		  else
		  {
			  ptr.next  = temp;
			  ptr = ptr.next;
		  }
		  itr = itr.next;
	  }
	  
	  
	  nextNode = nd;
  }
		
  public boolean hasNext() 
  {
   if(nextNode!=null)
	   return true;
   
   return false;
  }
		
  public K next()
  {
	  if(nextNode==null)
		  return null;

	  K k = nextNode.getKey();
	  nextNode = nextNode.next;
	  
	  return k;
  }
 } 
	
 private class ValueIterator implements Iterator<V>
 {
  private Node nextNode;
  		
  private ValueIterator()
  {
	  Node nd=null,ptr=null;
	  Node itr = firstNode;
	  
	  while(itr!=null)
	  {		  
		  Node temp = new Node(itr.getKey(),itr.getValue());
		  
		  if(nd==null)
		  {
			  nd = temp;
			  ptr = nd;
		  }
		  else
		  {
			  ptr.next  = temp;
			  ptr = ptr.next;
		  }
		  itr = itr.next;
	  }
	  
	  
	  nextNode = nd;
  }
		
  public boolean hasNext() 
  {
   if(nextNode!=null)
     return true;

   return false;
  }
		
  public V next()
  {
	if(nextNode==null)
      return null;
	
	  V v = nextNode.getValue();
	  nextNode = nextNode.next;

   return v;
  }
 }

 private class Node
 {
  private K key;
  private V value;
  private Node next;

  private Node(K searchKey, V dataValue)
  {
   key = searchKey;
   value = dataValue;
   next = null;	
  }
		
  private Node(K searchKey, V dataValue, Node nextNode)
  {
   key = searchKey;
   value = dataValue;
   next = nextNode;	
  }
		
  private K getKey()
  {
   return key;
  }
		
  private V getValue()
  {
   return value;
  }

  private void setValue(V newValue)
  {
   value = newValue;
  }

  private Node getNextNode()
  {
   return next;
  }
		
  private void setNextNode(Node nextNode)
  {
   next = nextNode;
  }
  
  public String toString()
  {
   return "("+key+":"+value+")";	
  }
 }
}
		
