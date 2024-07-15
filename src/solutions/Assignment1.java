import java.util.Scanner;

public class Assignment1 {
	// declare the scanner for reading from the keyboard
	final static Scanner KEYBOARD = new Scanner(System.in);

	// define the first two percentage values as constants, since they are fixed.
	final static int PERCENTAGE1 = 95;
	final static int PERCENTAGE2 = 85;

	public static void main(String[] args) {

		/*
		 * Write a program that calculates and prints out three percentage values for 
		 * an integer read from the keyboard. The results are shown in this order: 
		 * 1) 95%, 2) 85% and 3) a percentage value read from the keyboard. 
		 * After that, the program then repeats the percentage calculations but this time, 
		 * treating the integer entered as a real number. 
		 * The program must be properly documented and facilitate user interaction.
		 */

		// data
		int integer, percentage3; // integer defines the integer value over which to calculate
		// the required percentages. Percentage3 defines the third percentage value, chosen by the user
		double real, value1, value2, value3; // real holds the real value (converted) for integer
		// value1, value2 y value3, are used to calculate percentage values 1, 2 and 3 respectively

		// instructions
		System.out.println("Introduce the integer number over which to calculate percentages");
		integer = KEYBOARD.nextInt();
		System.out.println("You have chosen the value " + integer);
		value1 = (integer * PERCENTAGE1) / 100;
		value2 = (integer * PERCENTAGE2) / 100;
		System.out.println("Introduce the value for the third percentage that will be calculated");
		percentage3 = KEYBOARD.nextInt();
		value3 = (integer * percentage3) / 100;

		System.out.println("The " + PERCENTAGE1 + "% of " + integer + " is: " + value1);
		System.out.println("The " + PERCENTAGE2 + "% of " + integer + " is: " + value2);
		System.out.println("The " + percentage3 + "% of " + integer + " is: " + value3);

		// get the value of integer as a real value
		real = integer;
		
		System.out.println("Calculations taking the chosen integer number as a real number: " + real);
		value1 = (real * PERCENTAGE1) / 100;
		value2 = (real * PERCENTAGE2) / 100;
		value3 = (real * percentage3) / 100;

		System.out.println("The " + PERCENTAGE1 + "% of " + real + " is: " + value1);
		System.out.println("The " + PERCENTAGE2 + "% of " + real + " is: " + value2);
		System.out.println("The " + percentage3 + "% of " + real + " is: " + value3);

	}
}
