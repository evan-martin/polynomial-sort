// CMSC 350 Data Structures and Analysis
// Project 2
// Evan Martin
// November 17, 2020

// This class creates a linked list from a supplied string while checking for syntax errors.
// It also contains toString methods to correctly display the list as well as
// methods to compare and iterate over nodes

package project2;

import java.util.Iterator;
import java.util.Scanner;

public class Polynomial implements Comparable<Polynomial>, Iterable<Polynomial.Node> {

    private Node head;

    static class Node {
            double coefficient;
            double exponent;
            Node next;

            Node(double c, double e) throws InvalidPolynomialSyntax  {
                coefficient = c;
                exponent = e;
                next = null;

                if (exponent < 0) throw new InvalidPolynomialSyntax("A polynomial may not contain negative exponent");
            }

            @Override
            public String toString(){
                String s =  String.valueOf(coefficient);

                if (exponent == 0) return s;
                else if(exponent == 1) return s + "x";
                else return s + "x^" + exponent;
            }
        }

    public Polynomial(String file) throws InvalidPolynomialSyntax {
        head = null;
        Scanner scanner = new Scanner(file);

        try {
            while (scanner.hasNext()) {
                Node index = head;
                if (index == null) {
                    head = new Node(scanner.nextDouble(), scanner.nextDouble());
                    head.next = null;
                } else {
                    while (index.next != null) index = index.next;
                    index.next = new Node(scanner.nextDouble(), scanner.nextDouble());
                    if (index.exponent < index.next.exponent) throw new InvalidPolynomialSyntax("Polynomial exponents must be in descending order");
                }
            }
            scanner.close();
        }catch (Exception error){
            throw new InvalidPolynomialSyntax("Syntax error");
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node next = head.next;

        if (head.coefficient > 0) stringBuilder.append(head.toString());
        else stringBuilder.append(" - ").append(head.toString());

        while(next !=null){
            if (next.coefficient > 0) {
                stringBuilder.append(" + ").append(next.toString());
            } else {
                stringBuilder.append(" - ").append(next.toString());
            }
            next = next.next;
        }
        return stringBuilder.toString();
    }


    @Override
    // returns 1 if the object the method is called on is greater than the object supplied as a parameter
    public int compareTo(Polynomial poly1) {

        while (this.head != null || poly1.head != null)
            if (this.head.exponent > poly1.head.exponent) return 1;
            else if (this.head.exponent < poly1.head.exponent) return 0;
            else if (this.head.coefficient > poly1.head.coefficient) return 1;
            else if (this.head.coefficient < poly1.head.coefficient) return 0;
            else {
                this.head = this.head.next;
                poly1.head = poly1.head.next;
        }
        return 0;
    }

    public int compareTo(Polynomial poly1, boolean exponent){
        while (this.head != null || poly1.head != null)
            if (this.head.exponent > poly1.head.exponent) return 1;
            else if (this.head.exponent < poly1.head.exponent) return 0;
            else {
                this.head = this.head.next;
                poly1.head = poly1.head.next;
        }
        return 0;
    }

    @Override
    public Iterator<Node> iterator() {
        Node node = this.head;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public Node next() {
                return node.next;
            }
        };
    }



}

