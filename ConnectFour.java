package CSE114Final;

import java.util.Scanner;

public class ConnectFour {
	
	public static String[][] Board(){
		
		String[][] board = new String[7][15]; // counting dividers
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(j%2 == 0)
					board[i][j]="|";
				else
					board[i][j]=" ";
				if (i == 6)
					board[i][j]="_";
			}
		}
		return board;
	}
	
	public static void printBoard(String [][] board) {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void redPlayer(String [][] board) {
		System.out.print("Player 1, drop a red disk at column (0-6): ");
		Scanner input = new Scanner(System.in);
		try {
			
				int r = 2*input.nextInt()+1;
				for (int row = 5; row >=0; row--) {
					if (board[row][r] == " ") {
						board[row][r] = "R";
						break;
					}
				}
				
				// if column is filled, player loses a turn
				if (board[0][r] != " ") {
					System.out.println("This column is full! Better luck next time.");
				}
					
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("Invalid column! Please try again.");
			redPlayer(board);
		}
		
	}
	
	public static void yellowPlayer(String [][] board) {
		System.out.print("Player 2, drop a yellow disk at column (0-6): ");
		Scanner input = new Scanner(System.in);
		try {
			int y = 2*input.nextInt()+1;
			
			for (int row = 5; row >=0; row--) {
				if (board[row][y] == " ") {
					board[row][y] = "Y";
					break;
				}
			}
			
			// if column is filled, player loses a turn
			if (board[0][y] != " ") {
				System.out.println("This column is full! Better luck next time.");
			}		
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("Invalid column! Try again.");
			yellowPlayer(board);
		}
	
	}
	
	public static String isFull(String[][] board,int counter) {
		
		// checks to see whether board is completely filled
		if (counter == board.length*board[0].length)
			return "This game ends in a draw.";
		return null;
	}
	
	public static String Win(String [][] board) {
		
		// diagonal from upper left down to right
		for (int row = 0; row < 3; row++) { // goes through highest 3 rows b4 checking for diagonal
			for (int col = 1; col < 9; col+=2) { 
				if((board[row][col] != " ") &&
						(board[row+1][col+2] != " ") && // adjusts row and column so it doesn't read the | or _
						(board[row+2][col+4] != " " ) &&
						(board[row+3][col+6] != " ") &&
						((board[row][col] == board[row+1][col+2])&&
						(board[row+1][col+2] == board[row+2][col+4]) &&
						(board[row+2][col+4] == board[row+3][col+6]))){
							return board[row][col];
						}
			}
		}
		
		// diagonal from upper right down to left
		for (int i = 0; i < 3; i++) { // going through highest three rows b4 checking for diagonal
			for (int j = 7; j < 15; j+=2) { // adjusting col to something aside from the dividers of board
				if((board[i][j] != " ") &&
						(board[i+1][j-2] != " ") &&
						(board[i+2][j-4] != " " ) &&
						(board[i+3][j-6] != " ") &&
						((board[i][j] == board[i+1][j-2])&&
						(board[i+1][j-2] == board[i+2][j-4]) &&
						(board[i+2][j-4] == board[i+3][j-6]))){
							return board[i][j];
						}
			}
		}
		
		// horizontal
		for (int row = 0; row < 6; row++) { // loops through all rows besides last one (_)
			for (int col = 0; col < 7; col+=2) { 
				if((board[row][col+1] != " ") && // adjust columns to read through ones that aren't dividers
					(board[row][col+3] != " ") &&
					(board[row][col+5] != " " ) &&
					(board[row][col+7] != " ") &&
					((board[row][col+1] == board[row][col+3])&&
					(board[row][col+3] == board[row][col+5]) &&
					(board[row][col+5] == board[row][col+7]))){
						return board[row][col+1];
					}
			}
		}
		
		// vertical
		for (int i = 1; i < board[0].length; i+=2) { // go through columns
			for (int row = 0; row < 3; row++) { // adjust rows to skip over dividers
				if((board[row][i] != " ") &&
					(board[row+1][i] != " ") &&
					(board[row+2][i] != " " ) &&
					(board[row+3][i] != " ") &&
					((board[row][i] == board[row+1][i]) &&
					(board[row+1][i] == board[row+2][i]) &&
					(board[row+2][i] == board[row+3][i]))){
						return board[row][i];
					}
			}
		}
		return null;
		
		
	}
	public static void main(String[] args) {
		
		String[][] board = Board();
		System.out.println("Welcome to Connect4! This is best enjoyed with two players.");
		printBoard(board);
		
		boolean game = true;
		int counter = 0;
		
		while (game) {
			if (counter % 2 == 0)
				redPlayer(board);
			else
				yellowPlayer(board);
			counter++;
			
			printBoard(board);
			
			if (isFull(board,counter) != null)
				System.out.println(isFull(board,counter));
			
			if (Win(board) != null) {
				if (Win(board).equals("R"))
					System.out.println("Player 1 won.");
				else if (Win(board).equals("Y"))
					System.out.println("Player 2 won.");
				game = false;
			}
		}
		
	}
	
}

