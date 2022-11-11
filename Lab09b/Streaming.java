import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.LinkedList;

public class Streaming {
  public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
    return stream
        .map(value -> new Pair<Integer, T> (1, value))
        .collect(LinkedList<Pair<Integer, T>>::new, 
        (list, pair) -> {
          // if list is empty OR the last pair does not have the same T value
          // add new Pair into the list
          if (list.isEmpty() || !list.getLast().getSnd().equals(pair.getSnd())){
            list.add(pair);
          }else{
            // else since same value consecutively, we add 1 to count
            list.getLast().setFst(list.getLast().getFst() + 1);
          }
        }, 
        (list1, list2) -> {
          if (list1.getLast().getSnd().equals(list2.getFirst().getSnd())){
            list1.getLast().setFst(list1.getLast().getFst() + list2.getFirst().getFst());
            list2.removeFirst();
          }
          list1.addAll(list2);
        });
  }
}