/**
 * The generic Array for CS2030S.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
/*
 * Extends Comparable<T> since Array<T> only takes in only a subtype of
 * Comparable<T>.
 */
class Array<T extends Comparable<T>> {
  /**
   * Array of T type.
   */
  private T[] array;

  /**
   * Constructor for an array.
   * 
   * @param size
   *          The maximum num of elements we can put in the array.
   */
  public Array(int size) {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
  }

  /**
   * Set method to insert an item into array.
   * 
   * @param index
   *          Index at which to insert item.
   * @param item
   *          Item that is being inserted.
   */
  public void set(int index, T item) {
    this.array[index] = item;
  }

  /**
   * Get method to retrieve item at given index in array.
   * 
   * @param index
   *          Index of item to retrieve.
   * @return Item of index in array.
   */
  public T get(int index) {
    return this.array[index];
  }

  /**
   * Returns item of least value (not with smallest index).
   * 
   * @return Object of type T, which has the smallest value.
   */
  public T min() {
    // Compare all items in array
    T minItem = this.array[0];    
    for (int i = 1; i < this.array.length; i++) {
      // IF compareTo returns -1, means array[i] is smaller
      // than current minItem
      if (this.array[i].compareTo(minItem) == -1) {
        minItem = this.array[i];
      }
    }
    return minItem;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
