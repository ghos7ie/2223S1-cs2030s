import java.util.List;
import cs2030s.fp.Immutator;
import cs2030s.fp.Reader;

/**
 * Test 1 for CS2030S Mock PE 2 (AY22/23 Sem 2).  Tests
 * for Reader of(), read(), and hasNext() methods
 *
 * @author Adi Yoga S. Prabawa
 */
class Test1 {
  /**
   * Main method for Test1.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    
    Reader<Integer> intInput = Reader.of(2, 0, 3, 0);
    i.expect("Reader<Integer> intInput = Reader.of(2, 0, 3, 0)", Reader.of(2, 0, 3, 0).toString(), "Reader");
    i.expect("intInput.read()", intInput.read(), 2);
    i.expect("intInput.read() to still read the same thing", intInput.read(), 2);
    i.expect("intInput.hasNext()", intInput.hasNext(), true);

    Reader<Object> noInput = Reader.of();
    i.expect("Reader<Object> noInput = Reader.of()", Reader.of().toString(), "EOF");
    i.expectException("noInput.read()", () -> { noInput.read(); return; }, new java.util.NoSuchElementException());
    i.expect("intInput.hasNext()", noInput.hasNext(), false);

    Reader<String> strInput = Reader.of("CS", "2030", "S");
    i.expect("Reader<String> strInput = Reader.of(\"CS\", \"2030\", \"S\")", Reader.of("CS", "2030", "S").toString(), "Reader");
    i.expect("strInput.read()", strInput.read(), "CS");
    i.expect("strInput.read() to still read the same thing", strInput.read(), "CS");
    i.expect("strInput.hasNext()", strInput.hasNext(), true);
  }
}
