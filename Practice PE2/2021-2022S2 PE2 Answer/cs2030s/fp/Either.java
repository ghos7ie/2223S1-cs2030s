package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S PE2 Question 1
 * AY21/22 Semester 2
 * Either API class.
 *
 * @author [Ong Seeu Sim](A0239298L) 
 */
public abstract class Either<T, U> {

  /**
   * Create a left, i.e., a new `Either` monad with a left value.
   * Given an Either with two `left` and `right` types, return an
   * instance with the given value stored in a `left` Either.
   *
   * @param <T> The type of the `left` Either.
   * @param <U> The type of the `right` Either.
   * @param t The value to insert.
   * @return The resulting `left` Either.
   */
  public static <T, U> Either<T, U> left(T t) {
    return new Left<T, U>(t); 
  }
  
  /**
   * Create a right, i.e., a new `Either` monad with a right value.
   * Given an Either with two `left` and `right` types, return an
   * instance with the given value stored in a `right` Either.
   *
   * @param <T> The type of the `left` Either.
   * @param <U> The type of the `right` Either.
   * @param u The value to insert.
   * @return The resulting `right` Either.
   */
  public static <T, U> Either<T, U> right(U u) {
    return new Right<T, U>(u);
  }
  
  /**
   * Indicate if the current `Either` instance is a `left` Either instance.
   *
   * @return The indicative boolean.
   */
  public abstract boolean isLeft();

  /**
   * Indicate if the current `Either` instance is a `right` Either instance.
   *
   * @return The indicative boolean.
   */
  public abstract boolean isRight();
  
  /**
   * Retrieve the value from this Either instance, if it is a `left` Either 
   * instance.
   * Else, throws a NoSuchElementException.
   *
   * @return The `left` value.
   */
  public abstract T getLeft();


  /**
   * Retrieve the value from this Either instance, if it is a `right` Either 
   * instance.
   * Else, throws a NoSuchElementException.
   *
   * @return The `right` value.
   */
  public abstract U getRight();

  /**
   * Takes in two `Transformer`s so that they can be applied computation on the 
   * content of `Either`.  
   * If `map` is called on an `Either` instance that is a `left` it will apply 
   * the first `Transformer`, and if it is a `right` it will apply the second 
   * `Transformer`.
   * Returns the Either wrapped around the transformed value.
   *
   * @param <A> The output type of the first function.
   * @param <B> The output type of the second function.
   * @param leftMapper The first function.
   * @param rightMapper The second function.
   * @return The resulting Either.
   */
  public abstract <A, B> Either<A, B> map(
      Transformer<? super T, ? extends A> leftMapper,
      Transformer<? super U, ? extends B> rightMapper);

  /**
   * Takes in two `Transformer`s so that we can compose multiple methods that 
   * produce a `Either` together.  
   * If `flatMap` is called on a `left` Either, it will apply the first 
   * transformer. Otherwise it will apply the second transformer.
   * Returns the output Either by applying the function on the value contained.
   *
   * @param <A> The output type of the first function.
   * @param <B> The output type of the second function.
   * @param lM The first function.
   * @param rM The second function.
   * @return the resulting Either.
   */
  public abstract <A, B> Either<A, B> flatMap(
      Transformer<? super T, 
        ? extends Either<? extends A, ? extends B>> lM,
      Transformer<? super U,
        ? extends Either<? extends A, ? extends B>> rM);
  
  /**
   * Takes in two `Transformer`s and folds the two possible types into a common 
   * type.
   * If this is a `left` Either, apply the first Transformer, else apply the 
   * second Transformer.
   *
   * @param <S> The output type.
   * @param leftMapper The function that acts on the `left` instance of this 
   *     Either.
   * @param rightMapper The function that acts on the `right` instance of this
   *     Either.
   * @return The output value given by applying the respective function.
   */
  public abstract <S> S fold(
      Transformer<? super T, ? extends S> leftMapper,
      Transformer<? super U, ? extends S> rightMapper);

  /**
   * Takes in a `BooleanCondition` and a `Transformer` and applies them on the
   * contained value.
   * If this is a `left` Either, returns the current instance.
   * If this is a `right` Either, returns one of the two:
   * 1. If the contained value passes the given predicate, return the current  
   *     instance.
   * 2. Else, apply the given function on the value and convert the current 
   *     instance into a `left` Either.
   *
   * @param pred The given predicate.
   * @param f The function to convert a `right` Either into a `left` Either.
   * @return The resulting Either instance according to the above rules.
   */
  public abstract Either<T, U> filterOrElse(
      BooleanCondition<? super U> pred,
      Transformer<? super U, ? extends T> f);
        

