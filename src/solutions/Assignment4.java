import java.util.*;

public class Assignment4 {

//	Management of the purchase of tickets for a bus with a single seat per row

/*	Write a program that:
 *  - Reads the number of seats on a bus (N) and generates a vector of N boolean values (true/false). 
 *	- Next, the user enters the number of tickets to be purchased (M), or 0 to end the program 
 *	- The number of seats to be purchased M must be a value <= to the number of available seats Z, i.e. M <= Z. 
 *	- If there are M available seats, the program will displays the vector, sets the M seats to taken (false), and displays the vector again.
 *  - If there are not M available seats, it shall inform the user and display the content of the bus.
 *  - This process shall be repeated as long as the number of available seats is greater than 0.
 */

	final static Scanner KEYBOARD = new Scanner(System.in);

	public static void main(String[] args) {

		// DATA
		int totalSeats, tickets;
		boolean[] bus;
		int seat, availableSeatsCount;
		boolean finishProgram; // controls the main loop of the application

		// INSTRUCTIONS

		// **** GET DATA AND CHECK IT IS CORRECT ****

		// Get number of seats for the bus
		System.out.println("Introduce the number of seats for the bus  ");
		totalSeats = KEYBOARD.nextInt();

		// Check the value is correct
		while (totalSeats <= 0) {
			System.out.println("Error, it must be a positive number. Try again...");
			totalSeats = KEYBOARD.nextInt();
		}

		// **** INITIALIZE ALL DATA ****
		// Create the vector that represents the bus
		bus = new boolean[totalSeats];

		// Initialize values in the vector to true (at the start every seat is available)
		for (int i = 0; i < bus.length; i++) {
			bus[i] = true;
		}

		// Initialize the counter of available seats (same as total seats in the beginning)
		availableSeatsCount = totalSeats;
		// At the start finishProgram takes value false
		finishProgram = false;

		// **** MAIN LOOP CONTROLLING THE TICKET SALE PROCESS ****//

		do {

			// Get the number of tickets to buy
			do {
				System.out.printf("Introduce the number of tickets to buy (between 1 and %d). Introduce 0 to finish the program ",
						availableSeatsCount);
				tickets = KEYBOARD.nextInt();
			} while (tickets < 0 || tickets > availableSeatsCount);

			// Check if the user wants to finish the program
			if (tickets == 0) // Yes
				finishProgram = true;
			else { // No, and requested number of tickets is <= than available seats

				// show state of the bus and proceed to sell tickets

				System.out.println("\nThere are enough available seats. The availability in the bus is as follows:");
				for (int i = 0; i < bus.length; i++) {
					System.out.printf("%6s ", bus[i]);
				}
				System.out.println();

				// Iterate over the vector to set the required seats as taken (set value to false) 
				// and update the available seats counter
				seat = 0;
				while (seat < totalSeats && tickets > 0) {
					if (bus[seat]) {
						availableSeatsCount--;
						tickets--;
						bus[seat] = false;
					}
					seat++;
				}

				// show state of the bus after the sale
				System.out.println("\nThe availability in the bus after the sale is as follows:");
				for (int j = 0; j < bus.length; j++) {
					System.out.printf("%6s ", bus[j]);
				}
				System.out.println();

				// Check if there are still available seats. Otherwise, the main loop ends.
				if (availableSeatsCount == 0) {
					finishProgram = true;
					System.out.println("All seats are taken. The ticket sale process will end.");
				}
					
			}
		} while (!finishProgram);

		// **** END OF THE PROGRAM ****//
		System.out.println("\n***** End of the program... ******");
	}
}