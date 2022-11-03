import java.util.List;
import cs2030s.fp.Immutator;
import cs2030s.fp.Reader;

/**
 * Test 2 for CS2030S Mock PE 2 (AY22/23 Sem 2).  Tests
 * for Reader consume() methods
 *
 * @author Adi Yoga S. Prabawa
 */
class Test2 {
  /**
   * Main method for Test2.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    
    Reader<Integer> intInput = Reader.of(2, 0, 3, 0);
    i.expect("Reader<Integer> intInput = Reader.of(2, 0, 3, 0)", Reader.of(2, 0, 3, 0).toString(), "Reader");
    i.expect("intInput.read()", intInput.read(), 2);
    i.expect("intInput.consume().read()", intInput.consume().read(), 0);
    i.expect("intInput.consume().consume().read()", intInput.consume().consume().read(), 3);
    i.expect("intInput.consume().consume().consume().read()", intInput.consume().consume().consume().read(), 0);
    i.expectException("intInput.consume().consume().consume().consume().read()", () -> { intInput.consume().consume().consume().consume().read(); return; }, new java.util.NoSuchElementException());
    i.expectException("intInput.consume().consume().consume().consume().consume()", () -> { intInput.consume().consume().consume().consume().consume(); return; }, new java.util.NoSuchElementException());
    

    Reader<Object> noInput = Reader.of();
    i.expect("Reader<Object> noInput = Reader.of()", Reader.of().toString(), "EOF");
    i.expectException("noInput.read()", () -> { noInput.read(); return; }, new java.util.NoSuchElementException());
    i.expectException("noInput.consume()", () -> { noInput.read(); return; }, new java.util.NoSuchElementException());

    Reader<String> strInput = Reader.of("CS", "2030", "S");
    i.expect("Reader<String> strInput = Reader.of(\"CS\", \"2030\", \"S\")", Reader.of("CS", "2030", "S").toString(), "Reader");
    i.expect("strInput.read()", strInput.read(), "CS");
    i.expect("strInput.consume().read()", strInput.consume().read(), "2030");
    i.expect("strInput.consume().consume().read()", strInput.consume().consume().read(), "S");
    i.expectException("strInput.consume().consume().consume().read()", () -> { strInput.consume().consume().consume().read(); return; }, new java.util.NoSuchElementException());
    i.expectException("strInput.consume().consume().consume().consume()", () -> { strInput.consume().consume().consume().consume(); return; }, new java.util.NoSuchElementException());
  }
}
