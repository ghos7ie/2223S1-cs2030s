import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class ShopSimulation extends Simulation {
  /**
   * The availability of counters in the shop.
   */
  private Counter[] counters;

  /**
   * Coutner (available:boolean)
   */
  /**
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;


  private static Queue customerQueue;
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
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    customerQueue = new Queue(sc.nextInt());

    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter();
    }

    // Customer Id -- should move to Customer object next time
    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(arrivalTime, serviceTime);
      // check for available
      initEvents[id] = new ArrivalEvent(customer, counters, customerQueue);
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
