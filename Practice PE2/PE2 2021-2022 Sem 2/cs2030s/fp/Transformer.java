package cs2030s.fp;

@FunctionalInterface
public interface Transformer<U, T> {
  T transform(U u);
}