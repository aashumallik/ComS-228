package edu.iastate.cs228.hw01;

/**
 * @author Y. Daniel Liang
 * 
 * Changes applied for cs228.hw01:
 * => The original equals method implementation is changed.
 * => Removed one of the methods.
 * => Check:
 *    https://www.mathsisfun.com/algebra/rational-numbers-operations.html
 *    https://www.mathsisfun.com/greatest-common-factor.html  
 */

/*
 * Rational.java: Define a rational number and its associated operations such as
 * add, subtract, multiply, and divide.
 */

@SuppressWarnings("serial")
public class Rational extends Number implements Comparable<Rational>
{
	// Data fields for numerator and denominator
	private long numerator = 0;
	private long denominator = 1;

	/** Default constructor */
	public Rational()
	{
		this(0, 1);
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational(long numerator, long denominator)
	{
		long gcd = gcd(numerator, denominator);
		this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
		this.denominator = Math.abs(denominator) / gcd;
	}

	/** Find GCD of two numbers */
	private long gcd(long n, long d)
	{
		long n1 = Math.abs(n);
		long n2 = Math.abs(d);

		int gcd = 1;

		for (int k = 1; k <= n1 && k <= n2; k++)
		{
			if (n1 % k == 0 && n2 % k == 0)
				gcd = k;
		}
		return gcd;
	}

	/** Return numerator */
	public long getNumerator()
	{
		return numerator;
	}

	/** Return denominator */
	public long getDenominator()
	{
		return denominator;
	}

	/** Add a rational number to this rational */
	public Rational add(Rational secondRational)
	{
		long n = numerator * secondRational.getDenominator() + denominator * secondRational.getNumerator();
		long d = denominator * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational subtract(Rational secondRational)
	{
		long n = numerator * secondRational.getDenominator() - denominator * secondRational.getNumerator();
		long d = denominator * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational multiply(Rational secondRational)
	{
		long n = numerator * secondRational.getNumerator();
		long d = denominator * secondRational.getDenominator();
		return new Rational(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational divide(Rational secondRational)
	{
		long n = numerator * secondRational.getDenominator();
		long d = denominator * secondRational.numerator;
		return new Rational(n, d);
	}

	@Override
	public String toString()
	{
		if (denominator == 1)
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	/** Override the equals method in the Object class */
	public boolean equals(Object parm1)
	{
		if(this == parm1) return true;
		if((parm1 == null) || (parm1.getClass()!=this.getClass())) return false;
			
		if ((this.subtract((Rational) (parm1))).getNumerator() == 0)
			return true;
		else
			return false;
	}

	/** Override the abstract intValue method in java.lang.Number */
	public int intValue()
	{
		return (int) doubleValue();
	}

	/** Override the abstract floatValue method in java.lang.Number */
	public float floatValue()
	{
		return (float) doubleValue();
	}

	/** Override the doubleValue method in java.lang.Number */
	public double doubleValue()
	{
		return numerator * 1.0 / denominator;
	}

	/** Override the abstract longValue method in java.lang.Number */
	public long longValue()
	{
		return (long) doubleValue();
	}

	@Override
	public int compareTo(Rational o)
	{
		if (this.subtract(o).getNumerator() > 0)
			return 1;
		else if (this.subtract(o).getNumerator() < 0)
			return -1;
		else
			return 0;
	}
}
