package cs2030s.fp;

@FunctionalInterface
public interface BooleanCondition<T> {
  boolean test(T t);
}