package cs2030s.fp;

import java.util.List;

/**
 * An infinite list is a list with an infinite number of elements
 * generated through lazy evaluation.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY 20/21 Sem 2
 *
 */

public class InfiniteList<T> {
  private Memo<Actually<T>> head;
  private Memo<InfiniteList<T>> tail;

  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }
  // You may add other private constructor but it's not necessary.

  /**
   * Generates an infinite list given the constant.
   * 
   * @param <T>  The type of elements in the list.
   * @param prod Producer of elements.
   * @return Infinite list containing prod.
   */
  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    return new InfiniteList<>(Memo.from(() -> Actually.ok(prod.init())),
        Memo.from(() -> InfiniteList.generate(prod)));
  }

  /**
   * Generates an infinite list given the immutator.
   * Returning a list of [seed, func(seed), func(func(seed)),...].
   * 
   * @param <T>  The type of elements in the list.
   * @param seed Initial element.
   * @param func Function to be applied to element.
   * @return Infinite list containing iterations of given element.
   */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    // TODO
    return new InfiniteList<>(Memo.from(Actually.ok(seed)),
        Memo.from(() -> InfiniteList.iterate(func.invoke(seed), func)));
  }

  /**
   * Returns the head of the List or subsequent one if current head is empty.
   * 
   * @return First non-null head field.
   */
  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  /**
   * Returns the tail value of the first non-null head field.
   * 
   * @return Tail value of the first non-null head field.
   */
  public InfiniteList<T> tail() {
    // transform basically returns tail if head is null
    return this.head.get().transform(x -> this.tail.get()).except(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    // TODO
    return new InfiniteList<R>(
        this.head.transform(h -> h.transform(f)),
        this.tail.transform(t -> t.map(f)));
  }

  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0L;
  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  public boolean isEnd() {
    return false;
  }

  // Add your End class here...
}