/**
 * This class implements Event.
 * This class represents the end of service for a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends Event {

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
   * ServiceEndEvent Constructor.
   * 
   * @param customer
   *          Customer that has been served.
   * @param counter
   *          Counter that is serving the customer.
   * @param shop
   *          Shop that customer is in
   * @param endTime
   *          Time that the service ended.
   */
  public ServiceEndEvent(Customer customer, Counter counter, Shop shop, double endTime) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  /**
   * Simulate Service Begin event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns Event[] containing DepartureEvent if Queue is empty.
   *         Returns Event[] containing DepartureEvent and ServiceBeginEvent if
   *         Queue is not empty.
   */
  @Override
  public Event[] simulate() {
    // first check if any counter queue can be filled
    if (this.shop.hasQueue()) {
      Counter counter = shop.availableCounter();
      System.out.println(counter);
      if (counter.canQueue()) {
        // queueEvent for counter from shopQueue?
      }
    }
    Customer customer = this.counter.nextCustomer();
    System.out.println(customer);
    if (customer == null) {
      return new Event[] {
          new DepartureEvent(this.customer, this.getTime())
      };
    } else {
      return new Event[] {
          new DepartureEvent(this.customer, this.getTime()),
          new ServiceBeginEvent(customer, this.counter, this.shop, this.getTime())
      };
    }

  }

  /**
   * Prints status of Event.
   * 
   * @return Returns string representation of a ServiceEndEvent.
   */
  @Override
  public String toString() {
    String str = String.format(": %s service done (by %s)", this.customer, this.counter);
    return super.toString() + str;
  }
}
