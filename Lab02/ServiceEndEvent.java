/**
 * This class implements Event.
 * This class represents the end of service for a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Queue customerQueue;

  /**
   * ServiceEndEvent Constructor
   * 
   * @param endTime
   *          Time that service ends
   * @param customerId
   *          Customer Id
   * @param counter
   */
  public ServiceEndEvent(Customer customer, Counter counter, Queue customerQueue, double endTime) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.customerQueue = customerQueue;
  }

  @Override
  public Event[] simulate() {
    // Pass entire counter object to encapsulate what is done to the counter
    this.counter.setAvailable(true);
    Customer customer = (Customer) this.customerQueue.deq();
    if (customer == null) {
      return new Event[] {
          new DepartureEvent(this.customer, this.getTime())
      };
    } else {
      // counter is now in use by the next customer
      this.counter.setAvailable(false);
      return new Event[] {
          new DepartureEvent(this.customer, this.getTime()),
          new ServiceBeginEvent(customer, this.counter, this.customerQueue, this.getTime())
      };
    }

  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": %s service done (by %s)", this.customer, this.counter);
    return super.toString() + str;
  }
}
