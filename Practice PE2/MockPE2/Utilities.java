import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

class Utilities {
  public static Stream<Character> split(String s) {
    List<Character> chars = new ArrayList<>();
    for (int i=0; i<s.length(); i++) {
      chars.add(s.charAt(i));
    }
    return chars.stream();
  }
}