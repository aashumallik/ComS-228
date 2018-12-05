package edu.iastate.cs228.hw09;

@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException
{
  public EmptyTreeException()
  {
	this(null);
  } // end default constructor

  public EmptyTreeException(String message)
  {
	super(message);
  } // end constructor
} // end EmptyTreeException