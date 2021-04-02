package game;

import java.util.ArrayList;
import java.util.Scanner;

public class sad {
	public static String[][] gameBoard = new String[3][3];
	public static String xMarker = "X";
	public static String oMarker = "O";
	public static String currentPlayer = "X";
	public static Scanner scanner = new Scanner(System.in);
	public static boolean running = true;

	public static void main(String args[]) {
		// making the game board empty and not null
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				gameBoard[i][k] = " ";
			}
		}
		while (running) {
			printBoard();
			System.out.println("Enter the coordinates: ");

			String input = scanner.nextLine();
			String[] splitter = input.split(" ");
			int x = 0; // just for initialization
			int y = 0;
			try {// will check for incorrect input
				x = Integer.parseInt(splitter[0]);
				y = Integer.parseInt(splitter[1]);
			} catch (NumberFormatException ex) {
				System.out.println("You should enter numbers!");
				continue;
			}
			if (x < 1 || x > 3 || y < 1 || y > 3) { // checking for outOfbounds coords.
				System.out.println("Coordinates should be from 1 to 3!");
				continue;
			}

			// after input values are valid, now we check for space availability
			if (!openSpaceCheck(x, y)) {
				System.out.println("This cell is occupied! Choose another one!");
				continue;
			}
			if (openSpaceCheck(x, y)) {
				gameBoard[Math.abs(3 - y)][Math.abs(1 - x)] = currentPlayer;
				printBoard();
				checkForWin();
			}
			if (currentPlayer.equals("X")) {
				currentPlayer = "O";
				continue;
			}
			if (currentPlayer.equals("O")) {
				currentPlayer = "X";
				continue;
			}

		}
	}

	public static boolean openSpaceCheck(int x, int y) {
		if (gameBoard[Math.abs(3 - y)][Math.abs(1 - x)].equals(" ")) {

			return true;
		}
		if (gameBoard[Math.abs(3 - y)][Math.abs(1 - x)].equals("X")
				|| gameBoard[Math.abs(3 - y)][Math.abs(1 - x)].equals("O")) {

			return false;
		}
		return true;
	}

	public static void printBoard() {
		System.out.println("---------");
		System.out.println("| " + gameBoard[0][0] + " " + gameBoard[0][1] + " " + gameBoard[0][2] + " |");
		System.out.println("| " + gameBoard[1][0] + " " + gameBoard[1][1] + " " + gameBoard[1][2] + " |");
		System.out.println("| " + gameBoard[2][0] + " " + gameBoard[2][1] + " " + gameBoard[2][2] + " |");
		System.out.println("---------");
	}

	public static void checkForWin() {
		int topRow = gameBoard[0][0].charAt(0) + gameBoard[0][1].charAt(0) + gameBoard[0][2].charAt(0);
		int midRow = gameBoard[1][0].charAt(0) + gameBoard[1][1].charAt(0) + gameBoard[1][2].charAt(0);
		int botRow = gameBoard[2][0].charAt(0) + gameBoard[2][1].charAt(0) + gameBoard[2][2].charAt(0);
		int firstDiag = gameBoard[0][0].charAt(0) + gameBoard[1][1].charAt(0) + gameBoard[2][2].charAt(0);
		int secondDiag = gameBoard[2][0].charAt(0) + gameBoard[1][1].charAt(0) + gameBoard[0][2].charAt(0);
		int firstColumn = gameBoard[0][0].charAt(0) + gameBoard[1][0].charAt(0) + gameBoard[2][0].charAt(0);
		int secondColumn = gameBoard[0][1].charAt(0) + gameBoard[1][1].charAt(0) + gameBoard[2][1].charAt(0);
		int thirdColumn = gameBoard[0][2].charAt(0) + gameBoard[1][2].charAt(0) + gameBoard[2][2].charAt(0);

		ArrayList<Integer> lines = new ArrayList<>();
		lines.add(topRow);
		lines.add(midRow);
		lines.add(botRow);
		lines.add(firstDiag);
		lines.add(secondDiag);
		lines.add(firstColumn);
		lines.add(secondColumn);
		lines.add(thirdColumn);

		int xPoints = 0;
		int yPoints = 0;

		for (int lineBytes : lines) {
			if (lineBytes == 264) {
				xPoints++;
			}
			if (lineBytes == 237) {
				yPoints++;
			}
		}

		// other
		boolean isItPossible = true;
		int xcounter = 0;
		int ycounter = 0;

		for (String[] row : gameBoard) {
			for (String markers : row) {
				if (markers.equals("X")) {
					xcounter++;
				}
				if (markers.equals("O")) {
					ycounter++;
				}
			}
		}
		if (Math.abs(xcounter - ycounter) != 1 && Math.abs(xcounter - ycounter) != 0) {
			isItPossible = false;

		}
		// winnning conditions
		if (xPoints == 1 && yPoints == 0) {
			System.out.println("X wins");
			scanner.close();
			running = false;
		}
		if (yPoints == 1 && xPoints == 0) {
			System.out.println("O wins");
			scanner.close();
			running = false;
		}
		if (yPoints == 0 && xPoints == 0) {
			boolean done = true;
			for (String[] row : gameBoard) {
				for (String markers : row) {
					if (markers.equals(" ")) {
						done = false;
					}
				}
			}
			if (done) {
				if (isItPossible) {
					System.out.println("Draw");
					scanner.close();
					running = false;
				}
			}
		}
	}
}
