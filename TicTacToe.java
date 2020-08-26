import java.lang.reflect.Array;
import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);
        while (true) {

            System.out.println("Enter your move (1-9)");
            int position = scanner.nextInt();
            if (playerPositions.size() == 9 || computerPositions.size() == 9) {
                printGameBoard(gameBoard);
                System.out.println("DRAW");
                break;
            }
            while (playerPositions.contains(position) || computerPositions.contains(position)){
                System.out.println("Position is already taken!");
                position = scanner.nextInt();
            }
            placePiece(gameBoard, position, "Player");
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                printGameBoard(gameBoard);
                break;
            }

            Random rnd = new Random();
            int computerPos = rnd.nextInt(9) + 1;
            while (playerPositions.contains(computerPos) || computerPositions.contains(computerPos)) {
                computerPos = rnd.nextInt(9) + 1;
            }
            placePiece(gameBoard, computerPos, "Computer");
            printGameBoard(gameBoard);
            result = checkWinner();
            if (result.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }

    }


    public static void printGameBoard(char[][] gameBoard) {
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("Player")) {
            playerPositions.add(pos);
            symbol = 'X';
        } else {
            computerPositions.add(pos);
            symbol = 'O';
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List list : winning) {
            if (playerPositions.containsAll(list)) {
                return "Congratulations, you won!";
            } else if (computerPositions.containsAll(list)) {
                return "You can't beat the machines!";
            }
        }
        return "";
    }

}

