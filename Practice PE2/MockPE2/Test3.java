import java.util.List;
import cs2030s.fp.Immutator;
import cs2030s.fp.Reader;

/**
 * Test 3 for CS2030S Mock PE 2 (AY22/23 Sem 2).  Tests
 * for Reader map() methods
 *
 * @author Adi Yoga S. Prabawa
 */
class Test3 {
  /**
   * Main method for Test3.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    
    Reader<String> strInput = Reader.of("CS", "2030", "S");
    Reader<Integer> intInput = strInput.map(s -> s.length());
    i.expect("intInput.read()", intInput.read(), 2);
    i.expect("intInput.consume().read()", intInput.consume().read(), 4);
    i.expect("intInput.consume().consume().read()", intInput.consume().consume().read(), 1);
    i.expectException("intInput.consume().consume().consume().read()", () -> { intInput.consume().consume().consume().read(); return; }, new java.util.NoSuchElementException());
    i.expectException("intInput.consume().consume().consume().consume()", () -> { intInput.consume().consume().consume().consume(); return; }, new java.util.NoSuchElementException());
  }
}
