import java.util.concurrent.RecursiveTask;

class MatrixMultiplication extends RecursiveTask<Matrix> {
  
  /** The fork threshold. */
  private static final int FORK_THRESHOLD = 1024; // Find a good threshold

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
   * @param  m1 The matrix to multiply with.
   * @param  m2 The matrix to multiply with.
   * @param  m1Row The starting row of m1.
   * @param  m1Col The starting col of m1.
   * @param  m2Row The starting row of m2.
   * @param  m2Col The starting col of m2.
   * @param  dimension The dimension of the input (sub)-matrices and the size
   *     of the output matrix.
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
    // Modify this
    return Matrix.recursiveMultiply(m1, m2, m1Row, m1Col, m2Row, m2Col, dimension);
  }
}
