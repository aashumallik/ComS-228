package edu.iastate.cs228.hw01;
/**
 * @author Aashutosh Mallik
 */
public class Triangle extends GeometricObject {
    private double side1, side2, side3;

    /**
     * A no­arg constructor that creates a default triangle with sides' 
     * values 1.0 to denote the three sides of a triangle.
     */
    public Triangle(){
        this(1.0, 1.0, 1.0);
    }
    
    /**
     * A constructor that creates a triangle with the specified side1, side2, 
     * and side3. (You can assume that sides values provided will be positive, 
     * and that triangle inequality would hold for these three values of sides.)
     * 
     * @param side1
     * @param side2
     * @param side3 
     */
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * The public accessor methods for side1 named getSide1
     * @return 
     */
    public double getSide1() {
        return side1;
    }

    /**
     * The public accessor methods for side2 named getSide2
     * 
     * @return 
     */
    public double getSide2() {
        return side2;
    }

    /**
     * The public accessor methods for side3 named getSide3
     * @return 
     */
    public double getSide3() {
        return side3;
    }
    
    /**
     * A public method named getArea() that returns the area of this triangle 
     * as a double value.
     * 
     * @return 
     */
    public double getArea(){
        double s = getPerimeter()/2;
        double area = Math.sqrt(((s-side1)*(s-side2)*(s-side3))*s);
        return area;
    }
    /**
     * Get Perimeter of current triangle
     * @return 
     */
    public double getPerimeter(){
        return side1+side2+side3;
    }
    
    /**
     * Overrides the toString to display meaningful information.
     * @return 
     */
    @Override
    public String toString()
    {
            return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
    }

}



/*
INSTRUCTIONS

Part 1. [20 pts] Design a class named Triangle that extends GeometricObject. The class needs to
contain only:
Three private double data fields named side1, side2, and side3.
A no­arg constructor that creates a default triangle with sides' values 1.0 to denote the three sides of a
triangle.
A constructor that creates a triangle with the specified side1, side2, and side3. (You can assume that
sides' values provided will be positive, and that triangle inequality would hold for these three values of
sides.)
The public accessor methods for all three data fields, named getSide1, getSide2, and getSide3,
respectively.
A public method named getArea() that returns the area of this triangle as a double value.
A public method named getPerimeter() that returns the perimeter of this triangle as a double value.
A public method named toString() that returns a String description for the triangle. This method is
already implemented for you.
For the formula to compute the area of a triangle, use Heron's
Formula https://www.mathsisfun.com/geometry/herons­formula.html
(https://www.mathsisfun.com/geometry/herons­formula.html)
 */