package cs2030s.fp;

/**
 * {@code Memo<T>} extends {@code Lazy<T>} class. Encapsulates and memorises
 * the value when initialised.
 * 
 * @version CS2030S Lab 6
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */
public class Memo<T> extends Lazy<T> {
  private Actually<T> value;

  /**
   * Private constructor for Memo class.
   * 
   * @param init  Constant to give to super class Lazy.
   * @param value value to initialize Actually with.
   */
  private Memo(Constant<? extends T> init, Actually<T> value) {
    super(init);
    this.value = value;
  }

  /**
   * Instantiate Memo object.
   * 
   * @param <T> Explicit type parameter. Telling compiler that the type of
   *            return will be T.
   * @param v   value of type T to instantiate Lazy object with.
   * @return Memo object through lambda expression.
   */
  public static <T> Memo<T> from(T v) {
    return new Memo<>(() -> v, Actually.ok(v));
  }

  /**
   * Creates a Memo object with an uninitialized value.
   * 
   * @param <T> Explicit type parameter. Telling compiler that the type of
   *            return will be T.
   * @param c   value of Constant type.
   * @return new Memo object created through protected constructor.
   */
  public static <T> Memo<T> from(Constant<? extends T> c) {
    // Actually is Failure since it has not been initialized.
    return new Memo<>(c, Actually.err(new Exception("error")));
  }

  /**
   * Computes and return value inside of Memo.
   * If computed, it will just return the value.
   * 
   * @return computed value of Memo.
   */
  public T get() {
    T computedVal = this.value.except(() -> super.get());
    this.value = Actually.ok(computedVal);
    return computedVal;
  }

  /**
   * Returns string representation of Memo.
   * 
   * @return String representation of Memo.
   */
  @Override
  public String toString() {
    return this.value.transform(v -> String.valueOf(v)).unless("?");
  }
}