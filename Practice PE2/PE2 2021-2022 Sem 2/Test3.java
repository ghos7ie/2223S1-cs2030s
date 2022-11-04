import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Test3 {
  public static void main(String[] args) throws Throwable {
    CS2030STest i = new CS2030STest();

    Map<String, List<Integer>> customerTable = Map.of("Michelle", List.of(12, 56), 
                                                      "Enzio",  List.of(34, 90), 
                                                      "Michael", List.of(78));


    Map<Integer, Double> salesTable = Map.of(12, 12.0,
                                             34, 6.0,
                                             56, 7.5,
                                             78, 9.0,
                                             90, 17.0);


    Map.Entry<String, List<Integer>> enzioEntry = new 
                                  SimpleEntry<String, List<Integer>>("Enzio",  List.of(34, 90));
    Map.Entry<String, List<Integer>> michaelEntry = new 
                                  SimpleEntry<String, List<Integer>>("Michael",  List.of(78));
    Map.Entry<String, List<Integer>> michelleEntry = new 
                                  SimpleEntry<String, List<Integer>>("Michelle",  List.of(12, 56));

    i.expectReturn("Query.getFilteredByKey(customerTable, x -> x.equals(\"Enzio\"))" + 
                   ".collect(Collectors.toList());\n// Should return a Stream containing" 
                   + " the Enzio Entry",
        () -> Query.getFilteredByKey(customerTable, x -> x.equals("Enzio"))
                                           .collect(Collectors.toList()), List.of(enzioEntry));
   
    i.expectReturn("Query.getFilteredByKey(customerTable, x -> x.startsWith(\"Mic\"))" + 
                   ".collect(Collectors.toList());\n// Should return a Stream containing" 
                   + " both the Michelle and Michael entries",
        () -> Query.getFilteredByKey(customerTable, x -> x.startsWith("Mic"))
                          .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                          .collect(Collectors.toList()), List.of(michaelEntry, michelleEntry));

    i.expectReturn("Query.getFilteredByKey(customerTable, x -> x.startsWith(\"A\"))" 
                 + ".collect(Collectors.toList());\n// should return an empty Stream",
        () -> Query.getFilteredByKey(customerTable, x -> x.startsWith("A"))
                   .collect(Collectors.toList()), Collections.emptyList());

    i.expectReturn("Query.getIdsFromName(customerTable, \"Michelle\").sorted()" + 
                   ".collect(Collectors.toList());\n// should return a list containing 12 and 56",
        () -> Query.getIdsFromName(customerTable, "Michelle").sorted()
                   .collect(Collectors.toList()), List.of(12, 56));

    i.expectReturn("Query.getIdsFromName(customerTable, \"Sam\").sorted()" +
                   ".collect(Collectors.toList());\n// should return an empty list",
        () -> Query.getIdsFromName(customerTable, "Sam").sorted()
                   .collect(Collectors.toList()), Collections.emptyList());

    i.expectReturn("Query.getCostsFromIDs(salesTable, Stream.of(12)).sorted()"
                   + ".collect(Collectors.toList());\n// should return a list containing 12.0",
        () -> Query.getCostsFromIDs(salesTable, Stream.of(12)).sorted()
                    .collect(Collectors.toList()), List.of(12.0));

    i.expectReturn("Query.getCostsFromIDs(salesTable, Stream.of(12, 90)).sorted()"
                   + ".collect(Collectors.toList());\n// should return a list "
                   + "containing 12.0 and 17.0",
        () -> Query.getCostsFromIDs(salesTable, Stream.of(12, 90)).sorted()
                    .collect(Collectors.toList()), List.of(12.0, 17.0));

    i.expectReturn("Query.getCostsFromIDs(salesTable, Stream.of(7)).sorted()" 
                   + ".collect(Collectors.toList());\n// should return an empty list",
        () -> Query.getCostsFromIDs(salesTable, Stream.of(7)).sorted()
                    .collect(Collectors.toList()), Collections.emptyList());
  }
}
