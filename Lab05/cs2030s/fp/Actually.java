package cs2030s.fp;

/**
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T> {

  protected T value;

  /**
   * Factory method for {@code Actually<T>}.
   * Cannot be accessed outside of class.
   *
   * @param value value of Actually<T>.
   */
  private Actually(T value) {
    this.value = value;
  }

  /**
   * ok(res) returns a Success Object.
   * 
   * @param res variable of type T to put into Success.
   *
   * @return new Success().
   *
   */
  public static <T> Success<T> ok(T res) {
    return new Success<>(res);
  }

  /**
   * err(exception) returns a Failure Object.
   * 
   * @param exception object of type exception.
   *
   * @return new Failure().
   */
  public static <T> Failure err(Exception exception) {
    return new Failure(exception);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Actually) {
      if (obj instanceof Success) {
        @SuppressWarnings("unchecked")
        Success<T> succ = (Success<T>) obj;
        if (this.value == succ.value) {
          return true;
        } else {
          return false;
        }
      } else if (obj instanceof Failure) {
        @SuppressWarnings("unchecked")
        Failure fail = (Failure) obj;
        if (this.value.getMessage() == fail.value.getMessage()) {
          return true;
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
  }

  static final class Success<T> extends Actually<T> {

    /**
     * Constuctor for Success class.
     *
     * @param val value of type T.
     *
     */
    public Success(T value) {
      super(value);
    }

    /**
     * Returns String representation of Success object.
     *
     * @return {@code <null>} if this.value is null.
     *         value.toString() if not null.
     */
    @Override
    public String toString() {
      if (super.value != null) {
        return "<" + super.value.toString() + ">";
      } else {
        return "<null>";
      }
    }
  }

  static final class Failure extends Actually<Object> {

    /**
     * Constructor for Failure class.
     *
     * @param obj obj of type Exception.
     *
     */
    public Failure(Exception obj) {
      super(obj);
    }

    /**
     * Returns String representation of Failure object.
     *
     * @return value in formatted manner.
     */
    @Override
    public String toString() {
      return "[" + ((Exception) super.value).getClass().toString() + "] " + ((Exception) super.value).getMessage();
    }
  }
}
