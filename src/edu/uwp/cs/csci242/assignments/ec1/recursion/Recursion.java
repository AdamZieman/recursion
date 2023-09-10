package edu.uwp.cs.csci242.assignments.ec1.recursion;

/**
 * This class will demonstrate the programmer's knowledge of recursion.
 * <p>
 *     This class contains 5 unique recursive methods, to demonstrate the programmer's understanding of recursion.
 *     Each method of this class is invoked by the main() method which contains test cases for each method, excluding
 *     trickyHanoi() method which is a user dependant upon the user.
 * </p>
 * @author Adam Zieman
 * @edu.uwp.cs.242.course CSCI 424 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 4
 * @bugs none
 */

import java.util.Scanner;

public class Recursion {
    /**
     * Given a string, return a "cleaned" string where adjacent characters
     * that are the same have been reduced to a single character. The
     * method must be recursive.
     *
     * @param str String value that may include identical, adjacent characters
     * @return String value that does not have any identical, adjacent characters
     */
    public static String stringClean(String str) {
        // if the String has a length of 1 or less, it does not contain any duplicates. Return the String
        if (str.length() <= 1) {
            return str;
        }
        // if the character at position 0 and 1 are the same, recursively call the method with a substring excluding the first duplicate
        if (str.charAt(0) == str.charAt(1)) {
            return stringClean(str.substring(1));
        }
        // if the character at position 0 and 1 were not the same, recursively call the method with a substring starting
        // at position 1 and return the non-duplicate character
        return str.charAt(0) + stringClean(str.substring(1));
    }

    /**
     * Recursive function countDigit() to count the number of time a
     * particular digit appears in a number n, where n > 0.
     *
     * @param num int value of a number to be searched
     * @param digit int value of a particular digit to search for
     * @return int value of how many times a particular digit was found in a numb
     */
    public static int countDigit(int num, int digit) {
        // counts the number of times a digit appears within a number
        int count = 0;

        // if the number is a single-digit and contains the digit, increment counter
        if (num < 10 && num == digit) {
            count++;
        }
        // if the number is a single-digit but doesn't contain the digit, don't increment the counter
        else if (num < 10) {
            count += 0;
        }
        // if the number is larger than a single-digit, recursively call the method with a single-digit of the method,
        // and the remainder
        else {
            return countDigit(num / 10, digit) + countDigit(num % 10, digit);
        }

        // returns the number of times a digit appears within a number
        return count;
    }

    /**
     * A recursive function that takes a string from which all characters
     * except the bracketing operators have been removed. The function returns
     * true if the bracketing operators in the string are balanced, which means
     * that they are correctly nested and aligned. If the string is not
     * balanced, the function returns false.
     *
     * @param str String value which should only contain bracketing operator characters
     * @return boolean value of whether the bracketing operators are balanced
     */
    public static boolean isBalanced(String str) {
        // int value for the leading character of the pair of bracketing operators
        int index = 0;
        boolean isBalanced = true;
        /*
        Checks if the String has a consecutive pair of bracketing operators.

        If the String does not have meet this condition, the String must be unbalanced and will return false.

        If the String does meet this condition, nested if-statements will check the length of the String. If there are
        only two characters, then the String must be balanced and will return true. If there are more than two
        characters, then the String will be reduced by one pair of bracketing operators.

        The method will be recursively called, with the reduced String as the parameter, until it is determined to be
        un-balanced or balanced
         */
        if (!(str.contains("()")) && !(str.contains("[]")) && !(str.contains("{}"))) {
            isBalanced = false;
        }
        else {
            // If the String contained a pair of bracketing operators and is only 2 characters long, then it is balanced
            if (str.length() == 2) {
                isBalanced = true;
            }

            // Determines the index of the pair of bracketing operators
            if (str.contains("()")) {
                index = str.indexOf("()");
            }
            else if (str.contains("[]")) {
                index = str.indexOf("[]");
            }
            else {
                index = str.indexOf("{}");
            }

            // Reduces the String by a pair of bracketing operators, and recursively calls the method
            if (index == 0) {
                isBalanced(str.substring(2));
            }
            else if (index == str.length() - 2) {
                isBalanced(str.substring(0, str.length() - 2));
            }
            else {
                isBalanced(str.substring(0, index) + str.substring(index + 2));
            }
        }
        // The initial String will return true if it contained a pair of bracketing operators
        return isBalanced;
    }

