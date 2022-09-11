/**
 * This is the Shop class.
 * It will contain the counters, queues and service events.
 * 
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
public class Shop {
  /**
   * The number of counters in the shop.
   */
  private Array<Counter> counters;

  /**
   * The queue that the shop has.
   */
  private Queue<Customer> queue;

  /**
   * Constructor for Shop class.
   * 
   * @param numOfCounters
   *          Num of counters shop has.
   * @param counterQueueLength
   *          Max length of each counter queue.
   * @param shopQueueLength
   *          Max length of shop queue.
   */
  public Shop(int numOfCounters, int counterQueueLength, int shopQueueLength) {
    counters = new Array<Counter>(numOfCounters);
    queue = new Queue<Customer>(shopQueueLength);
  }

  /**
   * Find the first available counter.
   * 
   * @return either an available counter or counter with the shortest queue.
   */
  public Counter availableCounter() {
    return this.counters.min();
  }

  /**
   * Checks if shop's queue is not full.
   * 
   * @return true if queue is not full.
   *         false if queue is full.
   */
  public boolean canQueue() {
    return !this.queue.isFull();
  }

  /**
   * Checks if shop has a queue.
   * 
   * @return true if queue is not empty.
   *         false if queue is empty.
   */
  public boolean hasQueue() {
    return !this.queue.isEmpty();
  }

  /**
   * Adds customer to shop queue.
   * 
   * @param customer
   *          customer that is joining shop queue.
   */
  public void joinQueue(Customer customer) {
    this.queue.enq((customer));
  }

  /**
   * Returns the string representation of shop aka its queue.
   * 
   * @return String representation of a shop event.
   */
  @Override
  public String toString() {
    String str = String.format("%s", this.queue);
    return super.toString() + str;
  }
}
