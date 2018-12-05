package edu.iastate.cs228.hw01;

import java.math.BigInteger;
import java.util.Scanner;
/**
 * @author Aashutosh Mallik
 */
public class TestMyString
{
	public static void main(String[] args)
	{
            
            // Testing MyString class
            System.out.println("==============================================");
            System.out.println("                MyString Class ");
            System.out.println("==============================================");
            MyString s = new MyString(new char[] { 'a', 'b', 'c' });
            System.out.println(s.length());
            System.out.println(s.charAt(1));
            System.out.println(s.equals(new MyString(new char[] { 'a', 'b', 'c' })));

            char[] chars = MyString.valueOf(345).toCharArray();
            for (int i = 0; i < chars.length; i++)
            {
                    System.out.print(chars[i]);
            }
            System.out.println("");
            System.out.println("==============================================");
            System.out.println("         Rational2 Class w/BigInteger");
            System.out.println("==============================================");
            
            // Testing Rational2 Class
            Rational2 r1 = new Rational2(new BigInteger("1000000"), new BigInteger("2000000"));
            Rational2 r2 = new Rational2(new BigInteger("3000000"), new BigInteger("4000000"));

            System.out.println("("+r1 + ") * (" + r2 + ") = " + r1.multiply(r2));
            System.out.println("("+r1 + ") / (" + r2 + ") = " + r1.divide(r2));
            System.out.println("("+r1 + ") + (" + r2 + ") = " + r1.add(r2));
            System.out.println("("+r1 + ") - (" + r2 + ") = " + r1.subtract(r2));
            
            
            System.out.println("==============================================");
            System.out.println("                Triangle Class");
            System.out.println("==============================================");
            
            Triangle triangle = new Triangle(5,5,5);
            System.out.println(triangle.getArea());
            
	}
}

/*
Outputs:
3
b
false
345
*/


