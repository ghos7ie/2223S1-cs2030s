import cs2030s.fp.Either;
import java.util.List;

class Test2 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Either.<Integer, String>left(2).map(i -> i + 2, s -> s + \" + 2\")",
        () -> Either.<Integer, String>left(2).map(x -> x + 2, s -> s + " + 2"), 
        Either.left(4));

    i.expectReturn("Either.<Integer, String>right(\"2\").map(i -> i + 2, s -> s + \" + 2\")",
        () -> Either.<Integer, String>right("2").map(x -> x + 2, s -> s + " + 2"),
        Either.right("2 + 2"));

    i.expectCompile("Either<Number, Number> enn = Either.left(2).map(hash, hash);",
        "cs2030s.fp.Transformer<Object, Integer> hash = o -> o.hashCode();\n" +
        "cs2030s.fp.Either<Number, Number> enn = cs2030s.fp.Either.left(2).map(hash, hash);",
        true);

    i.expectCompile("Either<Number, Number> enn = Either.right(2).map(hash, hash);",
        "cs2030s.fp.Transformer<Object, Integer> hash = o -> o.hashCode();\n" +
        "cs2030s.fp.Either<Number, Number> enn = cs2030s.fp.Either.right(2).map(hash, hash);",
        true);

    String strOrHash = "cs2030s.fp.Transformer<Object,cs2030s.fp.Either<String,Integer>>"  +
        "strOrHash = o -> (o.equals(8) ?\n" +
        "cs2030s.fp.Either.<String, Integer>left(o.toString()) : \n" +
        "cs2030s.fp.Either.<String, Integer>right(o.hashCode()));\n";

    i.expectReturn("Either.<Integer, String>left(2)" + 
        ".flatMap(i -> Either.left(i + 2), s -> Either.right(s + \" + 2\"));",
        () -> Either.<Integer, String>left(2)
        .flatMap(x -> Either.left(x + 2), s -> Either.right(s + " + 2")),
        Either.left(4));

    i.expectReturn("Either.<Integer, String>right(\"2\")" +
        ".flatMap(i -> Either.left(i + 2), s -> Either.right(s + \" + 2\"));",
        () -> Either.<Integer, String>right("2")
        .flatMap(x -> Either.left(x + 2), s -> Either.right(s + " + 2")),
        Either.right("2 + 2"));

    i.expectCompile("Either<Object, Number> enn = Either.left(2).flatMap(strOrHash, strOrHash);",
        strOrHash + "cs2030s.fp.Either<Object, Number> enn = " +
        "cs2030s.fp.Either.left(2).flatMap(strOrHash, strOrHash);",
        true);
    i.expectCompile("Either<Object, Number> enn = Either.left(8).flatMap(strOrHash, strOrHash);",
        strOrHash + "cs2030s.fp.Either<Object, Number> enn = " + 
        "cs2030s.fp.Either.left(8).flatMap(strOrHash, strOrHash);",
        true);
    i.expectCompile("Either<Object, Number> enn = Either.right(2).flatMap(strOrHash, strOrHash);",
        strOrHash + "cs2030s.fp.Either<Object, Number> enn = " +
        "cs2030s.fp.Either.right(2).flatMap(strOrHash, strOrHash);",
        true);
    i.expectCompile("Either<Object, Number> enn = Either.right(8).flatMap(strOrHash, strOrHash);",
        strOrHash + "cs2030s.fp.Either<Object, Number> enn = " +
        "cs2030s.fp.Either.right(8).flatMap(strOrHash, strOrHash);",
        true);

    i.expectReturn("Either.<List<Integer>, String>left(List.of(1,2,3))" + 
        ".fold(l -> l.size(), s -> s.length())",
        () -> Either.<List<Integer>, String>left(List.of(1, 2, 3))
        .fold(l -> l.size(), s -> s.length()),
        3);

    i.expectReturn("Either.<List<Integer>, String>right(\"hello there\")" +
        ".fold(l -> l.size(), s -> s.length())",
        () -> Either.<List<Integer>, String>right("hello there")
        .fold(l -> l.size(), s -> s.length()),
        11);

    i.expectCompile("Either.<List<Integer>, String>left(List.of(1,2,3))" +
        ".<Number>fold(hash, hash)",
        "cs2030s.fp.Transformer<Object, Integer> hash = o -> o.hashCode();\n" +
        "cs2030s.fp.Either.<java.util.List<Integer>, String>left(java.util.List.of(1,2,3))" +
        ".<Number>fold(hash, hash);",
        true);

    i.expectCompile("Either.<List<Integer>, String>right(\"hello there\")" +
        ".<Number>fold(hash, hash)",
        "cs2030s.fp.Transformer<Object, Integer> hash = o -> o.hashCode();\n" +
        "cs2030s.fp.Either.<java.util.List<Integer>, String>right(\"hello there\")" +
        ".<Number>fold(hash, hash);",
        true);

    i.expectReturn("Either.<String, Boolean>left(\"no change\")" +
        ".filterOrElse(x -> x == true, x -> \"\");",
        () -> Either.<String, Boolean>left("no change").filterOrElse(x -> x == true, x -> ""),
        Either.left("no change"));

    i.expectReturn("Either.<String, Boolean>right(true)" + 
        ".filterOrElse(x -> x == true, x -> \"is false\");",
        () -> Either.<String, Boolean>right(true).filterOrElse(x -> x == true, x -> "is false"),
        Either.right(true));

    i.expectReturn("Either.<String, Boolean>right(false)" + 
        ".filterOrElse(x -> x == true, x -> \"is false\")",
        () -> Either.<String, Boolean>right(false).filterOrElse(x -> x == true, x -> "is false"),
        Either.left("is false"));

    String lambdas = "cs2030s.fp.Transformer<Object, Exception> toException = " 
        + "o -> new IllegalStateException(o + \" is illegal\");"
        + "cs2030s.fp.BooleanCondition<Number> isPositive = n -> n.intValue() > 0;";
    i.expectCompile("Either.<Throwable, Integer>left(new IllegalStateException())" + 
        ".filterOrElse(isPositive, toException)",
        lambdas + "cs2030s.fp.Either.<Throwable, Integer>left(new IllegalStateException())" + 
        ".filterOrElse(isPositive, toException);",
        true);
    i.expectCompile("Either.<Throwable, Integer>right(0)" +
        ".filterOrElse(isPositive, toException)",
        lambdas + "cs2030s.fp.Either.<Throwable, Integer>right(0)" +
        ".filterOrElse(isPositive, toException)",
        true);
    i.expectCompile("Either.<Throwable, Integer>right(8)" + 
        ".filterOrElse(isPositive, toException);",
        lambdas + "cs2030s.fp.Either.<Throwable, Integer>right(8)" +
        ".filterOrElse(isPositive, toException)",
        true);
  }
}
