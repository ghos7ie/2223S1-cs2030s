/**
 * This class implements Event.
 * This class represents the arrival of a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ArrivalEvent extends Event {

  private Customer customer;
  private Counter[] counters;
  private Queue customerQueue;

  /**
   * ArrivalEvent Constructor
   * 
   * @param customer Customer obj
   * @param counters
   */
  public ArrivalEvent(Customer customer, Counter[] counters, Queue customerQueue) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.counters = counters;
    this.customerQueue = customerQueue;
  }

  /**
   * Simulate Arrival event.
   */
  @Override
  public Event[] simulate() {
    // Pass entire counter array to encapsulate what is done to the counter
    Counter counter = Counter.getAvailableCounter(this.counters);
    if (counter == null) {
      if (!this.customerQueue.isFull()) {
        return new Event[] {
            new QueueEvent(this.customer, this.customerQueue, this.getTime())
        };
      } else {
        // return DepatureEvent if no queue is full
        return new Event[] {
            new DepartureEvent(this.customer, this.getTime())
        };
      }
    } else {
      return new Event[] {
          new ServiceBeginEvent(this.customer, counter, this.customerQueue, this.getTime())
      };
    }
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": %s arrived %s", this.customer, this.customerQueue);
    return super.toString() + str;
  }
}
