package cs2030s.fp;

/**
 * The Actionable interface that can
 * act when given an action.
 * <p>
 * Contains a single abstract method act.
 * </p>
 *
 * @version CS2030S Lab 5
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public interface Actionable<T> {
  /**
   * Abstract method of act.
   * Super since action is being consumed
   * 
   * @param action Action to be performed.
   */
  abstract void act(Action<? super T> action);
}
