/**
 * @author A0000000X
 */
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

abstract class Reader<T> {
  protected final List<T> inputs;
  protected Reader(List<T> inputs) {
    this.inputs = inputs;
  }
  protected Reader() {
    this.inputs = null;
  }
    
  private static final Reader<?> EOF = new EOF();
  
  public abstract T read();
  public abstract Reader<T> consume();
  public abstract boolean hasNext();
  public abstract <R> Reader<R> map(Immutator<? extends R, ? super T> f);
  public abstract Reader<T> flatMap(Immutator<? extends Reader<? extends T>, ? super T> f);
    
  @SafeVarargs
  public static <T> Reader<T> of(T... inputs) {
    List<T> currInputs = new ArrayList<T>();
    for (int i=0; i<inputs.length; i++) {
      currInputs.add(inputs[i]);
    }
    return new NonEmpty<>(currInputs);
  }
  
  public static <T> Reader<T> of() {
    @SuppressWarnings("unchecked")
    Reader<T> res = (Reader<T>) EOF;
    return res;
  }
  
  private static class NonEmpty<T> extends Reader<T> {
    protected NonEmpty(List<T> inputs) {
      super(inputs);
    }
    
    @Override
    public T read() {
      return this.inputs.get(0);
    }
    
    @Override
    public boolean hasNext() {
      return true;
    }
    
    @Override
    public Reader<T> consume() {
      if (this.inputs.size() == 1) {
        return Reader.<T>of();
      }
      List<T> currInputs = new ArrayList<T>(this.inputs);
      currInputs.remove(0);
      return new NonEmpty<>(currInputs);
    }
    
    @Override
    public <R> Reader<R> map(Immutator<? extends R, ? super T> f) {
      List<R> currInputs = new ArrayList<R>();
      for (int i=0; i<this.inputs.size(); i++) {
        currInputs.add(f.invoke(this.inputs.get(i)));
      }
      return new NonEmpty<>(currInputs);
    }
    
    @Override
    public Reader<T> flatMap(Immutator<? extends Reader<? extends T>, ? super T> f) {
      Reader<? extends T> tmp = f.invoke(this.inputs.get(0));
      List<T> currInputs = new ArrayList<T>();
      for (int i=0; i<tmp.inputs.size(); i++) {
        currInputs.add(tmp.inputs.get(i));
      }
      for (int i=1; i<this.inputs.size(); i++) {
        currInputs.add(this.inputs.get(i));
      }
      return new NonEmpty<>(currInputs);
    }
    
    @Override
    public String toString() {
      return "Reader";
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof NonEmpty<?>)) {
        return false;
      }
      return true;
    }
  }
  
  private static class EOF extends Reader<Object> {
    @Override
    public Object read() {
      throw new NoSuchElementException();
    }
    
    @Override
    public Reader<Object> consume() {
      throw new NoSuchElementException();
    }
    
    @Override
    public boolean hasNext() {
      return false;
    }
    
    @Override
    public <R> Reader<R> map(Immutator<? extends R, Object> f) {
      return Reader.of();
    }
    
    @Override
    public Reader<Object> flatMap(Immutator<? extends Reader<? extends Object>, ? super Object> f) {
      return Reader.of();
    }
    
    @Override
    public String toString() {
      return "EOF";
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof EOF)) {
        return false;
      }
      return true;
    }
  }
}