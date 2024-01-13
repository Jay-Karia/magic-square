import java.util.*;

public class table {

  public static boolean check(int[][] table, int n) {
    int[] row = new int[n];
    int[] col = new int[n];
    boolean valid = false;
    // Calculate the products of rows and columns
    for (int i = 0; i < n; i++) {
      row[i] = 1;
      col[i] = 1;
      for (int j = 0; j < n; j++) {
        row[i] *= table[i][j];
        col[i] *= table[j][i];
      }
    }
    // Check if the products are equal
    for (int i = 0; i < row.length; i++) {
      if (row[i] != col[i]) return false;
    }

    return true;
  }

  public static void update(int[][] table, int n, int c) {
    System.out.println(c);
  }

  private static List<List<Integer>> generatePermutations(
    List<Integer> numbers
  ) {
    List<List<Integer>> permutations = new ArrayList<>();
    generatePermutationsHelper(numbers, 0, permutations);
    return permutations;
  }

  // Helper function for recursive permutation generation
  private static void generatePermutationsHelper(
    List<Integer> numbers,
    int start,
    List<List<Integer>> permutations
  ) {
    if (start == numbers.size() - 1) {
      permutations.add(new ArrayList<>(numbers));
      return;
    }
    
    for (int i = start; i < numbers.size(); i++) {
      // Swap elements to generate permutations
      Collections.swap(numbers, i, start);
      generatePermutationsHelper(numbers, start + 1, permutations);
      Collections.swap(numbers, i, start); // Undo swap for backtracking
    }
  }

  // Function to fill the matrix with a given permutation
  private static void fillMatrix(int[][] matrix, List<Integer> permutation) {
    int count = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = permutation.get(count++);
      }
    }
  }

  // Function to print the matrix
  private static void printMatrix(int[][] matrix) {
    for (int[] row : matrix) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Initialize variables
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number: ");
    int n = sc.nextInt();
    // int n = 3;
    int k = 1;
    boolean check = false;
    int p = 0;

    // Create a list of numbers from 1 to 25
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= n * n; i++) {
      numbers.add(i);
    }

    // Create table
    int[][] table = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        table[i][j] = k;
        k++;
      }
    }
    // Generate all permutations of the numbers
    List<List<Integer>> permutations = generatePermutations(numbers);

    for (List<Integer> permutation : permutations) {
      fillMatrix(table, permutation);
      check = check(table, n);
      if (check) {
        printMatrix(table);
        break;
      }
    }
  }
}
