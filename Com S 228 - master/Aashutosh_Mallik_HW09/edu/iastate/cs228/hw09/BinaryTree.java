 package edu.iastate.cs228.hw09;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.LinkedList;

/**
 * 
 * A class that implements the ADT binary tree.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Aashutosh Mallik
 * 
 * 
 * NOTEs and REQUIREMENTs:
 *  
 * 0. Put your Firstname and Lastname after above empty author tag.
 * 	  Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 	  If you are introducing your own helper methods those need to be 
 *    private and properly documented as per Javadoc style, i.e., you
 *    are not allowed to have public helper method. Already existing 
 *    methods declaration cannot be changed.     
 * 2. No additional data fields can be introduced in any of the 
 *    classes below. You are not allowed to change the case of the ones
 *    already existing, or rename those. Except where it's explicitly
 *    mentioned that you are allowed.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed, besides the ones that are
 *    already provided.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the provided source codes
 *    or shown under lecture notes section of Canvas, which do not 
 *    violate any of above.
 * 7. Check carefully the lecture notes of Week 10 named 
 *    "treeImpls_part2.pdf" for hints (and partial solutions) for
 *    this HW's required parts.   
 * 8. If you have any additional questions PLEASE ask on Piazza Q/A
 *    platform, but first PLEASE search and make sure that it was not
 *    already asked and answered. PLEASE setup your notifications for 
 *    both Canvas and Piazza so that you are updated whenever there
 *    are any changes immediately.
 * 9. You need to provide implementation to all methods and constructors
 *    which have a comment //TODO in their body. For all of these methods
 *    and constructors there is no need to provide comments. (Latter
 *    part applies also for all provided classes/interfaces, including
 *    the constructors/methods which you are not required to implement
 *    as part of this HW.)
 * 10.You can assume that data of each node of binary tree will never
 *    be null.
 *    
 * 
 */

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
  private BinaryNode<T> root;

  public BinaryTree()
  {
	root = null;
  } // end default constructor

  public BinaryTree(T rootData)
  {
	root = new BinaryNode<>(rootData);
  } // end constructor

  public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
	privateSetTree(rootData, leftTree, rightTree);
  } // end constructor

  public void setTree(T rootData)
  {
	root = new BinaryNode<>(rootData);
  } // end setTree

  public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
  {
	privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
  } // end setTree

  private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
  {
	root = new BinaryNode<>(rootData);

	if ((leftTree != null) && !leftTree.isEmpty())
	  root.setLeftChild(leftTree.root);

	if ((rightTree != null) && !rightTree.isEmpty())
	{
	  if (rightTree != leftTree)
		root.setRightChild(rightTree.root);
	  else
		root.setRightChild(rightTree.root.copy());
	} // end if

	if ((leftTree != null) && (leftTree != this))
	  leftTree.clear();

	if ((rightTree != null) && (rightTree != this))
	  rightTree.clear();
  } // end privateSetTree

  public T getRootData()
  {
	if (isEmpty())
	  throw new EmptyTreeException();
	else
	  return root.getData();
  } // end getRootData

  public boolean isEmpty()
  {
	return root == null;
  } // end isEmpty

  public void clear()
  {
	root = null;
  } // end clear

  public void setRootData(T rootData)
  {
	root.setData(rootData);
  } // end setRootData

  public void setRootNode(BinaryNode<T> rootNode)
  {
	root = rootNode;
  } // end setRootNode

  public BinaryNode<T> getRootNode()
  {
	return root;
  } // end getRootNode

  public int getHeight()
  {
	return root.getHeight();
  } // end getHeight

  public int getNumberOfNodes()
  {
	return root.getNumberOfNodes();
  } // end getNumberOfNodes

  public Iterator<T> getPreorderIterator()
  {
	return new PreorderIterator();
  } // end getPreorderIterator

  public Iterator<T> getInorderIterator()
  {
	return new InorderIterator();
  } // end getInorderIterator

  public Iterator<T> getPostorderIterator()
  {
	return new PostorderIterator();
  } // end getPostorderIterator

  public Iterator<T> getLevelOrderIterator()
  {
	return new LevelOrderIterator();
  } // end getLevelOrderIterator

  
  private class InorderIterator implements Iterator<T>
  {
	private Stack<BinaryNode<T>> nodeStack;
	private BinaryNode<T> currentNode;

	public InorderIterator()
	{
	  nodeStack = new Stack<>();
	  currentNode = root;
	} // end default constructor

	public boolean hasNext()
	{
	  return !nodeStack.empty() || (currentNode != null);
	} // end hasNext

	public T next()
	{
	  BinaryNode<T> nextNode = null;

	  while (currentNode != null)
	  {
		nodeStack.push(currentNode);
		currentNode = currentNode.getLeftChild();
	  }

	  if (!nodeStack.empty())
	  {
		nextNode = nodeStack.pop();
		assert nextNode != null;
		currentNode = nextNode.getRightChild();
	  } 
	  else throw new NoSuchElementException();

	  return nextNode.getData();
	}	
  } // end InorderIterator

  public void iterativeInorderTraverse()
  {
	Stack<BinaryNode<T>> nodeStack = new Stack<>();
	BinaryNode<T> currentNode = root;

	while (!nodeStack.isEmpty() || (currentNode != null))
	{
	  while (currentNode != null)
	  {
		nodeStack.push(currentNode);
		currentNode = currentNode.getLeftChild();
	  }

	  if (!nodeStack.isEmpty())
	  {
		BinaryNode<T> nextNode = nodeStack.pop();
		assert nextNode != null;
		System.out.print(nextNode.getData() + " ");
		currentNode = nextNode.getRightChild();
	  }
	}
  } // end iterativeInorderTraverse
  
  
  /**
   * Outputs exact same info as iterativeInorderTraverse()
   * method but for preorder traversal. You need to use Stack 
   * to implement this method iteratively.  
   */
  public void iterativePreorderTraverse()
  {
	//TODO
	Stack<BinaryNode<T>> nodeStack = new Stack<>();
	nodeStack.push(root);
	
	while( !nodeStack.isEmpty()) {
		
		BinaryNode<T> nextNode = nodeStack.pop();
		assert nextNode != null;
		System.out.print(nextNode.getData() + " ");
		
		if (nextNode.hasRightChild()) {
			nodeStack.push(nextNode.getRightChild());
		}
		if (nextNode.hasLeftChild()) {
			nodeStack.push(nextNode.getLeftChild());
		}
	}
  }
  /**
   * Outputs exact same info as iterativeInorderTraverse()
   * method but for postorder traversal. You need to use 
   * Stack to implement this method iteratively. 
   */
  public void iterativePostorderTraverse()
  {
	//TODO
	  Stack<BinaryNode<T>> stack = new Stack<>();
	  stack.push(root);
	  
	  while(!stack.isEmpty()) {
		  BinaryNode<T> nextNode = stack.peek();
		  assert nextNode != null;
		  if(!nextNode.hasLeftChild() && !nextNode.hasRightChild()) {
			  BinaryNode<T> popedNode = stack.pop();
			  System.out.println(popedNode.getData() + " ");
		  }
		  else {
			  if(nextNode.hasRightChild()) {
				  stack.push(nextNode.getRightChild());
				  nextNode.setRightChild(null);
			  }
			  if(nextNode.hasLeftChild()) {
	                stack.push(nextNode.getLeftChild());
	                nextNode.setLeftChild(null);
	          }
		  }
	  }
  }
  /**
   * Outputs exact same info as iterativeInorderTraverse()
   * method but for levelorder traversal. You need to use 
   * Queue to implement this method iteratively. Simulate 
   * Queue using LinkedList.
   *  
   */
  public void iterativeLevelorderTraverse()
  {
	//TODO
	LinkedList<BinaryNode<T>> nodeQueue = new LinkedList<>();
	nodeQueue.add(root);
	
	while(!nodeQueue.isEmpty()) {
		BinaryNode<T> nextNode = nodeQueue.poll();
		System.out.println(nextNode.getData() + " ");
		
		// enqueue left child
		if (nextNode.hasLeftChild()) {
			nodeQueue.add(nextNode.getLeftChild());
		}
		if (nextNode.hasRightChild()) {
			nodeQueue.add(nextNode.getRightChild());
		}
	}
  }
  
  private class PreorderIterator implements Iterator<T>
  {
	//You are allowed to create no more than
	//2 data fields in this class.
	private final Stack<BinaryNode<T>> nodeStack;
	
	public PreorderIterator()
	{
		//TODO
		nodeStack = new Stack<>();
		nodeStack.add(root);
	}

	public boolean hasNext()
	{
	  //TODO
	  return !nodeStack.isEmpty();
	}

	public T next()
	{
		//TODO
		if (!hasNext()) throw new EmptyTreeException("No more elements available to iterate");
		
		final BinaryNode<T> nextNode = nodeStack.pop();
		
		if (nextNode.hasRightChild()) nodeStack.push(nextNode.getRightChild());
		if (nextNode.hasLeftChild()) nodeStack.push(nextNode.getLeftChild());
		return nextNode.getData();
	}
  } // end PreorderIterator

  private class PostorderIterator implements Iterator<T>
  {
	//You are allowed to create no more than
	//2 data fields in this class.
	Stack<BinaryNode<T>> nodeStack = new Stack<>();
	
	public PostorderIterator()
	{
		//TODO
		nodeStack.push(root);
	}

	public boolean hasNext()
	{
	 //TODO
	 return !nodeStack.isEmpty();
	}

	public T next()
	{
		//TODO
		if (!hasNext()) throw new EmptyTreeException("No more elements available to iterate");
		
		
		while(hasNext()) {
			  BinaryNode<T> nextNode = nodeStack.peek();
			  //System.out.println("nodeStack peek var " + nextNode.getData());
			  assert nextNode != null;
			  if(!nextNode.hasLeftChild() && !nextNode.hasRightChild()) {
				  BinaryNode<T> popedNode = nodeStack.pop();
				  return popedNode.getData();
			  }
			  else {
				  if(nextNode.hasRightChild()) {
					  nodeStack.push(nextNode.getRightChild());
					  nextNode.setRightChild(null);
				  }
				  if(nextNode.hasLeftChild()) {
		                nodeStack.push(nextNode.getLeftChild());
		                nextNode.setLeftChild(null);
		          }
			  }
		  }
		return null;
	}
  } // end PostorderIterator

  private class LevelOrderIterator implements Iterator<T>
  {
	  //You are allowed to create no more than
	  //2 data fields in this class.
	  LinkedList<BinaryNode<T>> nodeQueue = new LinkedList<>();
	  public LevelOrderIterator()
	  {
		  //TODO
		  nodeQueue.add(root);
	  }

	  public boolean hasNext()
	  {
		  //TODO
		  return nodeQueue.isEmpty(); 
	  }

	  public T next()
	  {
		  //TODO 
		  if (!hasNext()) throw new EmptyTreeException("No more elements available to iterate");
		  BinaryNode<T> nextNode = nodeQueue.poll();
		  assert nextNode != null;
		  
		  if (nextNode.hasLeftChild()) {
			  nodeQueue.add(nextNode.getLeftChild());
		  }
		  if (nextNode.hasRightChild()) {
			  nodeQueue.add(nextNode.getRightChild());
		  }
		  
		  return nextNode.getData();
	  }
  } // end LevelOrderIterator
} // end BinaryTree