/**
 * The Immutator interface that can transform
 * to type T2, an object of type T1.
 *
 * Contains a single abstract method invoke.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Immutator<R, P> {
  /**
   * Changes param of type P to R.
   * 
   * @param param to change to type R.
   * @return param from type P to R.
   */
  R invoke(P param);
}