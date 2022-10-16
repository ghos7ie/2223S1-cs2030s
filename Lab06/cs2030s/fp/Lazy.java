package cs2030s.fp;

public class Lazy<T> /* implements Immutatorable<T> */ {
  private Constant<? extends T> init;

  /**
   * Protected constructor of Lazy.
   * 
   * @param c Constant of type T.
   */
  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }

  /**
   * Instantiate Lazy object.
   * 
   * @param <T> Explicit type parameter. Telling compiler that the type of
   *            return will be T.
   * @param v   value of type T to instantiate Lazy object with.
   * @return Lazy object through lambda expression.
   */
  public static <T> Lazy<T> from(T v) {
    return new Lazy<>(() -> v);
  }

  /**
   * 
   * @param <T> Explicit type parameter. Telling compiler that the type of
   *            return will be T.
   * @param c   value of Constant type.
   * @return new Lazy object created through protected constructor.
   */
  public static <T> Lazy<T> from(Constant<? extends T> c) {
    return new Lazy<>(c);
  }

  /**
   * Computes and return value inside of Lazy.
   * 
   * @return computed value of Lazy.
   */
  public T get() {
    return this.init.init();
  }

  /**
   * String representation of Lazy.
   * 
   * @return String representation of Lazy.
   */
  @Override
  public String toString() {
    return this.init.init().toString();
  }
}