  private static final class Left<T, U> extends Either<T, U> {
    private final T value;

    private Left(T t) {
      this.value = t;
    }

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public T getLeft() {
      return this.value;
    }

    @Override 
    public U getRight() {
      throw new NoSuchElementException();
    }
    
    /**
     * Overriden Object::equals method for use with other `Either` instances.
     * Returns true if the input object is an instance of a `left` Either that
     * contains a semantically equal value.
     *
     * @param o The object to compare to.
     * @return The indicative boolean.
     */
    @Override
    public boolean equals(Object o) {
      if (o instanceof Left<?, ?>) {
        Left<?, ?> other = (Left<?, ?>) o;

        if (this.value == null) {
          return this.value == other.value;
        }
        return this.value.equals(other.value);
      }
      return false;
    }

    @Override
    public <A, B> Either<A, B> map(
        Transformer<? super T, ? extends A> leftMapper,
        Transformer<? super U, ? extends B> rightMapper) {
      return new Left<A, B>(
          leftMapper.transform(this.value));
    }
    
    @Override
    public <A, B> Either<A, B> flatMap(
        Transformer<? super T, 
          ? extends Either<? extends A, ? extends B>> lM,
        Transformer<? super U,
          ? extends Either<? extends A, ? extends B>> rM) {
      @SuppressWarnings("unchecked")
      Either<A, B> out = (Either<A, B>) lM.transform(this.value);
      return out;
    }

    @Override
    public <S> S fold(
        Transformer<? super T, ? extends S> leftMapper,
        Transformer<? super U, ? extends S> rightMapper) {
      return leftMapper.transform(this.value);
    }      
    
    @Override
    public Either<T, U> filterOrElse(
        BooleanCondition<? super U> pred,
        Transformer<? super U, ? extends T> f) {
      return this;
    }

    /**
     * Overriden Object::toString for a human friendly representation of this 
     * `Either` instance.
     *
     * @return The value in this format, as a String: "Left[value]"
     */
    @Override
    public String toString() {
      return "Left[" + this.value + "]";
    }

  }

  private static final class Right<T, U> extends Either<T, U> {
    private final U value;

    private Right(U u) {
      this.value = u;
    }

    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public boolean isRight() {
      return true;
    }

    @Override 
    public T getLeft() {
      throw new NoSuchElementException();
    }

    @Override
    public U getRight() {
      return this.value;
    }
    
    /**
     * Overriden Object::equals method for use with other `Either` instances.
     * Returns true if the input object is an instance of a `right` Either that
     * contains a semantically equal value.
     *
     * @param o The object to compare to.
     * @return The indicative boolean.
     */
    @Override
    public boolean equals(Object o) {
      if (o instanceof Right<?, ?>) {
        Right<?, ?> other = (Right<?, ?>) o;

        if (this.value == null) {
          return this.value == other.value;
        }
        return this.value.equals(other.value);
      }
      return false;
    }

    @Override
    public <A, B> Either<A, B> map(
        Transformer<? super T, ? extends A> leftMapper,
        Transformer<? super U, ? extends B> rightMapper) {
      return new Right<A, B>(
          rightMapper.transform(this.value));
    }
    
    @Override
    public <A, B> Either<A, B> flatMap(
        Transformer<? super T, 
          ? extends Either<? extends A, ? extends B>> lM,
        Transformer<? super U,
          ? extends Either<? extends A, ? extends B>> rM) {
      @SuppressWarnings("unchecked")
      Either<A, B> out = (Either<A, B>) rM.transform(this.value);
      return out;
    }

    @Override
    public <S> S fold(
        Transformer<? super T, ? extends S> leftMapper,
        Transformer<? super U, ? extends S> rightMapper) {
      return rightMapper.transform(this.value);
    } 
    
    @Override
    public Either<T, U> filterOrElse(
        BooleanCondition<? super U> pred,
        Transformer<? super U, ? extends T> f) {
      if (pred.test(this.value)) {
        return this;
      }
      return super.left(f.transform(this.value));
    }
    
    /**
     * Overriden Object::toString for a human friendly representation of this 
     * `Either` instance.
     *
     * @return The value in this format, as a String: "Right[value]"
     */
    @Override
    public String toString() {
      return "Right[" + this.value + "]";
    }
  }
}
