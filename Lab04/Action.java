/**
 * The Action interface that can be called
 * on an object of type T to act.
 *
 * Contains a single abstract method call.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

interface Action<T> {
  /**
   * Abstract call method.
   * 
   * @param item Item to perform Action on.
   */
  abstract void call(T item);
}
