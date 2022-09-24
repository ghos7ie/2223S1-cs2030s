/**
 * The Applicable interface that can probably
 * transform if given something that is
 * probably an Immutator.
 *
 * Contains a single abstract method apply.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY22/23 Semester 1
 */

interface Applicable<T> {
  abstract <R> Probably<R> apply(Immutator<R, T> immutator);
}