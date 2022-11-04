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

  public abstract <U, V> Either<U, V> map(Transformer<? super L, ? extends U> left,
      Transformer<? super R, ? extends V> right);

  public abstract <U, V> Either<U, V> flatMap(Transformer<? super L, ? extends Either<? extends U, ? extends V>> left,
      Transformer<? super R, ? extends Either<? extends U, ? extends V>> right);

  public abstract <U> U fold(Transformer<? super L, ? extends U> left,
      Transformer<? super R, ? extends U> right);

  public abstract Either<L, R> filterOrElse(BooleanCondition<? super R> cond,
      Transformer<? super R, ? extends L> transformerR);

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
        return left.value.equals(this.value);
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

    @Override
    public <U, V> Either<U, V> map(Transformer<? super L, ? extends U> left,
        Transformer<? super Object, ? extends V> right) {
      return left(left.transform(this.value));
    }

    @Override
    public <U, V> Either<U, V> flatMap(Transformer<? super L, ? extends Either<? extends U, ? extends V>> left,
        Transformer<? super Object, ? extends Either<? extends U, ? extends V>> right) {
      // TODO Auto-generated method stub
      @SuppressWarnings("unchecked")
      Either<U, V> trans = (Either<U, V>) left(left.transform(this.value));
      return trans;
    }

    @Override
    public <U> U fold(Transformer<? super L, ? extends U> left, Transformer<? super Object, ? extends U> right) {
      // TODO Auto-generated method stub
      return left.transform(this.value);
    }

    @Override
    public Either<L, Object> filterOrElse(BooleanCondition<? super Object> cond,
        Transformer<? super Object, ? extends L> transformerR) {
      // TODO Auto-generated method stub
      return this;
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
        return right.value.equals(this.value);
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

    @Override
    public <U, V> Either<U, V> map(Transformer<? super Object, ? extends U> left,
        Transformer<? super R, ? extends V> right) {
      return right(right.transform(this.value));
    }

    @Override
    public <U, V> Either<U, V> flatMap(Transformer<? super Object, ? extends Either<? extends U, ? extends V>> left,
        Transformer<? super R, ? extends Either<? extends U, ? extends V>> right) {
      @SuppressWarnings("unchecked")
      Either<U, V> trans = (Either<U, V>) right(right.transform(this.value));
      return trans;
    }

    @Override
    public <U> U fold(Transformer<? super Object, ? extends U> left, Transformer<? super R, ? extends U> right) {
      // TODO Auto-generated method stub
      return right.transform(this.value);
    }

    @Override
    public Either<Object, R> filterOrElse(BooleanCondition<? super R> cond,
        Transformer<? super R, ? extends Object> transformer) {
      // TODO Auto-generated method stub
      if (cond.test(this.value)) {
        return this;
      } else {
        return left(transformer.transform(this.value));
      }
    }
  }

}