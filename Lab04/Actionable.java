/**
 * The Actionable interface that can
 * act when given an action.
 *
 * Contains a single abstract method act.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Actionable<T> {
  void act(Action<? super T> action);
}
