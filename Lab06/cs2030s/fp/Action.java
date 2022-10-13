package cs2030s.fp;

/**
 * Represent a function that acts on a value.
 * CS2030S Lab 5
 * AY22/23 Semester 1
 *
 * @param <T> The type of the input value.
 */
@FunctionalInterface
interface Action<T> {
  /**
   * The functional method to act on the value t.
   *
   * @param t The input value.
   */
  void call(T t);
}