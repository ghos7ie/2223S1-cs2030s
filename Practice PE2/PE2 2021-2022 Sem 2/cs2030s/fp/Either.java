/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

abstract class Either<L,R>{
  protected L leftVal;
  protected R rightVal;

  protected Either(L value){
    this.leftVal = value;
    this.rightVal = null;
  }

  protected Either(R value){
    this.leftVal = null;
    this.rightVal = value;
  }

  // abstract mehtods
  public abstract boolean isLeft();
  public abstract boolean isRight();
  public abstract L getLeft();
  public abstract R getRight();

  public static <L,R> Either<L,R> left(L value){
    return new Left(value);
  }

  public static <L,R> Either<L,R> right(R value){
    return new Right(value);
  }

  private static class Left<L,R> extends Either<L,R>{
    protected Left(L val){
      super(val);
    }

    @Override
    public boolean isLeft(){
      return true;
    }
    
    @Override
    public boolean isRight(){
      return false;
    }

    @Override
    public L getLeft(){
      return this.leftValue;
    }

    @Override
    public R getRight(){
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj){
      if (obj == this){
        return true;
      }
      if (obj instanceof Left){
        return true;
      }
      if (obj instanceof Right){
        return this.leftValue == obj.rightValue;  
      }
      return false;
    }

    @Override
    public String toString(){
      return "Left[" + this.leftValue.toString() + "]";
    }
  
  }

  private static class Right<L,R> extends Either<L,R>{
    protected Right(R val){
      super(val);
    }
  }

}
