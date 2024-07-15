import java.util.Scanner;

public class Assignment6 {
	
	final static Scanner KEYBOARD = new Scanner(System.in);

	/**
	 * // Read a positive number from the standard input
	 * @param message to be prompted when asking for the number to be read
	 * @return positive number read from standard input
	 */
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

	/**
	 * // Read a number within a range from the standard input
	 * @param message to be prompted when asking for the number to be read
	 * @param lowerBound of the range
	 * @param upperBound of the range
	 * @return number read from standard input that is within the range
	 */
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

	/**
	 * Print the contents of a vector (1-dimensional array) of boolean elements
	 * @param vector of boolean elements to be printed to the standard output
	 */
	public static void showVector(boolean[] vector) {

		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i] +"  ");
		}
		System.out.println();
	}

	/**
	 * // Create a matrix (2-dimensional array) of N rows by M columns of boolean elements
	 * El método devolverá la matriz creada
	 * @param rows, number of rows for the matrix
	 * @param columns, number of columns for the matrix
	 * @param b, value to initialize all the elements in the array
	 * @return the array created and initialized with the specified value
	 */
	private static boolean[][] createMatrix(int rows, int columns, boolean b) {
		boolean[][] matrix = new boolean[rows][columns];
		for (int row=0; row<rows; row++)
			for (int col=0; col<columns; col++)
				matrix[row][col]=b;
		return matrix;
	}
	
	/**
	 * Print the contents of a matrix (2-dimensional array) of boolean elements
	 * @param matrix of boolean elements to be printed to the standard output
	 */
	public static void showMatrix(boolean[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			System.out.print("Row "+ i + ": ");
			showVector(matrix[i]);
		}
	}

	/**
	 * Count the number of times a value appears in a matrix (2-dimensional array) of boolean elements
	 * @param matrix containing boolean elements to be check
	 * @param b, value to be searched and counted in the matrix
	 * @returns number of times that b appears in matrix
	 */
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
	
	public static void main(String[] args) {
		System.out.println("Program for assignment 6");
		String[] options= {"End program", "Ask Number in Range", "Ask Positive Number", "Show Vector", "Create and Show Matrix", "Count the Times a Value Appears in a Matrix"};
		boolean finish=false;
		do {
			finish=showMenu(options);
		}while (!finish);
		System.out.println("End of the program...");
	}
	
	public static void showOptions(String[] optionsText) {
		for (int pos=0; pos<optionsText.length; pos++) {
			System.out.println(pos+". "+optionsText[pos]);
		}
	}
	
	public static boolean showMenu(String[] optionsText){
		boolean finish=false;
		int option;
		boolean[][] m=null;
		showOptions(optionsText);
		option=askIntegerInRange("Enter the number for the desired option", 0, optionsText.length-1);
		switch (option) {
			case 0: finish=true;
					break;
			case 1:	System.out.println("Introduce the value for the lower bound");
					int lb=KEYBOARD.nextInt();
					System.out.println("Introduce the value for the upper bound");
					int ub=KEYBOARD.nextInt();
					int n1= askIntegerInRange("Introduce a value between "+lb+" and "+ub, lb,ub);
					System.out.println("You have entered the value "+n1);
					break;
			case 2: int n2= askPositiveInteger("Introduce  a positive value"); 
					System.out.println("You have entered the value "+n2);
					break;
			case 3: boolean[] v= {true, false, false, true, true};
					showVector(v);
					break;
			case 4: boolean vb=false;
					m=createMatrix(askPositiveInteger("Rows?"), askPositiveInteger("Columns?"), vb);
					showMatrix(m);
					break;
			case 5:	boolean b=true;
					if (m==null)
						m=createMatrix(askPositiveInteger("Rows?"), askPositiveInteger("Columns?"), b);
						int c=countValue(m, b);
						System.out.println("There are "+ c +" values equals to "+ b + "in the matrix");
					break;
			default:System.out.println("Wrong option");
		}
		return finish;
	}
}
