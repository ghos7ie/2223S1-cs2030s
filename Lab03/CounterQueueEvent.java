/**
 * This class extends Event class.
 * This class represents the event where the customer joins the
 * counter queue.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class CounterQueueEvent extends Event {

  /**
   * Customer that is joining the queue.
   */
  private Customer customer;

  /**
   * Counter that customer is joining the queue of.
   */
  private Counter counter;

  /**
   * CounterQueueEvent Constructor.
   * 
   * @param customer
   *          Customer that is joining the queue.
   * @param shop
   *          Shop that customer is in.
   * @param queueTime
   *          Time that customer is joining the queue.
   * @param counter
   *          Counter that customer is queuing for
   */
  public CounterQueueEvent(Customer customer, double queueTime, Counter counter) {
    super(queueTime);
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Simulate Counter Queue event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns Event[].
   */
  @Override
  public Event[] simulate() {
    this.counter.joinQueue(this.customer);
    return new Event[] {};
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of a counter queue event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s joined counter queue %s", this.customer, this.counter);
    return super.toString() + str;
  }
}
