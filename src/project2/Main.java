// CMSC 350 Data Structures and Analysis
// Project 2
// Evan Martin
// November 17, 2020

// This class allows the user to select a file containing polynomials that
// it is then scanned line by line as strings and sent to the Polynomial Class.
// The Polynomial class returns a Polynomial Object and these are contained in an array list.
// This class also calculates if the list is strongly and/or weakly ordered.
// The list of polynomials and results of the calculations are then displayed.

package project2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidPolynomialSyntax {

        ArrayList<Polynomial> polynomialArrayList = new ArrayList<>();

        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.showOpenDialog(null);

        File f = j.getSelectedFile();
        String filepath = f.getPath();
        Scanner scanner = new Scanner(new FileReader(filepath));

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if(s.equals("")) {
              s = scanner.nextLine();
            }
            Polynomial poly = new Polynomial(s);
            polynomialArrayList.add(poly);
        }

        scanner.close();

        for (Polynomial polynomial : polynomialArrayList) {
            System.out.println(polynomial.toString());
        }
        System.out.println("Strong Order: " + checkStrongOrder(polynomialArrayList));
        checkStrongOrder(polynomialArrayList);
        System.out.println("Weak Order: " + checkWeakOrder(polynomialArrayList));
        checkWeakOrder(polynomialArrayList);
    }

    public static boolean checkStrongOrder(ArrayList<Polynomial> polynomialArrayList) {
        for (int i = 0; i < polynomialArrayList.size() - 1; i++) {
            if (polynomialArrayList.get(i).compareTo(polynomialArrayList.get(i + 1)) > 0) return false;
        }
        return true;
    }

    public static boolean checkWeakOrder(ArrayList<Polynomial> polynomialArrayList) {
        for (int i = 0; i < polynomialArrayList.size() - 1; i++) {
            if (polynomialArrayList.get(i).compareTo(polynomialArrayList.get(i + 1), true) > 0) return false;
        }
        return true;
    }

}
