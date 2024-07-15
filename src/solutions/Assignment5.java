import java.util.*;

public class Assignment5 {
	// Management of the purchase of tickets for a bus with several seats per row.

	/*
	 * Write a program that reads the number of rows on a bus (R) and number of seats per row (C) and generates a two-dimensional array of RxC boolean values (true/false), 
	 * where the value true for a position in the array represents that this seat is available and the value false represents that it is taken.
	 * Next, the ticket sale process is carried out, which works as follows:
	 * The user enters the number of tickets to be purchased (M), or the value 0 to finish the program. 
	 * If M <= C, the program will check if there are M available seats in the same row of the bus (M elements in a same row in the array with value true). If so, it will show in which one.
	 * Otherwise (M > C or no row with M available seats), it will check if there are M available seats on the bus (in different rows). 
	 * In both cases, the program will display the bus, then set the M seats to taken (false), and finally display the bus again.
	 * If there are not M available seats, the program inform the user and display the content of the bus.
	 * This process shall be repeated as long as the number of available seats is greater than 0.
	 */

	final static Scanner KEYBOARD = new Scanner(System.in);

	public static void main(String[] args) {

		int rows, columns, tickets, availableSeatsCount;
		boolean[][] bus;
		boolean finishProgram = false;

		// Ask number of rows and columns (seats per row)
		System.out.println("Introduce the number of rows for the bus ");
		rows = KEYBOARD.nextInt();
		while (rows <= 0) {
			System.out.println("Error, it must be a positive number. Try again...");
			rows = KEYBOARD.nextInt();
		}
		System.out.println("Introduce the number of seats per row ");
		columns = KEYBOARD.nextInt();
		while (columns <= 0) {
			System.out.println("Error, it must be a positive number. Try again...");
			columns = KEYBOARD.nextInt();
		}

		final int NUM_SEATS = rows * columns;
		availableSeatsCount = NUM_SEATS;

		// Create the array that represents the bus
		bus = new boolean[rows][columns];

		// Initialize values in the vector to true (at the start every seat is
		// available)
		for (int i = 0; i < bus.length; i++) {
			for (int j = 0; j < bus[0].length; j++) {
				bus[i][j] = true;
			}
		}
		// Get the number of tickets to buy
		do {
			do {
				System.out.printf("Introduce the number of tickets to buy (between 1 and %d). Introduce 0 to finish the program ",
						availableSeatsCount);
				tickets = KEYBOARD.nextInt();
				if(tickets > availableSeatsCount) {
					System.out.println("There are not enough available seats for the number of tickets requested");

					// Show the bus
					System.out.println("The availability in the bus is as follows:");
					for (int i = 0; i < bus.length; i++) {
						for (int j = 0; j < bus[0].length; j++) {
							System.out.printf("%6s ", bus[i][j]);
						}
						System.out.println();
					}
					System.out.println();
				}
			} while (tickets < 0 || tickets > availableSeatsCount);

			// Check if the user wants to finish the program
			if (tickets == 0) // Yes
				finishProgram = true;
			else { // No, and requested number of tickets is <= than available seats

				// Check if there is a row with enough seats
				boolean enoughInSameRow = false;
				if (tickets <= columns) { // Check if tickets is <= than seats per row

					int row = 0, seatsAvailableInRow = 0, availableRow = 0, column;
					while (seatsAvailableInRow < tickets && row < rows) {
						column = 0;
						seatsAvailableInRow = 0;
						while (seatsAvailableInRow < tickets && column < columns) {
							if (bus[row][column]) {
								seatsAvailableInRow++;
							}
							column++;
						}
						row++;
					}
					if (seatsAvailableInRow == tickets) { // If there are enough seats in the row, show the bus, update values and show bus again
						enoughInSameRow = true;
						availableRow = row - 1;
						System.out.println("The row " + availableRow + " has enoguh seats available for the tickets requested");

						// Show bus
						System.out.println("The availability in the bus before the sale is as follows: ");
						for (int i = 0; i < bus.length; i++) {
							for (int j = 0; j < bus[0].length; j++) {
								System.out.printf("%6s ", bus[i][j]);
							}
							System.out.println();
						}
						System.out.println();
						// Update values for seats
						int seatsSold = 0;
						for (int j = 0; j < bus[0].length; j++) {
							if (bus[availableRow][j] && seatsSold < tickets) {
								bus[availableRow][j] = false;
								seatsSold++;
								availableSeatsCount--;
							}
						}
						// Show bus
						System.out.println("The availability in the bus after the sale is as follows:");
						for (int i = 0; i < bus.length; i++) {
							for (int j = 0; j < bus[0].length; j++) {
								System.out.printf("%6s ", bus[i][j]);
							}
							System.out.println();
						}
						System.out.println();
					} else
						System.out.println("There is not an available row for the requested tickets");
				} else
					System.out.println("The number of tickets chosen requires more than one row");

				// take seats in different rows 

				if (!enoughInSameRow) { // || tickets > columns 
					System.out.printf("There are enough available seats for the %d tickets requested \n", tickets);

					// Show bus
					System.out.println("The availability in the bus before the sale is as follows:");
					for (int i = 0; i < bus.length; i++) {
						for (int j = 0; j < bus[0].length; j++) {
							System.out.printf("%6s ", bus[i][j]);
						}
						System.out.println();
					}
					System.out.println();

					// Update status of seats that have been bought

					int row = 0;
					int updatedSeats = 0;
					while (updatedSeats < tickets && row < rows) {
						int column = 0;
						while (updatedSeats < tickets && column < columns) {
							if (bus[row][column]) {
								bus[row][column] = false;
								updatedSeats++;
								availableSeatsCount--;
							}
							column++;
						}
						if (column == columns)
							row++;
					}

					// Show bus
					System.out.println("The availability in the bus after the sale is as follows:");
					for (int i = 0; i < bus.length; i++) {
						for (int j = 0; j < bus[0].length; j++) {
							System.out.printf("%6s ", bus[i][j]);
						}
						System.out.println();
					}
					System.out.println();

				}
				// Check if there are still available seats. Otherwise, the main loop ends.
				if (availableSeatsCount == 0) {
					finishProgram = true;
					System.out.println("All seats are taken. The ticket sale process will end.");
				}
			} 
		} while (!finishProgram);
		System.out.println("\n***** End of the program... ******");
	}

}