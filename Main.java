import java.util.Scanner;

public class Main {
    static final int SIZE = 4;

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isSafe(char[][] grid, int row, int col, char num) {
        for (int i = 0; i < SIZE; ++i) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 2;
        int startCol = col - col % 2;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(char[][] grid) {
        for (int row = 0; row < SIZE; ++row) {
            for (int col = 0; col < SIZE; ++col) {
                if (grid[row][col] == '-') {
                    for (char num = '1'; num <= '4'; ++num) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num;

                            if (solveSudoku(grid)) {
                                return true;
                            }

                            grid[row][col] = '-';
                        }
                    }

                    return false;
                }
            }
        }

        // If grid is filled, return true
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[SIZE][SIZE];

        // Input
        for (int i = 0; i < SIZE; ++i) {
            String line = scanner.next();
            for (int j = 0; j < SIZE; ++j) {
                grid[i][j] = line.charAt(j);
            }
        }

        // Check if the grid is already filled
        boolean isFilled = true;
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (grid[i][j] == '-') {
                    isFilled = false;
                    break;
                }
            }
        }

        if (isFilled) {
            System.out.println("invalid");
        } else {
            if (solveSudoku(grid)) {
                // Print the solution
                printGrid(grid);
            } else {
                System.out.println("invalid");
            }
        }

        scanner.close();
    }
}