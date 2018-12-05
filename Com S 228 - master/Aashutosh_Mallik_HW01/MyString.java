package edu.iastate.cs228.hw01;

/**
 * @author Aashutosh Mallik
 *
 */
public class MyString
{
	private char[] chars;
        /**
         * Main constuctor
         * @param chars 
         */
	public MyString(char[] chars)
	{
		if(chars == null || chars.length == 0) throw new IllegalArgumentException();
		this.chars = chars;
	}
        
        /**
         * get the length
         * @return 
         */
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	public int length()
	{
            int count = 0;
            for (char letter: chars) {
                count++;
            }
            return count;
	}
	
        /**
         * Get char at position, if position does not exists throws an error
         * @param index
         * @return 
         */
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	public char charAt(int index)
	{
            char result = ' ';
            if (index>=0) {
                if (index<this.length()) {
                    result = this.chars[index];
                } else {
                    throw new StringIndexOutOfBoundsException();
                }
            }
            return result;
	}
        /**
         * Returns an string starting from position begin and finishing before 
         * positon end
         * 
         * @param begin
         * @param end
         * @return 
         */
	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	public MyString substring(int begin, int end)
	{
            char[] result = new char[end - begin];
            for (int i = begin; i < end; i++) {
                result[i] = this.charAt(i);
            }
            return new MyString(result);
	}

        /**
         * It lowercase the char array
         * @return 
         */
	//It is ok to use
	//https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	public MyString toLowerCase()
	{
            int count=0;
            for (char i: this.chars) {
                 chars[count] = Character.toLowerCase(i);
                 count++;
            }
            return new MyString(chars);
	}

	/**
         * It returns the value of an integer in a MyString type
         * @param i
         * @return 
         */
	//You can assume only positive values.
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	public static MyString valueOf(int i)
	{
            int count = (int)(Math.log10(i)+1);
            char[] result = new char[count];
            int tmp = i;
            for (int j=count-1; j >=0; j--) {
                result[j] = (char)((tmp%10)+'0');
                tmp = tmp/10;
            }
            return new MyString(result);
	}


	public char[] toCharArray()
	{
		return chars;
	}
}