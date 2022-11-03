/**
 * Represent a function that transforms one value into another, possible of different types.
 * CS2030S Mock PE 2
 * AY22/23 Semester 2
 *
 * @param <R> The type of the result value
 * @param <P> The type of the input value
 */
package cs2030s.fp;

public interface Immutator<R, P> {
  /**
   * The function method to transforms the value p.
   *
   * @param p The input value
   * @return The value after applying the given immutator on p.
   */
  R invoke(P p);
}
