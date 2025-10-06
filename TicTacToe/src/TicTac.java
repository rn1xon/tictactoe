import java.util.Arrays;
import java.util.Scanner;

public class TicTac {

    public static int rows = 3;
    public static int cols = 3;

    public static char[][] createBoard() {

        char[][] board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static Boolean decideWhoFirst() {
        int randomNum = (int)(Math.random()*2);
        Boolean isPlayerXTurn;

        if (randomNum == 0) {
            System.out.println("Player O will start...\n");
            isPlayerXTurn = false;
            return isPlayerXTurn;
        } else {
            System.out.println("Player X will start...\n");
            isPlayerXTurn = true;
            return isPlayerXTurn;
        }
    }

    public static char[][] updateBoard(char[][] currentBoard, char newValue, int row, int col) {
        currentBoard[row][col] = newValue;
        return currentBoard;
    }

    public static void displayBoard(char[][] currentBoard) {
        for (char[] row : currentBoard) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int getPlayerTurn(Boolean isRow, Scanner scanner) {
        String rowOrCol;
        int rowOrColVal;

        if (isRow) {
            rowOrCol = "row";
        } else {
            rowOrCol = "column";
        }

        System.out.println("Enter the " + rowOrCol + " you'd like to play in: ");
        rowOrColVal = scanner.nextInt();

        if (rowOrColVal != 1 && rowOrColVal != 2 && rowOrColVal != 3) {
            System.out.println("Not a valid " + rowOrCol + " number, please try again...");
            System.out.println("Enter the " + rowOrCol + " you'd like to play in: ");
            rowOrColVal = scanner.nextInt();
        }

        return rowOrColVal - 1; // Convert to 0-based index
    }

public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
        char[][] board = createBoard();
        System.out.println("Welcome to TicTacToe!");
        char value;
        Boolean isPlayerXTurn = decideWhoFirst();
        value = isPlayerXTurn ? 'X' : 'O';

        boolean gameOver = false;

        while (!gameOver) {
            displayBoard(board);
            int row = getPlayerTurn(true, scanner);
            int col = getPlayerTurn(false, scanner);

            if (board[row][col] == ' ') {
                board[row][col] = value;

                if (checkWin(board, value, rows, cols)) {
                    displayBoard(board);
                    System.out.println("Player " + value + " wins!");
                    gameOver = true;
                } else if (isBoardFull(board)) {
                    displayBoard(board);
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    isPlayerXTurn = !isPlayerXTurn;
                    value = isPlayerXTurn ? 'X' : 'O';
                }
            } else {
                System.out.println("That spot is taken. Try again.");
            }
        }

    } 
}

    private static boolean checkWin(char[][] board, char value, int rows, int cols) {
       
        // Rows
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == value && 
                board[i][1] == value &&
                board[i][2] == value) {
                    return true;
            }
        }

        // Columns
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == value && 
                board[1][j] == value &&
                board[2][j] == value) {
                    return true;
            }
        }

        // Diagonals
        if (board[0][0] == value && 
            board [1][1] == value && 
            board[2][2] == value) {
                return true;
        }

        if (board[0][2] == value && 
            board [1][1] == value && 
            board[2][0] == value) {
                return true;
        }

        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
