/**
 * The Actionable interface that can
 * act when given an action.
 * <p>
 * Contains a single abstract method act.
 * </p>
 *
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Actionable<T> {
  /**
   * Abstract method of act.
   * Super since action is being consumed
   * 
   * @param action Action to be performed.
   */
  void act(Action<? super T> action);
}
