/**
 * This is the Counter class. This class is responsible for tracking
 * the availability of the counters.
 * 
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class Counter implements Comparable<Counter> {
  /**
   * The universal counter id - keeps track of number of counters created.
   */
  private static int count = 0;

  /**
   * The id of the counter.
   */
  private int id;

  /**
   * The availabilty of the counter - Set default value to true.
   */
  private boolean available = true;

  private Queue<Customer> queue;

  /**
   * Counter Constructor.
   * 
   * @param queueLength
   *          max queue Length of counter.
   */
  public Counter(int queueLength) {
    this.id = count++;
    this.queue = new Queue<Customer>(queueLength);
  }

  /**
   * Compare this counter with a given counter c.
   * First check which is available, if both available then take the
   * counter with a shorter queue.
   * 
   * @param c
   *          The other counter to compare to.
   * @return
   */
  @Override
  public int compareTo(Counter c) {
    if (c.available) {
      return 1;
    } else if (this.available) {
      return -1;
    }
    // if both unavailable, return the one with shorter queue (not max queue length)
    else {
      return Integer.compare(this.getQueueLength(), c.getQueueLength());
    }
  }

  /**
   * Tell if current counter is available.
   * 
   * @return Returns availability of a counter.
   */
  public boolean isAvailable() {
    return this.available;
  }

  /**
   * Make counter available.
   */
  public void makeAvailable() {
    this.available = true;
  }

  /**
   * Make counter unavailable.
   */
  public void makeUnavailable() {
    this.available = false;
  }

  /**
   * Retrieves next customer to serve from counter queue.
   *
   * @return next customer to serve.
   *         null if there are no more.
   */
  public Customer nextCustomer() {
    this.makeAvailable();
    // if queue isn't empty
    if (!this.queue.isEmpty()) {
      Customer nextCustomer = (Customer) this.queue.deq();
      this.makeUnavailable();
      return nextCustomer;
    }
    return null;
  }

  /**
   * Returns the queue length for the counter.
   * 
   * @return returns max length of queue.
   */
  public int getQueueLength() {
    return this.queue.length();
  }

  /**
   * Checks if counter can be queued for.
   * 
   * @return true if queue is not full.
   *         false if full.
   */
  public boolean canQueue() {
    return !this.queue.isFull();
  }

  /**
   * Adds customer to counter queue.
   * 
   * @param customer
   *          customer that is joining shop queue.
   */
  public void joinQueue(Customer customer) {
    this.queue.enq((customer));
  }

  // /**
  // * Returns the first available counter.
  // *
  // * @param counters
  // * Array of counters shop has.
  // * @return Returns the counter that is available.
  // * Else it returns null as no counter is available.
  // */
  // public static Counter getAvailableCounter(Counter[] counters) {
  // for (Counter counter : counters) {
  // if (counter.isAvailable()) {
  // counter.makeUnavailable();
  // return counter;
  // }
  // }
  // return null;
  // }

  /**
   * Returns the string representation of this counter.
   * 
   * @return String representation of a counter.
   */
  @Override
  public String toString() {
    String str = String.format("S%s %s", this.id, this.queue);
    return super.toString() + str;
  }
}