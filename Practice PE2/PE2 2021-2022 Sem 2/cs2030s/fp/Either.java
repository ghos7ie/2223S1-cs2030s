/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Either<L, R> {

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract L getLeft();

  public abstract R getRight();

  public static <L, R> Either<L, R> left(L value) {
    @SuppressWarnings("unchecked")
    Either<L, R> either = (Either<L, R>) new Left<L>(value);
    return either;
  }

  public static <L, R> Either<L, R> right(R value) {
    @SuppressWarnings("unchecked")
    Either<L, R> either = (Either<L, R>) new Right<R>(value);
    return either;

  }

  private static class Left<L> extends Either<L, Object> {

    private L value;

    private Left(L value) {
      this.value = value;
    }

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
        Left<?> left = (Left<?>) obj;
        if (left.value == this.value) {
          return true;
        }
        if (left.value == null) {
          return false;
        }
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

    private Right(R value) {
      this.value = value;
    }

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
        Right<?> right = (Right<?>) obj;
        if (right.value == this.value) {
          return true;
        }
        if (right.value == null) {
          return false;
        }
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