package edu.iastate.cs228.proj2;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Aashutosh Mallik
 * Selection sort is slower for big data because selection sort writes o(n^2) times, Selection sort works fast on small dataset. Merge sort performs better with large data sets because it uses time complexity being Ο(n log n), Merge sort works the same for small dataset because the time complexity stays the same O(n log(n)).
 * Quick Sort works kind of the same way as Merge Sort becuase of its similar tie complexity being Ο(n log n) but the worst case scenario being o(n^2s).

 */
public class EvalSorts {
	
	public static final int kNumberOfWordsToSort = 100000;

	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	 * @throws FileConfigurationException 
	 * @throws FileNotFoundException 
	*/
	public static void main(String args[]) throws FileNotFoundException, FileConfigurationException {
		char[] alphabet   = null;  //ref to the Lexicon it creates. 
		String[] wordList = null;  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		

		alphabet = readCharacterOrdering(args[0]);
		comp = new LexiconImpl(alphabet);
		wordList = readWordsFile(args[1], comp);		

		//configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		//now execute that instance
		theApp.runSorts();
		
	}

	
	private String[] words; //ref to the word list
	private Lexicon lex;    //ef to the relevant lexicon	
	private int numWordsToSort = kNumberOfWordsToSort;
	
	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in
	 * an instance of Lexicon
	 * @param lex the instance of lexicon to be used
	 * @param wordList the wordlist (as array of string)  to be sorted
	 * @param numWordsToSort each sort will be repeated until it has sorted
	 *                       this many words. 
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		//TODO
		this.lex = lex;
		words = wordList;
		this.numWordsToSort = numWordsToSort;
		
	}

	/**
	 * runSorts() performs the sort evaluation. 
	 * 
	 * Note: The three sorters extend a common base
	 * so they share the same interface for starting the sort and collecting statistics. 
	 * Thus, you should create instances of the sorter and save references to each in an 
	 * array of base type. This allows you to use a simple loop to run all the reports and 
	 * collect the statistics.   
	 */
	public void runSorts(){
		
		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		
		sorters[0] = new MergeSort();
		sorters[1] = new QuickSort();
		sorters[2] = new SelectionSort();
		
		for (int i = 0; i < sorters.length; ++i) {
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
		}

	}
	
	/**
	 * Reads the characters contained in filename and returns them as a character array.
	 * @param filename the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used 
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename) 
			throws FileNotFoundException, FileConfigurationException {
		 
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			char[] charArray = new char[kNumberOfWordsToSort];
			int index = 0;
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				if (str.length() > 1) {
					throw new FileConfigurationException();
				}
				char c = str.charAt(0);
				int i = 0;
				while (charArray[i] != 0) {//If not null
					if (c == charArray[i]) {//If char already exists in char arr close file and throw exeception
						scanner.close();
					throw new FileConfigurationException();
					}
					++i;
				}
				charArray[index] = c;//Otherwise add it to charArr and continue
				++index;
				
			}
			char[] completedArray = new char[index+1];//Resizes array to fit just right
			for (int i = 0; i < completedArray.length; ++i) {//Copy's elements into a returnable array
			completedArray[i] = charArray[i];
			}
			scanner.close();//Close File
			return completedArray;//Return array
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File Not Found please check location and try again");
		}  catch (FileConfigurationException e) {
			throw new FileConfigurationException();
		}
	}
	
	/**
	 * Reads the words from the file and returns them as a String array.
	 * @param filename file containing words
	 * @return the words contained in the file or null if there was a FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		 
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			String[] wordsArray = new String[kNumberOfWordsToSort];
			int index = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!comp.isValid(line)) {
					throw new FileConfigurationException();
				}
				wordsArray[index] = line;
				++index;
			}
			String[]  finalWords = new String[index];
			for (int i = 0; i < finalWords.length; ++i) {
				finalWords[i] = wordsArray[i];
			}
			scanner.close();
			return finalWords;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File Not Found, please check location and try again.");
		} catch (FileConfigurationException e) {
			throw new FileConfigurationException();
		}
	}

}
