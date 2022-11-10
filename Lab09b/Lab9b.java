import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Lab9b is the main driver class for testing parallel encoding.
 * Usage: java Lab9b
 */
class Lab9b {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    
    // Read list
    List<Integer> lst = new ArrayList<>();
    for (int i=0; i<n; i++) {
      lst.add(scanner.nextInt());
    }
    
    // Encoding + Print
    System.out.println(lst);
    Stream<Integer> stream = lst.stream().parallel();
    List<Pair<Integer,Integer>> res = Streaming.encode(stream);
    System.out.println(res);
    System.out.println(stream.isParallel());
  }
}
