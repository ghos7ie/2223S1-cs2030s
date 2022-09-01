/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class DepartureEvent extends Event {
  private Customer customer;

  /**
   * DepartureEvent Constructor
   *
   * @param customerId Customer Id
   */
  public DepartureEvent(Customer customer, double departTime) {
    super(departTime);
    this.customer = customer;
  }

  /**
   * Simulate Departure event.
   *
   * @param customerId
   * @return An array of new events to be scheduled by the simulator.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d departed", this.customer);
    return super.toString() + str;
  }
}