    /**
     * Given an array of ints, is it possible to divide the ints into two
     * groups, so that the sums of the two groups are the same.
     * @param array
     * @return
     */
    public static boolean splitArray(int[] array) {
        return splitArrayAux ( array, 0, 0, 0 );
    }

    /**
     * splitArray() helper method. See splitArray().
     * @param array
     * @param index
     * @param leftSum
     * @param rightSum
     * @return
     */
    public static boolean splitArrayAux(int[] array, int index, int leftSum, int rightSum) {
        // if the index is greater-than or equal to the length of the array, return a boolean if the int array can
        // be split into 2 equal sums
        if (index >= array.length) {
            return leftSum == rightSum;
        }
        // if the index is less-than the size of the array, recursively call the method which will increment the leftSum
        // or the rightSum. This will show all combinations of the array.
        else {
            return splitArrayAux(array, index+1, leftSum + array[index], rightSum) ||
                    splitArrayAux(array, index+1, leftSum, rightSum + array[index]);
        }
    }

    /**
     * transfer all the disks from peg A to peg C, but every move must involve
     * peg B (either as the from peg or the to peg). We still maintain the
     * restriction that only smaller disks can go on top of larger disks.
     * @param disks int value of the amount of disks
     */
    public static void trickyHanoi(int disks) {
        trickyHanoiAux ( disks, 'A', 'C', 'B' );
    }

    /**
     * trickyHanoi() helper method. See trickyHanoi().
     * @param disks int value of the disk to be moved
     * @param from char value of the peg to move the disk from
     * @param to char value of the peg to move the disk to
     * @param using char value of the peg that that cannot hold a larger disk on top of
     */
    public static void trickyHanoiAux(int disks, char from, char to, char using) {
        // base case for the smallest disk
        if (disks == 1) {
            System.out.println("Move disk 1 from peg " + from + " to peg " + to);
            return;
        }

        // tail-recursion to move the disks
        trickyHanoiAux(disks - 1, from, using, to);
        System.out.println("Move disk "  + disks + " from peg " + from + " to peg " + to);
        // head-recursion to move the disks
        trickyHanoiAux(disks - 1, using, to, from);
    }

    /**
     * main() is the first method ran when the program is ran. Contains test cases for all recursive methods.
     * @param args no arguments supplied when the program is called
     */
    public static void main(String[] args) {
        // checks stringClean() method
        System.out.println(stringClean ("yyzzza")); // yza
        System.out.println(stringClean("abbbcdd")); // abcd
        System.out.println(stringClean("Hello")); // Helo

        // checks countDigit() method
        System.out.println(countDigit (222,2)); // 3
        System.out.println(countDigit (123414, 1)); // 2
        System.out.println(countDigit ( 123414, 5)); // 0

        // Checks isBalanced() method
        System.out.println("Testing Parentheses");
        System.out.println(Recursion.isBalanced("(){}[]")); // true
        System.out.println(Recursion.isBalanced("")); // false
        System.out.println(Recursion.isBalanced("()")); // true
        System.out.println(Recursion.isBalanced("()()")); // true
        System.out.println(Recursion.isBalanced("(())")); // true
        System.out.println(Recursion.isBalanced("((){})[]")); // true
        System.out.println(Recursion.isBalanced("()[{}][]")); // true
        System.out.println(Recursion.isBalanced("(()())((()))((()))")); // true
        System.out.println(Recursion.isBalanced("{(})")); // false
        System.out.println((Recursion.isBalanced(")("))); // false
        System.out.println("\n\n");

        // Checks splitArray() method
        int [] array = {2,3,5};
        int [] brray = {2, 5, 3};
        int [] crray = {2, 3, 7};
        System.out.println(splitArray(array)); // true
        System.out.println(splitArray(brray)); // true
        System.out.println(splitArray(crray)); // false

        // Demonstrates trickyHanoi() method
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of disks: ");
        int n = in.nextInt();
        trickyHanoi(n); // prints each disk move
    }
}
