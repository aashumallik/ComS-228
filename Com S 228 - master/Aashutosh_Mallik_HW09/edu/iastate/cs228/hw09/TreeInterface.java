package edu.iastate.cs228.hw09;

public interface TreeInterface<T>
{
  public T getRootData();

  public int getHeight();

  public int getNumberOfNodes();

  public boolean isEmpty();

  public void clear();
} // end TreeInterface