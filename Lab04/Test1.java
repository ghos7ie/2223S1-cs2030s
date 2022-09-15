import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    PrintStream old = System.out;
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    new Print().call(17);
    we.expectPrint("new Print().call(17)",
                   "17",
                   baos,
                   old);
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    new Print().call("string");
    we.expectPrint("new Print().call(\"string\")",
                   "string",
                   baos,
                   old);
  }
}