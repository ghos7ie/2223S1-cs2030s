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
   * @return 1 if c is available.
   *         -1 if this.counter is available.
   *         -1 if c has shorter queue.
   *         1 if this.counter has shorter queue.
   *         0 if both same.
   */
  @Override
  public int compareTo(Counter c) {
    if (this.available && c.available) {
      // if both available, compare their queue
      if (c.queue.length() < this.queue.length()) {
        return -1;
      } else if (this.queue.length() > c.queue.length()) {
        return 1;
      } else {
        return 0;
      }
    } else if (this.available) {
      return 1;
    } else if (c.available) {
      return -1;
    } else {
      return 0;
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
    System.out.println("Is there a queue? " + this.queue.isEmpty());
    if (!this.queue.isEmpty()) {
      Customer nextCustomer = this.queue.deq();
      this.makeUnavailable();
      return nextCustomer;
    }
    return null;
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
    return str;
  }
}