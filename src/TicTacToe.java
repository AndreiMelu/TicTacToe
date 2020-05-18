import org.apache.commons.lang.StringUtils;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    static Scanner in;
    static String[] board;
    static String turn;
    static String firstName;
    static String secondName;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;
        populateEmptyBoard();

        System.out.println("Welcome to 2 Players TicTacToe.");
        System.out.println("--------------------------------");
        getNames();

        printBoard();
        System.out.println(firstName + " will play first. Enter a slot number to place X in:");

        while (winner == null) {
            String numInput;
            numInput = in.next();
            if (StringUtils.isNumeric(numInput)) {
                if (!(Integer.parseInt(numInput) > 0 && Integer.parseInt(numInput) <= 9)) {
                    System.out.println("Invalid input; re-enter a slot number:");
                    continue;
                }
            } else {
                System.out.println("Please enter a numeric value!");
                continue;
            }
            if (board[Integer.parseInt(numInput) - 1].equals(numInput)) {
                board[Integer.parseInt(numInput) - 1] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            String player = winner.equals("X") ? firstName : secondName;
            System.out.println("Congratulations! " + player + " (" + winner + ") have won! Thanks for playing.");
        }
        in.close();
    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) return "draw";
        }
        String player = turn.equals("X") ? firstName : secondName;
        System.out.println(player + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    static void printBoard() {
        System.out.println("                           Available slots:");
        System.out.println("/---|---|---\\               /---|---|---\\");
        System.out.println("| " + printCell(board[0]) + " | " + printCell(board[1]) + " | " + printCell(board[2]) + " |"+
                "               | " + printExample(board[0]) + " | " + printExample(board[1]) + " | " + printExample(board[2]) + " |");
        System.out.println("|-----------|               |-----------|");
        System.out.println("| " + printCell(board[3]) + " | " + printCell(board[4]) + " | " + printCell(board[5]) + " |"+
                "               | " + printExample(board[3]) + " | " + printExample(board[4]) + " | " + printExample(board[5]) + " |");
        System.out.println("|-----------|               |-----------|");
        System.out.println("| " + printCell(board[6]) + " | " + printCell(board[7]) + " | " + printCell(board[8]) + " |"+
                "               | " + printExample(board[6]) + " | " + printExample(board[7]) + " | " + printExample(board[8]) + " |");

        System.out.println("/---|---|---\\               /---|---|---\\");
    }

    static void populateEmptyBoard() {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }

    static String printCell(String x) {
        return StringUtils.isNumeric(x) ? " " : x;
    }
    static String printExample(String x){
        return StringUtils.isNumeric(x)? x :" ";
    }

    static void getNames() {
        System.out.println("Please enter first player's name:");
        firstName = in.nextLine();
        System.out.println("Please enter second player's name: ");
        secondName = in.nextLine();
    }
}