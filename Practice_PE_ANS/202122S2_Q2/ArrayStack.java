/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class ArrayStack<T> implements Stack<T> {

  private int maxDepth;
  private int size;
  private T[] array;

  public ArrayStack(int maxDepth) {
    this.maxDepth = maxDepth;
    this.size = 0;
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Object[maxDepth];
    this.array = a;
  }

  @Override
  public void push(T item) {
    if(this.size < this.maxDepth) {
      this.array[size] = item;
      size++;
    }
  }

  @Override
  public T pop() {
    if (this.size > 0) {
      T value = this.array[--size];
      return value;
    } else {
      return null;
    }
  }

  @Override
  public int getStackSize() {
    return this.size;
  }

  @Override
  public String toString() {
    String str = "Stack: ";
    for (int i = 0; i < size; i++) {
      str = str + this.array[i] + " ";
    }
    return str;
  }

  public static <T> ArrayStack<T> of(T arr[], int depth) {
    ArrayStack<T> stack = new ArrayStack<>(depth);
    for (int i = 0; i < depth && i < arr.length; i++) {
      stack.push(arr[i]);
    }
    return stack;
  }
   
  public void pushAll(ArrayStack<? extends T> src) {
    while (src.getStackSize() > 0) {
      this.push(src.pop());
    }
  }

  public void popAll(ArrayStack<? super T> dest) {
    while(this.getStackSize() > 0) {
      dest.push(this.pop());
    }
  }
   



}







