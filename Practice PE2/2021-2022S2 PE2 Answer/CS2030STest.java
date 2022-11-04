import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import javax.tools.DiagnosticCollector;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * A helper class to test CS2030S labs.
 */
class CS2030STest {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";

  private void ok() {
    System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
  }

  private void fail() {
    System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
  }

  /**
   * Test if two objects are equals.
   *
   * @param test A description of the test.
   * @param output The output from an expression.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public CS2030STest expect(String test, Object output, Object expect) {
    System.out.print(test);
    if ((expect == null && output == null) || output.equals(expect)) {
      ok();
    } else {
      fail();
      System.out.println("  expected: " + expect);
      System.out.println("  got this: " + output);
    }
    return this;
  }

  /**
   * Test if a given supplier produces a given object.
   *
   * @param <T> The type of object the given task will produce.
   * @param test A description of the test.
   * @param task The task to run.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public <T> CS2030STest expect(String test, Supplier<T> task, Object expect) {
    System.out.print(test);
    try {
      T output = CompletableFuture.supplyAsync(() -> {
        try {
          return task.get();
        } catch (Throwable e) {
          // shouldn't reach here
          fail();
          System.out.println("  with exception: " + e.getMessage());
          e.printStackTrace();
          return null;
        }
      }
      ).get(1, TimeUnit.SECONDS);
      if ((expect == null && output == null) || output.equals(expect)) {
        ok();
      } else {
        fail();
        System.out.println("  expected: " + expect);
        System.out.println("  got this: " + output);
      }
    } catch (Throwable e) {
      fail();
      System.out.println("  with exception: " + e.getMessage());
      e.printStackTrace();
    }
    return this;
  }

  /**
   * Test if a given producer returns a value.  Wrapper around expect(..)
   * to simplify caller.
   *
   * @param <T> The type of object the given task will produce.
   * @param test A description of the test.
   * @param task The task to run.
   * @param expect The expected output from that expression.
   * @return this object.
   */
  public <T> CS2030STest expectReturn(String test, Supplier<T> task, Object expect) {
    return this.expect(test + " returns " + expect, task, expect);
  }

  /**
   * Test if an expression throws an exception.
   *
   * @param test A description of the test.
   * @param task A lambda expression of the expression.
   * @param expectedE A exception instance of the same type as the expected one.
   * @return this object.
   */
  public CS2030STest expectException(String test, Runnable task, Exception expectedE) {
    System.out.print(test + " throws " + expectedE.getClass().getSimpleName());
    boolean gotException = false;
    try {
      task.run();
    } catch (Throwable e) {
      if (e.getClass().equals(expectedE.getClass())) {
        gotException = true;
      }
    }
    if (gotException) {
      ok();
    } else {
      fail();
      System.out.println("  did not catch expected exception " + expectedE.getClass());
    }
    return this;
  }

  /**
   * Test if an expression compiles with/without error.
   *
   * @param test A description of the test.
   * @param statement The java statement to compile
   * @param success Whether the statement is expected to compile or not 
   *     (true if yes; false otherwise)
   * @return this object.
   */
  public CS2030STest expectCompile(String test, String statement, boolean success) {
    System.out.print(test);

    class Code extends SimpleJavaFileObject {
      final String code;

      Code(String code) {
        super(URI.create("string:///TestCompile.java"), Kind.SOURCE);
        this.code = "class TestCompile {void test(){\n  " +  code + ";\n}}";
      }

      @Override
      public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
      }
    }

    boolean noError = ToolProvider
        .getSystemJavaCompiler()
        .getTask(null, null, new DiagnosticCollector<>(), null, null, 
           List.of(new Code(statement)))
        .call();

    if (noError != success) {
      fail();
      if (!success) {
        System.out.println("  expected compilation error but it compiles fine.");
      } else {
        System.out.println("  expected the statement to compile without errors but it does not.");
      }
    } else {
      ok();
    }
    return this;
  }
}
