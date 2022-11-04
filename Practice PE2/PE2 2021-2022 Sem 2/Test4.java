import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Test4 {
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

    Map<String, List<Integer>> badCustomerTable = Map.of("Bill", List.of(17),
                                                         "Sam", List.of(19));

    Map<Integer, Double> badSalesTable = Map.of(99, 3.0,
                                                98, 2.0);

    i.expectReturn("Query.allCustomerCosts(customerTable, salesTable).sorted()" 
                                             + ".collect(Collectors.toList());",
        () -> Query.allCustomerCosts(customerTable, salesTable).sorted()
                                                               .collect(Collectors.toList()), 
          List.of("Enzio: 17.0", "Enzio: 6.0", "Michael: 9.0", "Michelle: 12.0", "Michelle: 7.5"));
    i.expectReturn("Query.allCustomerCosts(customerTable, badSalesTable).sorted()"
                                                + ".collect(Collectors.toList());",
        () -> Query.allCustomerCosts(customerTable, badSalesTable).sorted()
                                                                  .collect(Collectors.toList()), 
                   Collections.emptyList());
    i.expectReturn("Query.allCustomerCosts(badCustomerTable, salesTable).sorted()"
                                                + ".collect(Collectors.toList());",
        () -> Query.allCustomerCosts(badCustomerTable, salesTable).sorted()
                                                                  .collect(Collectors.toList()), 
                   Collections.emptyList());

    i.expectReturn("Query.totaledCustomerCosts(customerTable, salesTable).sorted()" 
                                                 + ".collect(Collectors.toList());",
        () -> Query.totaledCustomerCosts(customerTable, salesTable).sorted()
                                                                   .collect(Collectors.toList()), 
                  List.of("Enzio: 23.0", "Michael: 9.0", "Michelle: 19.5"));
    i.expectReturn("Query.totaledCustomerCosts(customerTable, badSalesTable).sorted()"
                                                    + ".collect(Collectors.toList());",
        () -> Query.totaledCustomerCosts(customerTable, badSalesTable).sorted()
                                                        .collect(Collectors.toList()), 
                   List.of("Enzio: 0.0", "Michael: 0.0", "Michelle: 0.0"));
    i.expectReturn("Query.allCustomerCosts(badCustomerTable, salesTable).sorted()" 
                                                + ".collect(Collectors.toList());",
        () -> Query.totaledCustomerCosts(badCustomerTable, salesTable).sorted()
                                                        .collect(Collectors.toList()), 
                   List.of("Bill: 0.0", "Sam: 0.0"));

  }
}
