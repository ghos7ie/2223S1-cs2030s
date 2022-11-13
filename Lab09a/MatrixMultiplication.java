import java.util.concurrent.RecursiveTask;

class MatrixMultiplication extends RecursiveTask<Matrix> {

  /** The fork threshold. */
  private static final int FORK_THRESHOLD = 128; // Find a good threshold

  /** The first matrix to multiply with. */
  private final Matrix m1;

  /** The second matrix to multiply with. */
  private final Matrix m2;

  /** The starting row of m1. */
  private final int m1Row;

  /** The starting col of m1. */
  private final int m1Col;

  /** The starting row of m2. */
  private final int m2Row;

  /** The starting col of m2. */
  private final int m2Col;

  /**
   * The dimension of the input (sub)-matrices and the size of the output
   * matrix.
   */
  private int dimension;

  /**
   * A constructor for the Matrix Multiplication class.
   * 
   * @param m1        The matrix to multiply with.
   * @param m2        The matrix to multiply with.
   * @param m1Row     The starting row of m1.
   * @param m1Col     The starting col of m1.
   * @param m2Row     The starting row of m2.
   * @param m2Col     The starting col of m2.
   * @param dimension The dimension of the input (sub)-matrices and the size
   *                  of the output matrix.
   */
  MatrixMultiplication(Matrix m1, Matrix m2, int m1Row, int m1Col, int m2Row,
      int m2Col, int dimension) {
    this.m1 = m1;
    this.m2 = m2;
    this.m1Row = m1Row;
    this.m1Col = m1Col;
    this.m2Row = m2Row;
    this.m2Col = m2Col;
    this.dimension = dimension;
  }

  @Override
  public Matrix compute() {    
    Matrix result = new Matrix(dimension);
    
    if (dimension <= FORK_THRESHOLD) {
      return Matrix.nonRecursiveMultiply(m1, m2, m1Row, 
          m1Col, m2Row, m2Col, dimension);
    }

    // else, cut the matrix into four equal sizes and multiply them recursively and parallelly.
    int size = dimension / 2;
    // top-left corner
    MatrixMultiplication a11b11 = new MatrixMultiplication(m1, m2, m1Row, 
        m1Col, m2Row, m2Col, size);
    MatrixMultiplication a12b21 = new MatrixMultiplication(m1, m2, m1Row, 
        m1Col + size, m2Row + size, m2Col, size);
    a11b11.fork();
    a12b21.fork();

    // top-right corner
    MatrixMultiplication a11b12 = new MatrixMultiplication(m1, m2, m1Row, 
        m1Col, m2Row, m2Col + size, size);
    MatrixMultiplication a12b22 = new MatrixMultiplication(m1, m2, m1Row, 
        m1Col + size, m2Row + size, m2Col + size, size);
    a11b12.fork();
    a12b22.fork();

    // bottom-left corner
    MatrixMultiplication a21b11 = new MatrixMultiplication(m1, m2, m1Row + size, 
        m1Col, m2Row, m2Col, size);
    MatrixMultiplication a22b12 = new MatrixMultiplication(m1, m2, m1Row + size, 
        m1Col + size, m2Row + size, m2Col, size);
    a21b11.fork();
    a22b12.fork();

    // bottom-right corner
    MatrixMultiplication a21b12 = new MatrixMultiplication(m1, m2, m1Row + size, 
        m1Col, m2Row, m2Col + size, size);
    MatrixMultiplication a22b22 = new MatrixMultiplication(m1, m2, m1Row + size, 
        m1Col + size, m2Row + size, m2Col + size, size);

    a21b12.fork();

    // adding up the results to form the actual matrix.
    // start from the opposite order that the forks are performed.
    Matrix a22b22Result = a22b22.compute();
    Matrix a21b12Result = a21b12.join();
    matrixAddition(result, a22b22Result, a21b12Result, size, "BOTTOM-RIGHT");

    Matrix a22b12Result = a22b12.join();
    Matrix a21b11Result = a21b11.join();
    matrixAddition(result, a22b12Result, a21b11Result, size, "BOTTOM-LEFT");

    Matrix a12b22Result = a12b22.join();
    Matrix a11b12Result = a11b12.join();
    matrixAddition(result, a12b22Result, a11b12Result, size, "TOP-RIGHT");

    Matrix a12b21Result = a12b21.join();
    Matrix a11b11Result = a11b11.join();
    matrixAddition(result, a12b21Result, a11b11Result, size, "TOP-LEFT");

    return result;
  }

  /**
   * Adds matrices together.
   * 
   * @param result   The matrix to store the result in.
   * @param m1       first matrix.
   * @param m2       second matrix.
   * @param size     dimensions of the matrix to be added.
   * @param quadrant the quadrant of the matrix that the operands correspond to.
   */
  private void matrixAddition(Matrix result, Matrix m1,
      Matrix m2, int size, String quadrant) {

    int row = 0;
    int col = 0;
    switch (quadrant) {
      case "TOP-LEFT":
        break;
      case "TOP-RIGHT":
        col += size;
        break;
      case "BOTTOM-LEFT":
        row += size;
        break;
      case "BOTTOM-RIGHT":
        row += size;
        col += size;
        break;
      default:
        System.out.println("Incorrect quadrant provided.");
    }

    // getting each row
    for (int i = 0; i < size; i++) {
      double[] m1r = m1.m[i];
      double[] m2r = m2.m[i];
      double[] resultr = result.m[i + row];

      // adding the columns for each row
      for (int j = 0; j < size; j++) {
        resultr[j + col] = m1r[j] + m2r[j];
      }
    }
  }
}
