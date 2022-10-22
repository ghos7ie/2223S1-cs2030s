import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;
import cs2030s.fp.Combiner;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a lazily evaluated and memoized
 * list that can be generated with a lambda expression.
 *
 * @author Adi Yoga S. Prabawa
 * @version CS2030S AY 22/23 Sem 1
 */
class MemoList<T> {
  /** The wrapped java.util.List object */
  private List<Memo<T>> list;

  /**
   * A private constructor to initialize the list to the given one.
   *
   * @param list The given java.util.List to wrap around.
   */
  private MemoList(List<Memo<T>> list) {
    this.list = list;
  }

  /**
   * Generate the content of the list. Given x and a lambda f,
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))),
   * ... ]
   *
   * @param <T>  The type of the elements in the list.
   * @param n    The number of elements.
   * @param seed The first element.
   * @param f    The immutator function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T seed, Immutator<? extends T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    // current T
    Memo<T> curr = Memo.from(seed);
    for (int i = 0; i < n; i++) {
      memoList.list.add(curr);
      curr = curr.transform(f);
    }
    return memoList;
  }

  /**
   * Lazy Fibonacci Sequence.
   * Generate the content of the list. Given x, y and a lambda f,
   * generate the list of n elements as [x, y, f(x,y), f(y,f(x,y))* ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n   The number of elements.
   * @param fst The first element.
   * @param snd The second element.
   * @param f   The combiner function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T fst, T snd, Combiner<T, T, T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> curr1 = Memo.from(fst);
    Memo<T> curr2 = Memo.from(snd);
    memoList.list.add(curr1);
    memoList.list.add(curr2);
    // starting from 2 since first 2 evaluated already
    for (int i = 2; i < n; i++) {
      // if even
      if (i % 2 == 0) {
        curr1 = curr1.combine(curr2, f);
        memoList.list.add(curr1);
      } else {
        curr2 = curr2.combine(curr1, f);
        memoList.list.add(curr2);
      }
    }
    return memoList;
  }

  public MemoList<T> map(Immutator<T, T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    for (Memo<T> m : this.list) {
      memoList.list.add(m.transform(f));
    }
    return memoList;
  }

  /**
   * Return the element at index i of the list.
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /**
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list. -1 is element is not in the
   *         list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Memo.from(v));
  }

  /**
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
