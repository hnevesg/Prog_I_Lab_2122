/**
 * 
 */
import java.util.*;

public class Assignment2 {

	// SELECTION STATEMENTS

	/*
	 * For a C Tangana concert, M tickets have been put on sale at 33 euros each. 
	 * Write a program that calculates the purchase price of N tickets, taking into account that, 
	 * if the number of tickets to be purchased is greater than a certain quantity K1, a discount of 5% is given; 
	 * and, if it is greater than another quantity K2, with K2>K1, the discount is 15%.
	 * For this, the following restrictions must be taken into account: 
	 * The values corresponding to M, N, K1 and K2 will be read from the keyboard.
	 * If at any time that data must be read from the keyboard, the user enters an incorrect value, 
	 * the program will display an error message and terminate its execution. 
	 * 
	 */

	final static Scanner KEYBOARD = new Scanner(System.in);

	final static double DISCOUNT1 = 5;
	final static double DISCOUNT2 = 15;
	final static double PRICE = 33;

	public static void main(String[] args) {

		// DATA
		int availableTickets, ticketsBought, ticketsForDiscount1, ticketsForDiscount2;
		double price;

		// INSTRUCTIONS
		System.out.print("Introduce the number of tickets you want to offer for sale... ");
		availableTickets = KEYBOARD.nextInt();

		if (availableTickets <= 0) {
			System.out.println("Error. Since it is not a positive number, the program will finish...");
		} else {// numberTickets > 0
			System.out.print("Introduce the number of tickets required to get the maximum discount. It must be a value smaller than "
							+ availableTickets + "...");
			ticketsForDiscount2 = KEYBOARD.nextInt();
			if (ticketsForDiscount2 >= availableTickets) {
				System.out.println("Error. The value cannot be greater than or equals to " + availableTickets
						+ ". The program will finish...");
			} else {// ticketsWithDiscount2 < numberTickets
				System.out.print("Introduce the number of tickets required to get the smaller discount. It must be a value smaller than "
								+ ticketsForDiscount2 + "...");
				ticketsForDiscount1 = KEYBOARD.nextInt();
				if (ticketsForDiscount1 >= ticketsForDiscount2) {
					System.out.println("Error. The value cannot be greater than or equals to " + ticketsForDiscount2
							+ ". The program will finish...");
				}else {// ticketsWithDiscount1 < ticketsWithDiscount2 < numberTickets
					System.out.println("The price for each ticket is " + PRICE + "€");
					System.out.print("How many tickets do you want to buy? It must be a value smaller than or equals to " + availableTickets + " ... ");
					ticketsBought = KEYBOARD.nextInt();
					if (ticketsBought > availableTickets)
						System.out.println("Error. The value cannot be greater than " + availableTickets
								+ ". The program will finish...");

					else {// all data entered was correct. Calculate price of the tickets applying discount
						price = PRICE * ticketsBought;

						// check if discount can be applied and which one
						if (ticketsBought >= ticketsForDiscount1 && ticketsBought < ticketsForDiscount2) {
							System.out.print("Since the number of tickets is bigger than " + ticketsForDiscount1
									+ " but smaller than " + ticketsForDiscount2 + " ");
							System.out.println("a discount of " + DISCOUNT1 + "% will be applied.");
							price = price * (100 - DISCOUNT1) / 100;
						} else if (ticketsBought >= ticketsForDiscount2) {
							System.out.print("Since the number of tickets is bigger than " + ticketsForDiscount2 + " ");
							System.out.println("a discount of " + DISCOUNT2 + "% will be applied.");
							price = price * (100 - DISCOUNT2) / 100;
						}

						// finally, calculate the total price 
						System.out.println("The total price for the " + ticketsBought + " tickets is: " + price + " euros.");
					}
				}
			}
		}
		System.out.println("End of the program...");
	}// main
}// program
