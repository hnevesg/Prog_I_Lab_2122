import java.util.Scanner;

public class Assignment7 {
	final static int SEATS_PER_ROW = 4;
	final static int MAX_TICKETS = 10;
	final static double DISCOUNT = 0.05;
	final static String[] MENU = {"Exit", "Ticket sales", "Check available seats", "Cancel tickets"};
	
	final static Scanner KEYBOARD = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("** Welcome to the ticket sales system **");
		int rows = askPositiveInteger("Enter the number of rows of the airplane: ");
		boolean[][] seats = createMatrix(rows, SEATS_PER_ROW, true); // available: true, taken: false
		int price = askPositiveInteger("Enter the single ticket price: ");
		mainMenu(seats, price);
	}

	private static void mainMenu(boolean[][] seats, int price) {
		boolean exit = false;
		do {
			showMenuOptions(MENU);
			int option = askIntegerInRange("Choose an option from the menu", 0, MENU.length-1);
			switch(option) {
			case 0:
				exit = true;
				break;
			case 1: 
				sellTickets(seats, price);
				break;
			case 2:
				checkSeatsAvailability(seats);
				break;
			case 3:
				cancelTicket(seats);
				break;
			default:
				System.out.println("Unknown option.");
			}
		} while (!exit);
	}
	
	public static void showMenuOptions(String[] texto) {
		for (int pos=0; pos<texto.length; pos++)
			System.out.println(pos+". "+texto[pos]);
	}

	private static void sellTickets(boolean[][] seats, int price) {
		int availableSeats = countValue(seats, true); //get count of available seats
		if(availableSeats > 0) {
			int max = (availableSeats > MAX_TICKETS) ? MAX_TICKETS : availableSeats;
			int tickets = askIntegerInRange("How many tickets do you want to buy?: ", 1, max);
			showMatrix(seats);
			int[] requestedSeatRows = new int[tickets];
			for(int i = 0; i < tickets; i++) {
				requestedSeatRows[i] = requestSeat(seats, i+1);
			}
			boolean applyDiscount = checkIfDiscountApplies(requestedSeatRows, seats.length);
			double finalPrice = calculateFinalPrice(tickets, price, applyDiscount);
			showFinalPrice(finalPrice, applyDiscount);
		} else {
			System.out.println("Sorry there are not enough available seats.");
		}
	}

	private static int requestSeat(boolean[][] seats, int seat) {
		boolean bought = false;
		int row;
		do {
			 row = askIntegerInRange("Enter row for requested seat" + seat + ": ", 0, seats.length-1);
			int column = askIntegerInRange("Enter row for requested seat " + seat + ":", 0, seats[0].length-1);
			if(seats[row][column]) {
				seats[row][column] = false;
				bought = true;
				System.out.printf("You have bought the seat %d in row %d\n", column, row);
			} else {
				System.out.printf("You cannot buy seat %d in row %d, it is already taken\n", column, row);
				System.out.println("Choose one among the available:");
				showMatrix(seats);
			}
		} while(!bought);
		return row;
	}

	private static boolean checkIfDiscountApplies(int[] requestedSeatRows, int numberOfRows) {
		boolean applyDiscount = false;
		int[] seatsInEachRow = new int[numberOfRows];
		// we check how many seats have been purchased in each row of the airplane
		// to do this, we traverse the vector with the requested seat rows of purchased tickets.
		// we have a vector of counters, where each position i indicates the number of seats purchased in row i.
		for(int i = 0; i < requestedSeatRows.length; i++) {
			seatsInEachRow[requestedSeatRows[i]]++;
		}
		for(int i = 0; i < seatsInEachRow.length; i++) {
			if(seatsInEachRow[i] == SEATS_PER_ROW) { // All 4 seats in a row have been bought
				applyDiscount = true;
			}
		}
		return applyDiscount;
	}

	private static double calculateFinalPrice(int tickets, int price, boolean applyDiscount) {
		double finalPrice = tickets * price;
		if(applyDiscount) {
			finalPrice = finalPrice * (1 - DISCOUNT);
		}
		return finalPrice;
	}

	private static void showFinalPrice(double finalPrice, boolean applyDiscount) {
		if(applyDiscount) {
			System.out.printf("The final price, including the discount for buying a whole row is: %.2f\n", finalPrice);
		} else {
			System.out.printf("The final price (discount does not apply) is: %.2f\n", finalPrice);
		}
	}

	private static void checkSeatsAvailability(boolean[][] seats) {
		int row = askIntegerInRange("Enter row for the seat to check: ", 0, seats.length-1);
		int column = askIntegerInRange("Enter column for the seat to check:", 0, seats[0].length-1);
		if(seats[row][column]) {
			System.out.printf("The seat %d in row %d is available\n", column, row);
		} else {
			System.out.printf("The seat %d in row %d is taken\n", column, row);
		}
	}

	private static void cancelTicket(boolean[][] seats) {
		int row = askIntegerInRange("Enter row for the seat to cancel: ", 0, seats.length-1);
		int column = askIntegerInRange("Enter column for the seat to cancel: ", 0, seats[0].length-1);
		if(seats[row][column]) {
			System.out.printf("Error, the seat %d in row %d is not taken\n", column, row);
		} else {
			seats[row][column] = true;
			System.out.printf("The seat %d in row %d has been cancelled\n", column, row);
		}
	}

	public static int askPositiveInteger(String message) {
		int num;
		System.out.print(message);
		num = KEYBOARD.nextInt();
		while (num<=0) {
			System.out.print("Error, you must enter a positive number. Try again... ");
			num = KEYBOARD.nextInt();
		}
		return num;
	}

	private static int askIntegerInRange(String message, int lowerBound, int upperBound) {
		int num=0;
		if (lowerBound > upperBound) 
			System.out.println("The values for the bounds define an empty range");
			else {
				System.out.printf(message + "(value in range [%d, %d]) ", lowerBound, upperBound);
				num = KEYBOARD.nextInt();
				while (num < lowerBound || num > upperBound) {
					System.out.printf("Error, you must enter a number in the range [%d, %d]. Try again... ", lowerBound, upperBound);
					num = KEYBOARD.nextInt();
				}
			}
		return num;
	}

	public static void showVector(boolean[] vector) {

		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i] +"  ");
		}
		System.out.println();
	}

	private static boolean[][] createMatrix(int rows, int columns, boolean b) {
		boolean[][] matrix = new boolean[rows][columns];
		for (int row=0; row<rows; row++)
			for (int col=0; col<columns; col++)
				matrix[row][col]=b;
		return matrix;
	}

	public static void showMatrix(boolean[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			System.out.print("Row "+ i + ": ");
			showVector(matrix[i]);
		}
	}

	private static int countValue(boolean[][] matrix, boolean b) {
		int count = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]==b) {
					count++;
				}
			}
		}
		return count;
	}
}
