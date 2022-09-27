/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 * <p>
 * Contains abstract methods trasnform and check.
 * </p>
 * 
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Immutatorable<T> {
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
  abstract <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> immutator);
}