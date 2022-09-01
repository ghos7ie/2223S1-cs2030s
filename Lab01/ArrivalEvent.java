/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ArrivalEvent extends Event {

  private Customer customer;
  private Counter[] counters;

  /**
   * ArrivalEvent Constructor
   * 
   * @param customer Customer obj
   * @param counters
   */
  public ArrivalEvent(Customer customer, Counter[] counters) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.counters = counters;
  }

  /**
   * Simulate Arrival event.
   */
  @Override
  public Event[] simulate() {
    // Pass entire counter array to encapsulate what is done to the counter
    Counter counter = Counter.getAvailableCounter(this.counters);
    if (counter == null) {
      // return DepatureEvent if no counters are available
      return new Event[] {
          new DepartureEvent(this.customer, this.getTime())
      };
    } else {
      return new Event[] {
          new ServiceBeginEvent(this.customer, counter)
      };
    }
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %s arrives", this.customer);
    return super.toString() + str;
  }
}
