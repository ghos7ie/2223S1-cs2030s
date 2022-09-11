/**
 * This class implements Event.
 * This class represents the start of service for a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceBeginEvent extends Event {

  /**
   * Customer that arrived.
   */
  private Customer customer;
  /**
   * Counter that is serving the customer.
   */
  private Counter counter;
  /**
   * Shop that customer is in.
   */
  private Shop shop;

  /**
   * ServiceBeginEvent Constructor.
   * 
   * @param customer
   *          Customer that is being served.
   * @param counter
   *          Counter that is serving the customer.
   * @param shop
   *          Shop that customer is in.
   * @param startTime
   *          starting time of service (needed since there is a queue now).
   */
  public ServiceBeginEvent(Customer customer, Counter counter, Shop shop, double startTime) {
    super(startTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  /**
   * Simulate Service Begin event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns Event[] containing ServiceEndEvent.
   */
  @Override
  public Event[] simulate() {
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
        new ServiceEndEvent(this.customer, this.counter, this.shop, endTime)
    };
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of a service begin event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s service begin (by %s)",
        this.customer, this.counter);
    return super.toString() + str;
  }
}