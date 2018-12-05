package edu.iastate.cs228.hw09;

import java.util.Iterator;

/**
 * An interface of iterators for the ADT tree.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 */
public interface TreeIteratorInterface<T>
{
  public Iterator<T> getPreorderIterator();

  public Iterator<T> getPostorderIterator();

  public Iterator<T> getInorderIterator();

  public Iterator<T> getLevelOrderIterator();
} // end TreeIteratorInterface