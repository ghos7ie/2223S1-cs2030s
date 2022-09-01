/**
 * This class implements Event.
 * This class represents the start of service for a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceBeginEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Queue customerQueue;

  /**
   * ServiceBeginEvent Constructor
   * 
   * @param customer  Customer obj
   * @param counter Counter obj
   * @param customerQueue Queue obj
   * @param startTime starting time of service (needed since there is a queue now)
   */
  public ServiceBeginEvent(Customer customer, Counter counter, Queue customerQueue, double startTime) {
    super(startTime);
    this.customer = customer;
    this.counter = counter;
    this.customerQueue = customerQueue;
  }

  /**
   * Simulate Service Begin event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   */
  @Override
  public Event[] simulate() {
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
        new ServiceEndEvent(this.customer, this.counter, this.customerQueue, endTime)
    };
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": %s service begin (by  %s)",
        this.customer, this.counter);
    return super.toString() + str;
  }
}
