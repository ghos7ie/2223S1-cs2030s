import java.util.List;
import java.util.stream.Collectors;

/**
 * Test 5 for CS2030S Mock PE 2 (AY22/23 Sem 2).  Tests
 * for Stream methods
 *
 * @author Adi Yoga S. Prabawa
 */
class Test5 {
  /**
   * Main method for Test5.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    
    i.expect("Main.reverse(\"adam\")", Main.reverse("adam").toString(), "mada");
    i.expect("Main.reverse(\"eve\")", Main.reverse("eve").toString(), "eve");
    i.expect("Main.reverse(\"adi\")", Main.reverse("adi").toString(), "ida");
    i.expect("Main.reverse(\"racecar\")", Main.reverse("racecar").toString(), "racecar");

    List<String> words = List.of("adam", "eve", "adi", "racecar", "madam", "anna");
    i.expect("only 'eve', 'racecar', 'madam', 'anna' are palindrome", Main.palindrome(words.stream()).collect(Collectors.toList()), List.of("eve", "racecar", "madam", "anna"));
  }
}
