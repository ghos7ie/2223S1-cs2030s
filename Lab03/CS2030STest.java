import java.net.URI;
import java.util.List;
import javax.tools.DiagnosticCollector;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

class CS2030STest {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

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
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  expected: " + expect);
      System.out.println("  got this: " + output);
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

    class JavaSourceFromString extends SimpleJavaFileObject {
      final String code;

      JavaSourceFromString(String code) {
        super(URI.create("string:///TempClass.java"), Kind.SOURCE);
        this.code = "class TempClass {void foo(){" +  code + ";}}";
      }

      @Override
      public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
      }
    }

    boolean noError = ToolProvider
        .getSystemJavaCompiler()
        .getTask(null, null, new DiagnosticCollector<>(), null, null, 
            List.of(new JavaSourceFromString(statement)))
        .call();

    if (noError != success) {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      if (!success) {
        System.out.println("  expected compilation error but it compiles fine.");
      } else {
        System.out.println("  expected the statement to compile without errors but it does not.");
      }
    } else {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    }
    return this;
  }
}
