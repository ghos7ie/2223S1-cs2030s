/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Predicate;

class Query {

  public static <T,S> Stream<Map.Entry<T, S>> getFilteredByKey(Map<T, S> table, Predicate<T> p) {
    return table.entrySet()
      .stream()
      .filter(e -> p.test(e.getKey()));
  }

  public static Stream<Integer> getIdsFromName(Map<String, List<Integer>> table, String name){
    return getFilteredByKey(table, x -> x.equals(name))
      .flatMap(e -> e.getValue().stream());      
  }

  public static Stream<Double> getCostsFromIDs(Map<Integer, Double> table, Stream<Integer> ids){
    return ids
      .map(x -> table.get(x))
      .filter(x -> x != null);
  }

  public static Stream<String> allCustomerCosts(Map<String, List<Integer>> customerTable, Map<Integer, Double> salesTable){
    return customerTable.entrySet()
      .stream()
      .flatMap(customer -> getCostsFromIDs(salesTable, customer.getValue().stream())
          .map(costs -> customer.getKey().toString() + ": " + costs.toString()));
  }

  public static Stream<String> totaledCustomerCosts(Map<String, List<Integer>> customerTable, Map<Integer, Double> salesTable){
    return customerTable.entrySet()
      .stream()
      .map(customer -> customer.getKey().toString() + ": " + 
          getCostsFromIDs(salesTable, customer.getValue().stream())
            .reduce(0.0, (x,y) -> x+y));
  }
}

