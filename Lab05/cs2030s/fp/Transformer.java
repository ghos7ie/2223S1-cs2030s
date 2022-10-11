package cs2030s.fp;

/**
 * Abstract {@code Transformer<R,P>} class.
 * 
 * @version CS2030S Lab 5
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Transformer<R, P> implements Immutator<R, P> {

  public abstract R invoke(P param);

  /**
   * Transforms N to P to R.
   * 
   * @param <N>   Explicit type parameter. Telling compiler that the type of
   *              return will be N.
   * @param after the Transformer to change.
   * @return new {@code Transformer<R,N>}.
   */
  public <N> Transformer<R, N> after(Transformer<? extends P, ? super N> after) {
    /*
     * Currently i have Transformer <R,P> and Transformer<P,N>
     * 
     * I want to get Transformer<R,N> at the end.
     *
     */
    Transformer<R, P> f = this;
    return new Transformer<R, N>() {
      public R invoke(N param) {
        // after.invoke() will change param from N to P
        // f.invoke() will then change param from P to R
        return f.invoke(after.invoke(param));
      }
    };
  }

  /**
   * Transforms R to N to T.
   * 
   * @param <T>    Explicit type parameter. Telling compiler that the type of
   *               return will be N.
   * @param before the Transformer to change.
   * @return new {@code Transformer<T,P>}.
   */
  public <T> Transformer<T, P> before(Transformer<? extends T, ? super R> before) {
    /*
     * Currently I have Transformer<R, P> and Transformer<T, R>.
     * 
     * I want to get Transformer<T, P>.
     * 
     * Means R must become T
     */
    Transformer<R, P> f = this;
    return new Transformer<T, P>() {
      public T invoke(P param) {
        return before.invoke(f.invoke(param));
      }
    };
  }
}