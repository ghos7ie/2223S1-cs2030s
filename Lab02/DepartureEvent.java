/**
 * This class implements Event.
 * This class represents the departure of a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class DepartureEvent extends Event {

  /**
   * Customer that is departing.
   */
  private Customer customer;

  /**
   * 
   * @param customer
   *          Customer that is departing.
   * @param departTime
   *          Time that customer is leaving.
   */
  public DepartureEvent(Customer customer, double departTime) {
    super(departTime);
    this.customer = customer;
  }

  /**
   * Simulate Departure event.
   *
   * @return An array of new events to be scheduled by the simulator.
   *         Returns empty Event[] as nothing happens after departure of customer.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of an departure event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }
}
