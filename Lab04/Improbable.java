/**
 * A generic Immutator that takes in an object
 * that is T and returns an object that is probably T.
 *
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

class Improbable<T> implements Immutator<Probably<T>, T> {
  /**
   * Changes param of type T to {@code Probably<T>}.
   * 
   * @param param to change to {@code Probably<T>}.
   * @return param from type T to {@code Probably<T>}.
   */
  @Override
  public Probably<T> invoke(T param) {
    return Probably.just(param);
  }
}