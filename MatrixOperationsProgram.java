import java.util.Arrays;
import java.util.Scanner;

/**
 * MatrixOperations Program
 * Author: Igas
 * Description: A formal, menu-driven Java application for performing standard matrix operations.
 */
public class MatrixOperationsProgram {

    // === HELPER METHODS ===

    /**
     * Displays the matrix in a formatted grid layout.
     */
    public static void displayMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Matrix is empty.");
            return;
        }
        int cols = matrix[0].length;
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
        for (int i = 0; i < cols * 7; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prompts the user to initialize a matrix with integer elements.
     * Returns null if input is invalid or cancelled.
     */
    public static int[][] inputMatrix(Scanner scanner) {
        try {
            System.out.print("Enter number of rows: ");
            int rows = scanner.nextInt();
            System.out.print("Enter number of columns: ");
            int cols = scanner.nextInt();
            if (rows <= 0 || cols <= 0) {
                System.out.println("Dimensions must be positive integers.");
                return null;
            }

            int[][] matrix = new int[rows][cols];
            System.out.printf("Please enter %d integer elements row by row:%n", rows * cols);
            for (int i = 0; i < rows; i++) {
                System.out.printf("Row %d (%d elements separated by spaces): ", i + 1, cols);
                for (int j = 0; j < cols; j++) {
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                    } else {
                        System.out.println("Error: Invalid integer detected. Initialization aborted.");
                        scanner.next(); // clear invalid token
                        return null;
                    }
                }
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid integers.");
            return null;
        }
    }

    // === MATRIX OPERATIONS ===

    public static int[][] sortRowWise(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i].clone();
            Arrays.sort(result[i]);
        }
        return result;
    }

    public static int[][] sortColWise(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            int[] col = new int[rows];
            for (int i = 0; i < rows; i++) {
                col[i] = matrix[i][j];
            }
            Arrays.sort(col);
            for (int i = 0; i < rows; i++) {
                result[i][j] = col[i];
            }
        }
        return result;
    }

    /**
     * Rotates each row cyclically to the right by 1 position.
     */
    public static int[][] rotateClockwise1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][(j + 1) % cols] = matrix[i][j];
            }
        }
        return result;
    }

    /**
     * Rotates each row cyclically to the left by 1 position.
     */
    public static int[][] rotateCounterClockwise1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][(j - 1 + cols) % cols] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][] rotate90(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][] rotate180(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j];
            }
        }
        return result;
    }

    public static void traverseRowWise(int[][] matrix) {
        System.out.print("Row-wise traversal: ");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }

    public static void traverseColWise(int[][] matrix) {
        System.out.print("Column-wise traversal: ");
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void printSpiral(int[][] matrix) {
        System.out.print("Spiral traversal: ");
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) System.out.print(matrix[top][i] + " ");
            top++;
            for (int i = top; i <= bottom; i++) System.out.print(matrix[i][right] + " ");
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) System.out.print(matrix[bottom][i] + " ");
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) System.out.print(matrix[i][left] + " ");
                left++;
            }
        }
        System.out.println();
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    // === MAIN EXECUTION LOOP ===

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = null;

        for (int i = 0; i < 40; i++) System.out.print("=");
        System.out.println();
        System.out.println("       MATRIX OPERATIONS PROGRAM");
        for (int i = 0; i < 40; i++) System.out.print("=");
        System.out.println();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1-a. Sort the matrix row-wise");
            System.out.println("1-b. Sort the matrix column-wise");
            System.out.println("2-a. Rotate Matrix Clockwise by 1");
            System.out.println("2-b. Rotate Matrix Counter-Clockwise by 1");
            System.out.println("2-c. Rotate a matrix by 90");
            System.out.println("2-d. Rotate a matrix by 180");
            System.out.println("3-a. Row-wise traversal of matrix");
            System.out.println("3-b. Column-wise traversal of matrix");
            System.out.println("4. Print matrix in spiral form");
            System.out.println("5. Transpose matrix");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.next().trim().replace("-", "").toLowerCase();
            if (choice.equals("6")) {
                System.out.println("Program terminated. Thank you for using the application.");
                break;
            }

            if (matrix == null) {
                System.out.println("\nNo matrix initialized. Please create one first.");
                matrix = inputMatrix(scanner);
                if (matrix == null) {
                    continue;
                }
            }

            try {
                switch (choice) {
                    case "1a":
                        matrix = sortRowWise(matrix);
                        System.out.println("\nMatrix sorted row-wise:");
                        displayMatrix(matrix);
                        break;
                    case "1b":
                        matrix = sortColWise(matrix);
                        System.out.println("\nMatrix sorted column-wise:");
                        displayMatrix(matrix);
                        break;
                    case "2a":
                        matrix = rotateClockwise1(matrix);
                        System.out.println("\nMatrix rotated clockwise by 1:");
                        displayMatrix(matrix);
                        break;
                    case "2b":
                        matrix = rotateCounterClockwise1(matrix);
                        System.out.println("\nMatrix rotated counter-clockwise by 1:");
                        displayMatrix(matrix);
                        break;
                    case "2c":
                        matrix = rotate90(matrix);
                        System.out.println("\nMatrix rotated by 90 degrees:");
                        displayMatrix(matrix);
                        break;
                    case "2d":
                        matrix = rotate180(matrix);
                        System.out.println("\nMatrix rotated by 180 degrees:");
                        displayMatrix(matrix);
                        break;
                    case "3a":
                        traverseRowWise(matrix);
                        break;
                    case "3b":
                        traverseColWise(matrix);
                        break;
                    case "4":
                        printSpiral(matrix);
                        break;
                    case "5":
                        matrix = transpose(matrix);
                        System.out.println("\nMatrix transposed:");
                        displayMatrix(matrix);
                        break;
                    default:
                        System.out.println("Invalid selection. Please refer to the menu options.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                matrix = null;
            }
        }
        scanner.close();
    }
}