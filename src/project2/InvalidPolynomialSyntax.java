// CMSC 350 Data Structures and Analysis
// Project 2
// Evan Martin
// November 17, 2020

// This class displays a JOptionPane with a supplied error message

package project2;

import javax.swing.*;

public class InvalidPolynomialSyntax extends Throwable {

    InvalidPolynomialSyntax(String errorMessage) {
        JOptionPane.showMessageDialog(null,
                errorMessage, "error",
                JOptionPane.ERROR_MESSAGE);
    }
}
