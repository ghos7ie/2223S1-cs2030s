/**
 * The Array<T> for CS2030S 
 *
 * @author XXX
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    // TODO
  }

  public void set(int index, T item) {
    // TODO
  }

  public T get(int index) {
    // TODO
  }

  public T min() {
    // TODO
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
