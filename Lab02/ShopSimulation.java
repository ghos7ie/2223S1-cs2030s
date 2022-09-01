import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class ShopSimulation extends Simulation {
  /**
   * The array of counters in Shop
   */
  private Counter[] counters;

  /**
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /**
   * Constructor for a shop simulation.
   *
   * @param sc A scanner to read the parameters from. The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters. Next is a
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {

    // num of customers/num of events to create
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int queueLimit = sc.nextInt();

    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter();
    }

    // Customer Id -- should move to Customer object next time
    int id = 0;
    while (sc.hasNextDouble()) {

      Customer customer = new Customer(sc.nextDouble(), sc.nextDouble());
      // check for available
      initEvents[id] = new ArrivalEvent(customer, counters);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
