import java.util.List; 
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * CS2030S PE2 Question 2
 * AY21/22 Semester 2
 * Query API class.
 *
 * @author [Ong Seeu Sim](A0239298L)
 */
class Query {
  
  /**
   * Produces a Stream of `java.util.Map.Entry` instances whose keys pass the 
   * given predicate, from the given Table.
   *
   * @param <T> The type of the table's keys.
   * @param <S> The type of the table's values.
   * @param table The hash table to filter.
   * @param p The predicate to filter the table.
   * @return The stream of filtered table entries.
   */
  public static <T, S> Stream<Map.Entry<T, S>> getFilteredByKey(Map<T, S> table, Predicate<T> p) {
    return table.entrySet().stream()
      .parallel()
      .filter(x -> p.test(x.getKey()));
  }
  
  /**
   * Given a table of Name to ID mappings, return a stream of the IDs for the 
   * given name.
   *
   * @param table The table mapping names to ID indices.
   * @param key The name to query the table with.
   * @return The IDs for the given name in the given table, if they exist.
   */
  public static Stream<Integer> getIdsFromName(Map<String, List<Integer>> table, String key) {
    return table.entrySet().stream()
      .filter(k -> {
        String tblK = k.getKey();
        return tblK == key || tblK.equals(key);
      })
      .flatMap(ent -> ent.getValue().stream());
  }
  
  /**
   * Given a table of ID to Cost mappings, return a stream of the costs for the given IDs.
   * 
   * @param table The table mapping IDs to Cost.
   * @param ids The Stream of ID indices.
   * @return The stream of respective costs.
   */
  public static Stream<Double> getCostsFromIDs(
      Map<Integer, Double> table, Stream<Integer> ids) {
    return ids.map(x -> table.get(x)).filter(x -> x != null);
  }
  
  /**
   * Given a table mapping Customer Name to ID indices, and another table 
   * mapping ID indices to Costs, return a Stream of all the costs for 
   * each customer.
   *
   * @param custTable The customer to ID table.
   * @param salesTable The ID to cost table.
   * @return The Stream of costs for each customer.
   */
  public static Stream<String> allCustomerCosts(
      Map<String, List<Integer>> custTable, Map<Integer, Double> salesTable) {
    return custTable.entrySet().stream()
      .parallel()
      .flatMap(ent -> {
        String name = ent.getKey() + ": ";
        return ent.getValue().stream()
          .parallel()
          .filter(x -> salesTable.get(x) != null) 
          .map(x -> name + salesTable.get(x));
      });
  }
  
  /**
   * Given a table mapping Customer Name to ID indices, and another table 
   * mapping ID indices to Costs, return a Stream of the total cost for 
   * each customer.
   *
   * @param cusTable The Customer to ID table.
   * @param salesTable The ID to cost table.
   * @return The Stream containing the total costs for each customer.
   */
  public static Stream<String> totaledCustomerCosts(
      Map<String, List<Integer>> cusTable, Map<Integer, Double> salesTable) {
    return cusTable.entrySet().stream()
      .parallel()
      .map(ent -> {
        String name = ent.getKey();
        return name + ": " + ent.getValue().stream()
          .parallel()
          .filter(x -> salesTable.get(x) != null)
          .map(x -> salesTable.get(x))
          .reduce(0.0, (x, y) -> x + y, (x, y) -> x + y);
      });
  }
}

