package cs2030s.fp;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * @author A0253229E
 */

public abstract class Reader<T> {

  public abstract T read();
  public abstract boolean hasNext();

  @SafeVarargs
  public static <T> Reader<T> of (T... args){
    List<T> list = new ArrayList<T>();
    for (int i = 0; i < args.length; i++){
      list.add(args[i]);
    }
    return new NonEmpty<>(list);
  }

  public static <T>  Reader<T> of (){
    @SuppressWarnings("unchecked")
    Reader<T> empty = (Reader<T>) new Empty();
    return empty;
  }

  static final class NonEmpty<T> extends Reader<T>{
    private List<T> elementList;

    public NonEmpty(List<T> list){
      this.elementList = list;
    }

    @Override
    public T read(){
      return this.elementList.get(0);
    }

    @Override
    public boolean hasNext(){
      return true;
    }

    @Override
    public NonEmpty<T> consume(){
      return Reader.of(

    @Override
    public boolean equals(Object obj){
      if (obj == this){
        return true;
      }
      if (obj instanceof NonEmpty){
        return true;
      }else{
        return false;
      }
    }

    @Override
    public String toString(){
      return "Reader";
    }
   
  }

  static final class Empty extends Reader<Object>{
    public Empty(){  
    }

    @Override
    public Object read(){
      throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext(){
      return false;
    }

    @Override
    public Object consume(){
      throw new NoSuchEelementException();
    }

    @Override 
    public boolean equals(Object obj){
      if (obj == this){
        return true;
      }
      if (obj instanceof Empty){
        return true;
      }else{
        return false;
      }
    }
    @Override
    public String toString(){
      return "EOF";
    }
  }

}
