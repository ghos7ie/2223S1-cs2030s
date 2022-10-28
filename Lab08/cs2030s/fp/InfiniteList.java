package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {
  private Memo<Actually<T>> head;
  private Memo<InfiniteList<T>> tail;
  
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }
  // You may add other private constructor but it's not necessary.

  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    // TODO
    return new InfiniteList<>(null, null);
  }
  
  public T head() {
    // TODO
    return null;
  }
  
  public InfiniteList<T> tail() {
    // TODO
    return new InfiniteList<>(null, null);
  }
  
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    // TODO
    return new InfiniteList<>(null, null);
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

  public <U> U reduce (U id, Combiner<U, U, ? super T> acc) {
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