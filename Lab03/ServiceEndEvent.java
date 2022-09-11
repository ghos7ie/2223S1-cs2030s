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
    // get next customer from counter queue
    Customer customer = this.counter.nextCustomer();
    System.out.println(customer);
    // IF NULL --> means that counter has no more customers in queue
    if (customer == null) {
      // if shop has a queue
      if (this.shop.hasQueue()) {
        if (counter.canQueue()) {
          // put next customer in line into counter queue
          Customer customerInShopQueue = this.shop.leaveQueue();
          return new Event[] {
              new CounterQueueEvent(customerInShopQueue, this.getTime(), this.counter),
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(customer, this.counter, this.shop, this.getTime())
          };
        } else {
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(customer, this.counter, this.shop, this.getTime())
          };
        }
      } else {
        return new Event[] {
            new DepartureEvent(this.customer, this.getTime())
        };
      }
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
