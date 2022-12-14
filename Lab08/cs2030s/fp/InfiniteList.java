package cs2030s.fp;

import java.util.ArrayList;
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
    // this.head.get() returns the head value
    // .except() will handle the case where head is an Err
    // and recursively call for the next head
    return this.head.get().except(() -> this.tail.get().head());
  }

  /**
   * Returns the tail value of the first non-null head field.
   * 
   * @return Tail value of the first non-null head field.
   */
  public InfiniteList<T> tail() {
    // transform basically returns tail if head is null
    return this.head.get()
        .transform(x -> this.tail.get())
        .except(() -> this.tail.get().tail());
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
        this.head.transform(h -> h.check(pred)),
        this.tail.transform(t -> t.filter(pred)));
  }

  /**
   * Truncates Infinitelist to a FiniteList :).
   * With at most n elements.
   * 
   * @param n max number of elements in the "InfiniteList".
   * @return Truncated InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    // returns an end if n is less than or equals to 0
    // else recursively calls the next InfiniteList and decrementing n depending on
    // whether the head
    // value exists.
    return n <= 0 ? end()
        : new InfiniteList<>(
            this.head,
            Memo.from(() -> this.head.get()
                .transform(t -> this.tail.get().limit(n - 1))
                .except(() -> this.tail.get().limit(n))));
  }

  /**
   * Finitises an InfiniteList to the first element that fails the check.
   * 
   * @param pred condition to check element against.
   * @return Truncated list containing elements that pass until the first one that
   *         fails.
   */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    // Create a new Memo, to retrieve the first exisitng head
    // this.head() will skip over the heads that are failures!!
    // uses check(pred)
    // if head value fails (because err or fails check), return err
    // else returns the Actually<head>.
    Memo<Actually<T>> exist = Memo.from(() -> Actually.ok(this.head()).check(pred));
    return new InfiniteList<>(
        exist,
        Memo.from(() -> exist.get()
            // if pass pred, can do recusive call
            .transform(t -> this.tail().takeWhile(pred))
            // unless so that if it is an end/fails, it will be evaluated.
            .except(() -> end())));
  }

  /**
   * Collects elements in InfiniteList into a list.
   * 
   * @return List containing all the elements in the InfiniteList.
   */
  public List<T> toList() {
    List<T> output = new ArrayList<>();
    Action<T> addToArray = (e) -> {
      output.add(e);
    };
    InfiniteList<T> checkList = this;
    while (!checkList.isEnd()) {
      // does not add if head is err
      checkList.head.get().finish(addToArray);
      checkList = checkList.tail.get();
    }
    return output;
  }

  /**
   * Performs an operation to accumulate all the elements in the InfiniteList.
   * 
   * @param <U> Type of return value is U.
   * @param id  Starting value of the reduction.
   * @param acc Function to perform on the List.
   * @return Reduced value of the list.
   */
  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {
    U pass = this.head.get()
        // bascially pass the value inside of head into the accumulator
        .transform(x -> acc.combine(id, x))
        // if can't do that since head.get() is Failure
        // return the id unchanged.
        .unless(id);
    return this.tail.get().reduce(pass, acc);
  }

  /**
   * Returns count of elements in InfiniteList.
   * 
   * @return Count of elements.
   */
  public long count() {
    // basically + 1 for every element that exists inside
    return reduce(0L, (x, y) -> x + 1L);
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
  public boolean isEnd() {
    return false;
  }

  // Add your End class here...
  static class End extends InfiniteList<Object> {
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
     * Lazily applies immutator to each element in the infinite list.
     * 
     * @param <R> The type of elements in the new list.
     * @param f   The immutator to mutate each elements with.
     * @return New InfiniteList with mutated values.
     */
    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> f) {
      return End.end();
    }

    /**
     * Filters out elements that fail given the Immutator provided.
     * 
     * @param pred The immutator to check each element with.
     * @return new List of elements that fail the Immutator.
     */
    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> pred) {
      return End.end();
    }

    /**
     * Truncates Infinitelist to a FiniteList :).
     * With at most n elements.
     * 
     * @param n max number of elements in the "InfiniteList".
     * @return End.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return End.end();
    }

    /**
     * Finitises an InfiniteList to the first element that fails the check.
     * 
     * @param pred condition to check element against.
     * @return End.
     */
    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return End.end();
    }

    /**
     * Performs an operation to accumulate all the elements in the InfiniteList.
     * 
     * @param <U> Type of return value is U.
     * @param id  Starting value of the reduction.
     * @param acc Function to perform on the List.
     * @return Reduced value of the list.
     */
    @Override
    public <U> U reduce(U id, Combiner<U, U, ? super Object> acc) {
      return id;
    }

    /**
     * Returns count of elements in InfiniteList.
     * 
     * @return Count of elements.
     */
    @Override
    public long count() {
      return 0;
    }

    /**
     * Returns empty list.
     * 
     * @return Empty List.
     */
    @Override
    public List<Object> toList() {
      return List.of();
    }

    /**
     * Checks if InfiniteList is the end.
     * 
     * @return Returns true as it is end.
     */
    public boolean isEnd() {
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