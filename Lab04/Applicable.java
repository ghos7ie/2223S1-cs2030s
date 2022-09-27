/**
 * The Applicable interface that can probably
 * transform if given something that is
 * probably an Immutator.
 * <p>
 * Contains a single abstract method apply.
 * </p>
 *
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Applicable<T> {
  /**
   * Takes in {@code Probably<Immutator>} and mutates value inside
   * {@code Probably<Immutator>}.
   * 
   * @param <R>      Explicit type parameter. Telling compiler that the type of
   *                 return will be R.
   * @param probably {@code Probably<Immutator>} to be mutated.
   * @return {@code Probably<R>} if immutator is this.value is not null and is an
   *         immutator.
   *         Else none();
   */
  <R> Probably<R> apply(Probably<? extends Immutator<? extends R, ? super T>> probably);
}