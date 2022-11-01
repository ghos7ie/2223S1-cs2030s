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
  /**
   * Actually encapsulated by Memo.
   */
  private Memo<Actually<T>> head;
  /**
   * InfiniteList encapsulated by Memo.
   * Contains the rest of the elements in the InfiniteList.
   */
  private Memo<InfiniteList<T>> tail;

  /**
   * Caches the end of the infinite list.
   */
  private static final End END = new End();

  /**
   * Factory method of Infinite List.
   * 
   * @param head Starting element.
   * @param tail Rest of the list.
   */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  // You may add other private constructor but it's not necessary.
  /**
   * Empty constructor for InfiniteList.
   */
  private InfiniteList() {
    this.head = null;
    this.tail = null;
  }

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
   * Creates the end infinite list.
   * 
   * @param <T> The type of elements in the list.
   * @return Empty Infinite List.
   */
  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> end = (InfiniteList<T>) END;
    return end;
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

  /**
   * Lazily applies immutator to each element in the infinite list.
   * 
   * @param <R> The type of elements in the new list.
   * @param f   The immutator to mutate each elements with.
   * @return New InfiniteList with mutated values.
   */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<R>(
        this.head.transform(h -> h.transform(f)),
        // recursively call map and apply immutator to each head value.
        this.tail.transform(t -> t.map(f)));
  }

  /**
   * Filters out elements that fail given the Immutator provided.
   * 
   * @param pred The immutator to check each element with.
   * @return new List of elements that fail the Immutator.
   */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<>(
        head.transform(h -> h.check(pred)),
        tail.transform(t -> t.filter(pred)));
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

  /**
   * String represenation of InfiniteList.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /**
   * Checks if InfiniteList is the end.
   * 
   * @return Returns false as it is not the end.
   */
  public static boolean isEnd() {
    return false;
  }

  // Add your End class here...
  private static class End extends InfiniteList<Object> {
    /**
     * Constructor for End.
     */
    End() {
      super();
    }

    /**
     * Returns the head of the List or subsequent one if current head is empty.
     * 
     * @return No Such Element Exception.
     */
    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    /**
     * Returns the tail value of the first non-null head field.
     * 
     * @return No Such Element Exception.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    /**
     * Checks if InfiniteList is the end.
     * 
     * @return Returns true as it is end.
     */
    public static boolean isEnd() {
      return true;
    }

    /**
     * String represenation of InfiniteList.
     */
    @Override
    public String toString() {
      return "-";
    }
  }
}