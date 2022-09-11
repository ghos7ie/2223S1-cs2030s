/**
 * This class implements Event.
 * This class represents the event where the customer joins the
 * queue.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class QueueEvent extends Event {

  /**
   * Customer that is joining the queue.
   */
  private Customer customer;
  /**
   * Customer that is joining the queue.
   */
  private Shop shop;

  /**
   * QueueEvent Constructor.
   * 
   * @param customer
   *          Customer that is joining the queue.
   * @param shop
   *          Shop that customer is in.
   * @param queueTime
   *          Time that customer is joining the queue.
   */
  public QueueEvent(Customer customer, Shop shop, double queueTime) {
    super(queueTime);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Simulate Queue event.
   * Adds customer to customerQueue.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns empty Event[] as nothing happens after joining queue.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of an queue event.
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
