import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.LinkedList;

public class Streaming {
  public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
    return stream
        .collect(
            // Supplier - The ArrayList to add to
            LinkedList<Pair<Integer, T>>::new,
            (list, value) -> {
              if (list.isEmpty() || !list.getLast().getSnd().equals(value)) {
                list.add(new Pair<Integer, T>(1, value));
              }
              list.getLast().setFst(list.getLast().getFst() + 1);
            },
            (list1, list2) -> {
              if (list1.getLast().getSnd().equals(list2.getFirst().getSnd())) {
                list1.getLast().setFst(list1.getLast().getFst() + list2.getLast().getFst());
                list2.removeFirst();
              }

            })
        .stream().collect(Collectors.toList());
  }
}