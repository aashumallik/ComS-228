package edu.iastate.cs228.hw01;

import java.math.BigInteger;

/**
 * @author Aashutosh Mallik
 */

/*
 * Rational.java: Define a rational number and its associated operations such as
 * add, subtract, multiply, and divide.
 */
@SuppressWarnings("serial")
public class Rational2 extends Number implements Comparable<Rational2> {
    // Data fields for numerator and denominator

    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    /**
     * Default constructor
     */
    public Rational2() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    /**
     * Construct a rational with specified numerator and denominator
     */
    public Rational2(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = ((denominator.compareTo(BigInteger.ZERO) > 0) ? new BigInteger("1") : new BigInteger("-1")).multiply(numerator.divide(gcd));
        this.denominator = denominator.abs().divide(gcd);
    }

    /**
     * Find GCD of two numbers
     */
    private BigInteger gcd(BigInteger n, BigInteger d) {
        //long n1 = Math.abs(n);
        //long n2 = Math.abs(d);
        BigInteger n1 = n.abs();
        BigInteger n2 = d.abs();

        //int gcd = 1;
        BigInteger gcd = BigInteger.ONE;

        //for (int k = 1; k <= n1 && k <= n2; k++)
        for (BigInteger k = BigInteger.ONE; k.compareTo(n1) <= 0 && k.compareTo(n2) <= 0; k = k.add(BigInteger.ONE)) {
            {
                //if (n1 % k == 0 && n2 % k == 0)
                if (n1.mod(k).equals(BigInteger.ZERO) && n2.mod(k).equals(BigInteger.ZERO)) {
                    gcd = k;
                }

            }
        }
        return gcd;
    }
        /**
         * Return numerator
         */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * Return denominator
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * Add a rational number to this rational
     */
    public Rational2 add(Rational2 secondRational) {
        BigInteger n = (numerator.multiply(secondRational.getDenominator())).add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational2(n, d);
    }

    /**
     * Subtract a rational number from this rational
     */
    public Rational2 subtract(Rational2 secondRational) {
        BigInteger n = (numerator.multiply(secondRational.getDenominator())).subtract(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational2(n, d);
    }

    /**
     * Multiply a rational number to this rational
     */
    public Rational2 multiply(Rational2 secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational2(n, d);
    }

    /**
     * Divide a rational number from this rational
     */
    public Rational2 divide(Rational2 secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.numerator);
        return new Rational2(n, d);
    }

    @Override
    public String toString() {
        if (0==denominator.compareTo(BigInteger.ONE)) {
            return numerator + "";
        } else {
            return numerator + "/" + denominator;
        }
    }

    /**
     * Override the equals method in the Object class
     */
    public boolean equals(Object parm1) {
        if (this == parm1) {
            return true;
        }
        if ((parm1 == null) || (parm1.getClass() != this.getClass())) {
            return false;
        }

        if ((this.subtract((Rational2) (parm1))).getNumerator().equals(BigInteger.ZERO)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Override the abstract intValue method in java.lang.Number
     */
    public int intValue() {
        return (int) doubleValue();
    }

    /**
     * Override the abstract floatValue method in java.lang.Number
     */
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * Override the doubleValue method in java.lang.Number
     */
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    /**
     * Override the abstract longValue method in java.lang.Number
     */
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public int compareTo(Rational2 o) {
        if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0) {
            return 1;
        } else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
