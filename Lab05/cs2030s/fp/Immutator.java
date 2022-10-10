package cs2030s.fp;

/**
 * The Immutator interface that can transform
 * to type T2, an object of type T1.
 * <p>
 * Contains a single abstract method invoke.
 * </p>
 * 
 * @version CS2030S Lab 5
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public interface Immutator<R, P> {
  /**
   * Changes param of type P to R.
   * 
   * @param param to change to type R.
   * @return param from type P to R.
   */
  R invoke(P param);
}
