package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author Aashutosh Mallik
 * 
 * 
 * An application class that uses EntryTree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the EntryTree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the EntryTree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the EntryTree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the EntryTree class on each command line 
 * specifies that the public method should be called from the EntryTree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of EntryTree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary {
	public static void main(String[] args) {
		// Create tree
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		FileReader inputFile = null;
		try {
			// Get input file from args
			inputFile = new FileReader("infile.txt");
		} catch (FileNotFoundException e) {
			// If file not found, print the directory that the user should place
			// the file in, then terminate
			String current;
			try {
				current = new java.io.File(".").getCanonicalPath();
				System.out.println("Place file in this directory:" + current);
			} catch (IOException e1) {
			}
			System.out.println("Terminating the program.");
			System.exit(-1);
		}

		// Start scanning
		Scanner sc = new Scanner(inputFile);
		while (sc.hasNextLine()) {
			String line;
			line = sc.nextLine(); 
			String[] sArr = line.split("\\s+"); 
			if (sArr[0].equalsIgnoreCase("add")) {
				System.out.println("Command: " + sArr[0] + " " + sArr[1] + " "
						+ sArr[2]);
				System.out.println("Result from add: "
						+ tree.add(toCharacterArray(sArr[1]), sArr[2]));
			}
			if (sArr[0].equalsIgnoreCase("remove")) {

				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.println("Result from remove: "
						+ tree.remove(toCharacterArray(sArr[1])));
			}
			if (sArr[0].equalsIgnoreCase("showTree")) {
				System.out.println("Result from showTree:");
				tree.showTree();
			}
			if (sArr[0].equalsIgnoreCase("search")) {
				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.println("Result from search: " + tree.search(toCharacterArray(sArr[1])));
			}
			if (sArr[0].equalsIgnoreCase("prefix")) {
				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.print("Result from prefix: ");
				Character[] charray = tree.prefix(toCharacterArray(sArr[1]));
				for (int i = 0; i < charray.length; i++) {
					System.out.print(charray[i]);
				}
				System.out.println("");
			}
			System.out.println("");
		}
	}
	private static Character[] toCharacterArray(String s) {
		if (s == null) {
			return null;
		}
		Character[] array = new Character[s.length()];
		for (int i = 0; i < s.length(); i++) {
			array[i] = new Character(s.charAt(i));
		}
		return array;
	}

}