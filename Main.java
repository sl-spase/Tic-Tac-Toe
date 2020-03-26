package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> xPosition = new ArrayList<>();
    static ArrayList<Integer> oPosition = new ArrayList<>();
    public static boolean playGame = true;
    public static String[][] array = new String[3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArr = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
        fillArray(array, inputArr);

        boolean playerX = true;

        String symbol;
        int diagonally = 0;
        int vertical = 0;

        while (playGame) {

            draw(array);

            String[] position;

            boolean isNumber = true;
            int pos = 0;
            while (isNumber) {
                try {
                    System.out.print("Enter the coordinates: ");
                    position = scanner.nextLine().split(" ");
                    diagonally = Integer.parseInt(position[0]);
                    vertical = Integer.parseInt(position[1]);
                    pos = position(diagonally, vertical, pos);
                    if (diagonally < 1 || diagonally > 3 ||
                            vertical < 1 || vertical > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (xPosition.contains(pos) || oPosition.contains(pos)) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        pos = position(diagonally, vertical, pos);
                        isNumber = false;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("You should enter numbers!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You should enter two numbers with space between!");
                }

            }

            symbol = switchXO(playerX, pos);

            fillSymbol(symbol, diagonally, vertical);

            playerX = isPlayerX(playerX);

            System.out.println(checkWinner());
        }

    }

    private static String switchXO(boolean playerX, int pos) {
        String symbol;
        if (playerX) {
            symbol = "X";
            xPosition.add(pos);
        } else {
            symbol = "O";
            oPosition.add(pos);
        }
        return symbol;
    }


    private static boolean isPlayerX(boolean playerX) {
        if (playerX) {
            playerX = false;
        } else {
            playerX = true;
        }
        return playerX;
    }


    private static void fillSymbol(String symbol, int diagonally, int vertical) {
        if (diagonally == 1 && vertical == 1) {
            array[2][0] = symbol;
        } else if (diagonally == 1 && vertical == 2) {
            array[1][0] = symbol;
        } else if (diagonally == 1 && vertical == 3) {
            array[0][0] = symbol;
        } else if (diagonally == 2 && vertical == 1) {
            array[2][1] = symbol;
        } else if (diagonally == 2 && vertical == 2) {
            array[1][1] = symbol;
        } else if (diagonally == 2 && vertical == 3) {
            array[0][1] = symbol;
        } else if (diagonally == 3 && vertical == 1) {
            array[2][2] = symbol;
        } else if (diagonally == 3 && vertical == 2) {
            array[1][2] = symbol;
        } else if (diagonally == 3 && vertical == 3) {
            array[0][2] = symbol;
        }
    }


    private static int position(int diagonally, int vertical, int pos) {
        if (diagonally == 1 && vertical == 1) {
            pos = 7;
        } else if (diagonally == 1 && vertical == 2) {
            pos = 4;
        } else if (diagonally == 1 && vertical == 3) {
            pos = 1;
        } else if (diagonally == 2 && vertical == 1) {
            pos = 8;
        } else if (diagonally == 2 && vertical == 2) {
            pos = 5;
        } else if (diagonally == 2 && vertical == 3) {
            pos = 2;
        } else if (diagonally == 3 && vertical == 1) {
            pos = 9;
        } else if (diagonally == 3 && vertical == 2) {
            pos = 6;
        } else if (diagonally == 3 && vertical == 3) {
            pos = 3;
        }
        return pos;
    }

    private static void fillArray(String[][] array, String[] inputArr) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = inputArr[count];
                count++;
            }
        }
    }

    private static void draw(String[][] array) {
        System.out.println("-----------");
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("| %s %s %s |", array[i][0], array[i][1], array[i][2]));
        }
        System.out.println("-----------");
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (xPosition.containsAll(l)) {
                draw(array);
                playGame = false;
                return "X wins";
            } else if (oPosition.containsAll(l)) {
                draw(array);
                playGame = false;
                return "O wins";
            }
        }

        if (xPosition.size() + oPosition.size() == 9) {
            draw(array);
            playGame = false;
            return "Draw";
        }
        return "";
    }
}
