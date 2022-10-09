package cs2030s.fp;

/**
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T> {
  /**
   * ok(res) returns a Success Object.
   * 
   * @param res variable of type T to put into Success.
   *
   * @return new Success().
   *
   */
  public static <T> Actually<T> ok(T res) {
    return new Success<T>(res);
  }

  /**
   * err(exception) returns a Failure Object.
   * 
   * @param exception object of type exception.
   *
   * @return new Failure().
   */
  public static Actually<Object> err(Exception exception) {
    return new Failure(exception);
  }

  static final class Success<T> extends Actually<T> {

    private T value;

    /**
     * Constuctor for Success class.
     *
     * @param val value of type T.
     *
     */
    public Success(T value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Success) {
        // it is fine to suppress since we already checked that
        // object is of Success type.
        @SuppressWarnings("unchecked")
        Success<T> succ = (Success<T>) obj;
        if (this.value == succ.value) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }

    /**
     * Returns String representation of Success object.
     *
     * @return {@code <null>} if this.value is null.
     *         value.toString() if not null.
     */
    @Override
    public String toString() {
      if (this.value != null) {
        return "<" + this.value.toString() + ">";
      } else {
        return "<null>";
      }
    }
  }

  static final class Failure extends Actually<Object> {

    private Exception exception;

    /**
     * Constructor for Failure class.
     *
     * @param obj obj of type Exception.
     *
     */
    public Failure(Exception exception) {
      this.exception = exception;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure fail = (Failure) obj;
        if (fail.getClass() == this.getClass()) {
          if (this.exception.getMessage() == fail.exception.getMessage()) {
            return true;
          } else {
            return false;
          }
        } else {
          return false;
        }
      } else {
        return false;
      }
    }

    /**
     * Returns String representation of Failure object.
     *
     * @return value in formatted manner.
     */
    @Override
    public String toString() {
      return "[" + this.exception.getClass().toString() + "] " + this.exception.getMessage();
    }
  }
}
