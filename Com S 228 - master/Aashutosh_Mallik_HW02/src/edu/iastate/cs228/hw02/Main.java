package edu.iastate.cs228.hw02;

public class Main {
	/**

	@author Aashutosh Mallik
	*/

    public static void main(String[] args) {

        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();
        BagInterface<String> bag3 = new LinkedBag<>();

        bag1.add("a");
        bag1.add("b");
        bag1.add("c");

        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");


        System.out.println("BAG 1 : " + bag1);
        System.out.println("BAG 2 : " + bag2);

        System.out.println("TEST UNION : LinkedBag");
        bag3 = bag1.union(bag2);
        System.out.println("BAG 3 : " + bag3);

        System.out.println("TEST INTERSECT : LinkedBag");
        bag3 = bag1.intersection(bag2);
        System.out.println("BAG 3 : INTERSECT : " + bag3);

        bag3 = bag2.intersection(bag1);
        System.out.println("BAG 3 : INTERSECT : " + bag3);

        System.out.println("TEST DIFF : LinkedBag");
        bag3 = bag1.difference(bag2);
        System.out.println("BAG1 diff BAG2 : " + bag3);

        bag3 = bag2.difference(bag1);
        System.out.println("BAG2 diff BAG1: " + bag3);




        BagInterface<String> rbag1 = new ResizableArrayBag<>();
        BagInterface<String> rbag2 = new ResizableArrayBag<>();
        BagInterface<String> rbag3;

        rbag1.add("a");
        rbag1.add("b");
        rbag1.add("c");

        rbag2.add("b");
        rbag2.add("b");
        rbag2.add("d");
        rbag2.add("e");

        System.out.println("BAG 1 : " + rbag1);
        System.out.println("BAG 2 : " + rbag2);

        System.out.println("TEST UNION : ResizableArrayBag");
        rbag3 = rbag1.union(rbag2);
        System.out.println("BAG 3 : " + rbag3);

        System.out.println("TEST INTERSECT : ResizableArrayBag");
        rbag3 = rbag1.intersection(rbag2);
        System.out.println("BAG 3 : INTERSECT : " + rbag3);

        rbag3 = rbag2.intersection(rbag1);
        System.out.println("BAG 3 : INTERSECT : " + rbag3);

        System.out.println("TEST DIFF : ResizableArrayBag");
        rbag3 = rbag1.difference(rbag2);
        System.out.println("BAG1 diff BAG2 : " + rbag3);

        rbag3 = rbag2.difference(rbag1);
        System.out.println("BAG2 diff BAG1: " + rbag3);



    }
}
