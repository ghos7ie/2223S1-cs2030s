import java.util.List;
import cs2030s.fp.Immutator;
import cs2030s.fp.Reader;

/**
 * Test 4 for CS2030S Mock PE 2 (AY22/23 Sem 2).  Tests
 * for Reader flatMap() methods
 *
 * @author Adi Yoga S. Prabawa
 */
class Test4 {
  /**
   * Main method for Test4.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    
    Reader<Integer> oriInput = Reader.of(1, 2);
    Reader<Integer> intInput = oriInput.flatMap(x -> Reader.of(x * 10, x * 20, x * 30));
    i.expect("intInput.read()", intInput.read(), 10);
    i.expect("intInput.consume().read()", intInput.consume().read(), 20);
    i.expect("intInput.consume().consume().read()", intInput.consume().consume().read(), 30);
    i.expect("intInput.consume().consume().consume().read()", intInput.consume().consume().consume().read(), 2);
    i.expectException("intInput.consume().consume().consume().consume().read()", () -> { intInput.consume().consume().consume().consume().read(); return; }, new java.util.NoSuchElementException());
    i.expectException("intInput.consume().consume().consume().consume().consume()", () -> { intInput.consume().consume().consume().consume().consume(); return; }, new java.util.NoSuchElementException());
  }
}
