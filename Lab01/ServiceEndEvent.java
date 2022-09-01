/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends Event {

  private Customer customer;
  private Counter counter;

  /**
   * ServiceEndEvent Constructor
   * 
   * @param endTime    Time that service ends
   * @param customerId Customer Id
   * @param counter
   */
  public ServiceEndEvent(Customer customer, Counter counter, double endTime) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public Event[] simulate() {
    // Pass entire counter object to encapsulate what is done to the counter
    this.counter.setAvailable(true);
    return new Event[] {
        new DepartureEvent(customer, this.getTime())
    };
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %s service done (by Counter %s)", this.customer, this.counter);
    return super.toString() + str;
  }
}
