import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    PrintStream old = System.out;
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    Probably.just(4).act(new Print());
    we.expectPrint("Probably.just(4).act(new Print())",
                   "4",
                   baos,
                   old);
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    Probably.just("string").act(new Print());
    we.expectPrint("Probably.just(\"string\").act(new Print())",
                   "string",
                   baos,
                   old);
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    Probably.none().act(new Print());
    we.expectPrint("Probably.none().act(new Print())",
                   "",
                   baos,
                   old);
  }
}