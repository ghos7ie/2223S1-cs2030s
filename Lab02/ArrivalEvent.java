/**
 * This class implements Event.
 * This class represents the arrival of a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ArrivalEvent extends Event {

  /**
   * Customer that arrived.
   */
  private Customer customer;
  /**
   * Array of counters that the shop has.
   */
  private Counter[] counters;
  /**
   * Queue obj that contains number of customers currently in queue.
   */
  private Queue customerQueue;

  /**
   * ArrivalEvent Constructor.
   * 
   * @param customer
   *          Customer that arrived.
   * @param counters
   *          Array of counters that the shop has.
   */
  public ArrivalEvent(Customer customer, Counter[] counters, Queue customerQueue) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.counters = counters;
    this.customerQueue = customerQueue;
  }

  /**
   * Simulate Arrival event.
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         Returns Event[] containing QueueEvent if no counter is available and
   *         queue is not full
   *         Returns Event[] containing DepartureEvent if no counter is available
   *         and queue is full.
   *         Returns Event[] containing ServiceBeginEvent if counter is available.
   */
  @Override
  public Event[] simulate() {
    Counter counter = Counter.getAvailableCounter(this.counters);
    if (counter == null) {
      if (!this.customerQueue.isFull()) {
        // if queue is not full
        return new Event[] {
            new QueueEvent(this.customer, this.customerQueue, this.getTime())
        };
      } else {
        // if queue is full
        return new Event[] {
            new DepartureEvent(this.customer, this.getTime())
        };
      }
    } else {
      // if counter is available
      return new Event[] {
          new ServiceBeginEvent(this.customer, counter, this.customerQueue, this.getTime())
      };
    }
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of an arrival event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s arrived %s", this.customer, this.customerQueue);
    return super.toString() + str;
  }
}
