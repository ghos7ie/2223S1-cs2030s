import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

/**
 * Lab9a is the main driver class for testing matrix multiplication.
 * Usage: java Lab9a
 */
class Lab9a {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    
    // Read matrix 1
    Matrix m1 = new Matrix(n);
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        m1.m[i][j] = scanner.nextDouble();
      }
    }
    
    // Read matrix 1
    Matrix m2 = new Matrix(n);
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        m2.m[i][j] = scanner.nextDouble();
      }
    }
    
    // Multiply matrices
    Matrix res = Matrix.parallelMultiply(m1, m2);
    System.out.println(res);
  }
}
