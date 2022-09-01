/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceBeginEvent extends Event {

  private Customer customer;
  private Counter counter;

  /**
   * ServiceBeginEvent Constructor
   * 
   * @param arrivalTime Time that customer arrives
   * @param serviceTime Time that customer is served
   * @param customerId  Customer Id
   * @param counter
   */
  public ServiceBeginEvent(Customer customer, Counter counter) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.counter = counter;
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
        new ServiceEndEvent(this.customer, this.counter, endTime)
    };
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %s service begin (by Counter %s)",
        this.customer, this.counter);
    return super.toString() + str;
  }
}
