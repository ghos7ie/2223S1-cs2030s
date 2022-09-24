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
  <R> Probably<R> apply(Probably<Immutator<R, T>> immutator);
}