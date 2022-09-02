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
   * Queue obj that contains number of customers currently in queue.
   */
  private Queue customerQueue;

  /**
   * ServiceBeginEvent Constructor.
   * 
   * @param customer
   *          Customer that is being served.
   * @param counter
   *          Counter that is serving the customer.
   * @param customerQueue
   *          Queue obj that contains number of customers currently in queue.
   * @param startTime
   *          starting time of service (needed since there is a queue now).
   */
  public ServiceBeginEvent(Customer customer, Counter counter,
      Queue customerQueue, double startTime) {
    super(startTime);
    this.customer = customer;
    this.counter = counter;
    this.customerQueue = customerQueue;
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
        new ServiceEndEvent(this.customer, this.counter, this.customerQueue, endTime)
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