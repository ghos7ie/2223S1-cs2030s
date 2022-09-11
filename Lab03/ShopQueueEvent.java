/**
 * This class extends QueueEvent class.
 * This class represents the event where the customer joins the
 * shop queue.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class ShopQueueEvent extends QueueEvent {

  /**
   * Customer that is joining the queue.
   */
  private Customer customer;

  /**
   * Customer that is joining the queue.
   */
  private Shop shop;

  /**
   * ShopQueueEvent Constructor.
   * 
   * @param customer
   *          Customer that is joining the queue.
   * @param shop
   *          Shop that customer is in.
   * @param queueTime
   *          Time that customer is joining the queue.
   */
  public ShopQueueEvent(Customer customer, Shop shop, double queueTime) {
    super(customer, shop, queueTime);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Simulate Shop Queue event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns Event[].
   */
  @Override
  public Event[] simulate() {
    this.shop.joinQueue(this.customer);
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = String.format("%s joined shop queue %s", this.customer, this.shop);
    return super.toString() + str;
  }
}
