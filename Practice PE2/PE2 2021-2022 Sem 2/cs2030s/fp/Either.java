/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

abstract class Either<L, R> {

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract L getLeft();

  public abstract R getRight();

  public static <L, R> Either<L, R> left(L value) {

  }

  public static <L, R> Either<L, R> right(R value) {

  }

  private static class Left<L> extends Either<L, Object> {

    private L value;

    @Override
    public boolean isLeft() {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public boolean isRight() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public L getLeft() {
      // TODO Auto-generated method stub
      return this.value;
    }

    @Override
    public Object getRight() {
      // TODO Auto-generated method stub
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Left<?>) {
        return true;
      }
      if (obj instanceof Right<?>) {
        Right<?> right = (Right<?>) obj;
        return right.value == this.value;
      }
      return false;
    }

    @Override
    public String toString() {
      return "Left[" + this.value.toString() + "]";
    }
  }

  private static class Right<R> extends Either<Object, R> {
    private R value;

    @Override
    public boolean isLeft() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean isRight() {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public Object getLeft() {
      // TODO Auto-generated method stub
      throw new NoSuchElementException();
    }

    @Override
    public R getRight() {
      // TODO Auto-generated method stub
      return this.value;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Right<?>) {
        return true;
      }
      if (obj instanceof Left<?>) {
        Left<?> right = (Left<?>) obj;
        return right.value == this.value;
      }
      return false;
    }

    @Override
    public String toString() {
      return "Right[" + this.value.toString() + "]";
    }
  }

}