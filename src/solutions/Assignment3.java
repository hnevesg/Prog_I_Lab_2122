import java.util.*;

public class Assignment3 {

	// ITERATION

	/*
	 * Write a program that reads N positive numbers from the keyboard and checks 
	 * if they are correct. If at any time the user enters a negative value, an error 
	 * message will be displayed and the application will ask the user again to enter the value; 
	 * this process will be repeated until the user enters a correct (positive) value.
	 * When a value is entered, the program will print on the screen if said value is within 
	 * the range [K1, K2]. To do this, consider that K1 and K2 are two positive integers that 
	 * are defined as constants. In the case that K1>K2, the reference range will be defined 
	 * by the interval [K2, K1]. 
	 * Finally, the program will also show the amount of total values entered during the execution 
	 * of the program and, the number of values introduced that were incorrect, and the number 
	 * of values in the range [K1, K2] (or [K2, K1]).
	 * 
	 */

	final static Scanner KEYBOARD = new Scanner(System.in);

	// define the values K1 and K2 for the range
	final static int K1 = 15;
	final static int K2 = 5;

	public static void main(String[] args) {

		// DATA
		int amountN, value, lowerBound, upperBound, countInRange;
		// amountN, amount of positive numbers to introduce
		// value, number read from the keyboard
		// lowerBound and upperBound, to define the bounds of the interval
		// countInRange, to count the amount of numbers in the range [lowerBound, upperBound]

		int countValues, countErrors;
		// countValues, count of how many values the user entered
		// countErrors, count of how many values the user entered were errors

		// INSTRUCTIONS

		// initialize all counters at 0
		countInRange = 0;
		countValues = 0;
		countErrors = 0;

		// ask N: amount of numbers to be read
		System.out.print("How many positive numbers do you want to introduce?... ");
		amountN = KEYBOARD.nextInt();
		countValues = countValues + 1; // valores++;

		// ask for another value while the number introduced is negative
		while (amountN < 0) {
			countErrors++; // countErrors = countErrors + 1;
			System.out.print("Error, you have introduced a negative value. Try again... ");
			amountN = KEYBOARD.nextInt();
			countValues++; // countValues = countValues + 1;
		}

		countInRange = 0;
		lowerBound = K1;
		upperBound = K2;
		if (K1 > K2) {
			lowerBound = K2;
			upperBound = K1;
		}

		System.out.println("The reference range is [" + lowerBound + ", " + upperBound + "]");
		for (int num = 1; num <= amountN; num++) {
			System.out.print("Introduce the positive number " + num + "... ");
			value = KEYBOARD.nextInt();
			countValues++;

			while (value <= 0) {
				countErrors++;
				System.out.print("Error, you have introduced a number that is not positive. Try again... ");
				value = KEYBOARD.nextInt();
				countValues++;
			}

			if (value >= lowerBound && value <= upperBound) {
				countInRange = countInRange + 1;
				System.out.println("The number " + value + " is in the range [" + lowerBound + ", " + upperBound + "]");
			}
		}
		System.out.print("\nThroughout the execution of the program " + countValues + " numbers have been read. ");
		System.out.println(countErrors + " of them where wrong.");
		System.out.println("In total, " + countInRange + " values in the range [" + lowerBound + ", " + upperBound + "] have been read.");
		System.out.println("\n***** End of the program... *****");
	}// main
}// program
