import cs2030s.fp.Either;
import java.util.NoSuchElementException;

class Test1 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectCompile("new Either<>() cannot compile",
        "new cs2030s.fp.Either<>()",
        false);

    i.expectReturn("Either.right(\"two\").isLeft()",
        () -> Either.right("two").isLeft(), false);
    i.expectReturn("Either.right(\"two\").isRight()",
        () -> Either.right("two").isRight(), true);
    i.expectReturn("Either.right(\"two\").getRight()",
        () -> Either.right("two").getRight(), "two");
    i.expectReturn("Either.left(2).isLeft()",
        () -> Either.left(2).isLeft(), true);
    i.expectReturn("Either.left(2).isRight()",
        () -> Either.left(2).isRight(), false);
    i.expectReturn("Either.left(2).getLeft()",
        () -> Either.left(2).getLeft(), 2);

    i.expectException("Either.left(2).getRight()",
        () -> Either.left(2).getRight(),
        new NoSuchElementException());

    i.expectException("Either.right(\"two\").getLeft()",
        () -> Either.right("two").getLeft(), 
        new NoSuchElementException());

    i.expectCompile("Either<String, Integer> e = Either.left(2) cannot compile",
        "cs2030s.fp.Either<String, Integer> e = cs2030s.fp.Either.left(2);",
        false);
    i.expectCompile("Either<Double, Long> e = Either.right(true) cannot compile", 
        "cs2030s.fp.Either<Double, Long> e = cs2030s.fp.Either.right(true);",
        false);

    i.expectReturn("Either.right(new String(\"two\")).equals(Either.right(\"two\"))",
        () -> Either.right(new String("two")).equals(Either.right("two")), 
        true);

    i.expectReturn("Either.right(new String(\"two\")).equals(Either.left(\"two\"))",
        () -> Either.right(new String("two")).equals(Either.left("two")),
        false);

    i.expectReturn("Either.left(new String(\"two\")).equals(Either.right(\"two\"))",
        () -> Either.left(new String("two")).equals(Either.right("two")),
        false);

    i.expectReturn("Either.left(new String(\"two\")).equals(Either.left(\"two\"))",
        () -> Either.left(new String("two")).equals(Either.left("two")),
        true);

    i.expectReturn("Either.right(new String(\"two\")).equals(Either.right(2))",
        () -> Either.right(new String("two")).equals(Either.right(2)),
        false);

    i.expectReturn("Either.left(new String(\"two\")).equals(Either.left(2))",
        () -> Either.left(new String("two")).equals(Either.left(2)),
        false);

    i.expectReturn("Either.left(null).equals(Either.left(null))",
        () -> Either.left(null).equals(Either.left(null)),
        true);

    i.expectReturn("Either.right(null).equals(Either.right(null))",
        () -> Either.right(null).equals(Either.right(null)),
        true);

    i.expectReturn("Either.right(20).toString()",
        () -> Either.right(20).toString(),
        "Right[20]");

    i.expectReturn("Either.left(\"thirty\").toString()",
        () -> Either.left("thirty").toString(),
        "Left[thirty]");
  }
}
