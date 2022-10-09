package cs2030s.fp;

/**
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T> {

  /**
   * Unwraps nested class to get value.
   * 
   * @return T value of the implemented class.
   * @throws Exception for Failure class.
   */
  public abstract T unwrap() throws Exception;

  /**
   * Abstract except method.
   * 
   * @param c value to be returned.
   * @return value of type T.
   */
  public abstract <S extends T> T except(Constant<S> c);

  /**
   * Abstract finish method.
   * 
   * @param action to be used.
   */
  public abstract void finish(Action<? super T> action);

  /**
   * Abstract unless method.
   * 
   * @param item item of subtype T.
   * @return value of subtype T.
   */
  public abstract <U extends T> T unless(U item);

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

    /**
     * Unwraps {@code Success<T>}.
     * 
     * @return value inside Success.
     */
    @Override
    public T unwrap() {
      return this.value;
    }

    /**
     * Returns value of {@code Success<T>} apparently??
     * 
     * @param c has no use here i believe.
     * 
     * @return value within {@code Success<T>}.
     */
    @Override
    public <S extends T> T except(Constant<S> c) {
      return this.value;
    }

    /**
     * Performs provided action on value of {@code Success<T>}
     * 
     * @param action action to be performed.
     */
    @Override
    public void finish(Action<? super T> action) {
      action.call(this.value);
    }

    /**
     * Returns value fo {@code Success<T>}.
     * 
     * @param item does nothing.
     */
    @Override
    public <U extends T> T unless(U item) {
      return this.value;
    }

    /**
     * Equals implementation of {@code Success<T>}.
     * 
     * @param obj Object to be compared.
     * 
     * @return true if obj is of type {@code Success<T>} and values are the same.
     *         false for other cases.
     */
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
      if (this.value == null) {
        return "<null>";
      } else {
        return "<" + this.value.toString() + ">";
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

    /**
     * Throws the exception stored in Failure.
     * 
     * @reutrn returns nothing, exception will be thrown.
     */
    @Override
    public Object unwrap() throws Exception {
      throw this.exception;
    }

    /**
     * Gets value inside of c.
     * 
     * @param c object with value we are getting.
     * 
     * @return value within c.
     */
    @Override
    public <S> S except(Constant<S> c) {
      return c.init();
    }

    /**
     * Nothing. Don't do anything.
     * 
     * @param action :).
     */
    @Override
    public void finish(Action<Object> action) {
    }

    /**
     * Returns item in argument.
     * 
     * @param item of type U.
     * 
     * @return item in argument.
     */
    @Override
    public <U extends Object> Object unless(U item) {
      return item;
    }

    /**
     * Equals implementation for Failure.
     * 
     * @param obj Object to be compared.
     * 
     * @return true if obj is of type Failure and getMessage() are the same.
     *         false for all other cases.
     */
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure fail = (Failure) obj;
        if (this.exception.getMessage() == null || fail.exception.getMessage() == null) {
          return false;
        } else if (this.exception.getMessage() == fail.exception.getMessage()) {
          return true;
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
