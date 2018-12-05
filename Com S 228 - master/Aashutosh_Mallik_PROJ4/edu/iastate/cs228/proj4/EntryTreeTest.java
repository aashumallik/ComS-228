package edu.iastate.cs228.proj4;

import static org.junit.Assert.*;


import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Aashutosh Mallik
 *
 */

public class EntryTreeTest {
	
	EntryTree<Character, String> newTree;
	Character[] charArrGood;
	Character[] charArrBad;
	String stringGood;
	String stringBad;
	Character key;
	
	/*
	 * Initializes variables
	 */
	@Before 
	public void initialize() {
	    
	newTree = new EntryTree<>();
	charArrGood = new Character[4];
	charArrGood[0] = 'e';
	charArrGood[1] = 'd';
	charArrGood[2] = 'i';
	charArrGood[3] = 't';
	
	charArrBad = new Character[4];
	charArrBad[0] = 'e';
	charArrBad[1] = 'd';
	charArrBad[2] = null;
	charArrBad[3] = 't';
	
	
	stringGood = "value";
	stringBad = null;
	
	key = 'k';
}
	

	/*
	 * Tests NullPointerExceptions in EntryTree methods
	 * Thrown if any part of argument is null
	 */
	
	@Test(expected=NullPointerException.class)
	public void testNullPointerException() {
		newTree.add(charArrBad, stringGood);
	    newTree.search(charArrBad);
	    newTree.remove(charArrBad);
	    newTree.prefix(charArrBad);
	}
	
	
	/*
	 * Tests Node class methods
	 */
	@Test
	public void testNodeMethods(){
		EntryTree<Character,String>.Node node = newTree.new Node(key, stringGood);	//Create new Node
		
		assertEquals(key, node.key());	//Test that key works correctly
		assertEquals(stringGood, node.value());	//Test that value works correctly
		
		//Test that all attributes initialize to null
		assertNull(node.child());
		assertNull(node.parent());
		assertNull(node.next());
		assertNull(node.prev());

		//Test child() method
		EntryTree<Character,String>.Node nodeChild = newTree.new Node('c', "child");
		node.child = nodeChild;
		assertEquals(nodeChild, node.child());
		
		//Test parent() method
		EntryTree<Character,String>.Node nodeParent = newTree.new Node('p', "parent");
		node.parent = nodeParent;
		assertEquals(nodeParent, node.parent());
		
		//Test next() method
		EntryTree<Character,String>.Node nodeNext = newTree.new Node('n', "next");
		node.next = nodeNext;
		assertEquals(nodeNext, node.next());
		
		//Test prev() method
		EntryTree<Character,String>.Node nodePrev = newTree.new Node('r', "prev");
		node.prev = nodePrev;
		assertEquals(nodePrev, node.prev());
		
	}
	
	/*
	 * Tests the add() method
	 */
	@Test
	public void testAddMethod() {
		
		assertFalse(newTree.add(charArrGood, stringGood));	//Returns true if proper arguments
		assertFalse(newTree.add(charArrGood, stringBad));	//Returns false if there are any nulls in arguments

	}
	
	/*
	 * Tests the prefix() method
	 */
	
	public void testPrefixMethod(){
		newTree.add(charArrGood, stringGood);
		Character[] prefixCharArr = new Character[6];
		prefixCharArr[0] = 'e';
		prefixCharArr[1] = 'd';
		prefixCharArr[2] = 'i';
		prefixCharArr[3] = 't';
		prefixCharArr[4] = 'i';
		prefixCharArr[5] = 'o';
		assertFalse(Arrays.equals(charArrGood, newTree.prefix(prefixCharArr)));
		
		//Make sure prefix only search children starting at root
		Character[] prefixCharArrTwo = {'d', 'i', 't'};
		assertFalse(Arrays.equals(charArrGood, newTree.prefix(prefixCharArrTwo)));
	}
	
	private void assertFalse(boolean equals) {
		// TODO Auto-generated method stub
		
	}


	/*
	 * Tests search() method
	 */
	
	public void testSearchMethod(){
		newTree.add(charArrGood, stringGood);
		assertEquals(stringGood, newTree.search(charArrGood));
	}
	
	private void assertEquals(String stringGood2, String search) {
		// TODO Auto-generated method stub
		
	}


	/*
	 * Tests remove() method
	 */
	
	public void testRemoveMethod(){
		newTree.add(charArrGood, stringGood);
		assertEquals(stringGood, newTree.remove(charArrGood));
	}
}