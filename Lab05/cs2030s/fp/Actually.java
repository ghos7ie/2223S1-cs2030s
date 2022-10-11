package cs2030s.fp;

/**
 * {@code Actually<T>} class.
 * 
 * @version CS2030S Lab 5
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

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
   * @param <S> Explicit type parameter. Telling compiler that the type of
   *            return will be S.
   * @param c   value to be returned.
   * @return value of type T.
   */
  public abstract <S extends T> T except(Constant<S> c);

  /**
   * Abstract finish method.
   * 
   * @param action to be used.
   */
  public abstract void finish(Action<T> action);

  /**
   * Abstract unless method.
   * 
   * @param <U>  Explicit type parameter. Telling compiler that the type of
   *             return will be U.
   * @param item item of subtype T.
   * @return value of subtype T.
   */
  public abstract <U extends T> T unless(U item);

  /**
   * Transforms T item to R item.
   * 
   * @param <R>       Explicit type parameter. Telling compiler that the type of
   *                  return will be R.
   * @param immutator Item of type T that will be changed to
   *                  {@code Immutatorable<R>}.
   * 
   * @return Item of type {@code Immutatorable<R>}.
   */
  public abstract <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> immutator);

  /**
   * Abstract next method.
   * 
   * @param <R>       Explicit type parameter. Telling compiler that the type of
   *                  return will be R.
   * @param immutator contains the T value to wrap into {@code Actually<R>}.
   * @return {@code Actually<R>}.
   */
  public abstract <R> Actually<R> next(Immutator<Actually<R>, T> immutator);

  /**
   * Abstract method of act.
   * Super since action is being consumed
   * 
   * @param action Action to be performed.
   */
  public abstract void act(Action<? super T> action);

  /**
   * ok(res) returns a Success Object.
   * 
   * @param <T> Explicit type parameter. Telling compiler that the type of
   *            return will be T.
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
   * @param <T>       Explicit type parameter. Telling compiler that the type of
   *                  return will be T.
   * @param exception object of type exception.
   *
   * @return new Failure().
   */
  public static <T> Actually<T> err(Exception exception) {
    // this is fine since we know that Failure is a subtype
    // of Actually.
    @SuppressWarnings("unchecked")
    Actually<T> act = (Actually<T>) new Failure(exception);
    return act;
  }

  static final class Success<T> extends Actually<T> {

    private T value;

    /**
     * Constuctor for Success class.
     *
     * @param value value of type T.
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
     * Returns value of {@code Success<T>} apparently.
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
     * Performs provided action on value of {@code Success<T>}.
     * 
     * @param action action to be performed.
     */
    @Override
    public void finish(Action<T> action) {
      action.call(this.value);
    }

    /**
     * Returns value of {@code Success<T>}.
     * 
     * @param item does nothing.
     * @return value of Success.
     */
    @Override
    public <U extends T> T unless(U item) {
      return this.value;
    }

    /**
     * Tries to mutate value of this {@code Success<T>}.
     * 
     * @param <R>       Explicit type parameter. Telling compiler that the type of
     *                  return will be R.
     * @param immutator immutator that is going to mutate the value.
     * 
     * @return new {@code Success<T>}.
     *         or Failure.
     */
    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> immutator) {
      try {
        return Actually.ok(immutator.invoke(this.value));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    /**
     * Invokes the call method in Action on {@code Success<T>}.
     * 
     * @param action Action to be performed.
     * 
     */
    public void act(Action<? super T> action) {
      action.call(this.value);
    }

    /**
     * Uses provided T value and creates an {@code Actually<R>}.
     * 
     * @param <R>       Explicit type parameter. Telling compiler that the type of
     *                  return will be R.
     * @param immutator contains the T value to wrap into {@code Actually<R>}.
     * @return {@code Actually<R>}.
     */
    @Override
    public <R> Actually<R> next(Immutator<Actually<R>, T> immutator) {
      try {
        return (Actually<R>) immutator.invoke(this.value);
      } catch (Exception e) {
        return Actually.err(e);
      }
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
     * @param exception obj of type Exception.
     *
     */
    public Failure(Object exception) {
      this.exception = (Exception) exception;
    }

    /**
     * Throws the exception stored in Failure.
     * 
     * @throws Exception for Failure class.
     * @reutrn returns nothing, exception will be thrown.
     */
    @Override
    public Object unwrap() throws Exception {
      throw this.exception;
    }

    /**
     * Gets value inside of c.
     * 
     * @param <S> Explicit type parameter. Telling compiler that the type of
     *            return will be S.
     * @param c   object with value we are getting.
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
     * @param <U>  Explicit type parameter. Telling compiler that the type of
     *             return will be a subtype of Object.
     * @param item of type U.
     * 
     * @return item in argument.
     */
    @Override
    public <U extends Object> Object unless(U item) {
      return item;
    }

    /**
     * Propogates Failure as new Failure.
     * 
     * @param <R>       Explicit type parameter. Telling compiler that the type of
     *                  return will be R.
     * @param immutator immutator that is going to mutate the value.
     * 
     * @return new Failure wrapped in Actually.
     */
    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, ? super Object> immutator) {
      return Actually.err(this.exception);
    }

    /**
     * Does nothing.
     * 
     * @param action Action to be performed.
     * 
     */
    public void act(Action<? super Object> action) {
    }

    /**
     * Uses provided T value and creates an {@code Actually<R>}.
     * 
     * @param <S>       Explicit type parameter. Telling compiler that the type of
     *                  return will be S.
     * @param immutator contains the T value to wrap into {@code Actually<R>}.
     * @return {@code Actually<R>}.
     */
    @Override
    public <R> Actually<R> next(Immutator<Actually<R>, Object> immutator) {
      return Actually.err(this.exception);
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
      return "[" + this.exception.getClass().getCanonicalName() + "] "
          + this.exception.getMessage();
    }
  }
}
