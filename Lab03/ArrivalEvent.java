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
   * Shop that is involved.
   */
  private Shop shop;

  /**
   * ArrivalEvent Constructor.
   * 
   * @param customer
   *          Customer that arrived.
   * @param counters
   *          Array of counters that the shop has.
   * @param customerQueue
   *          Queue obj that contains number of customers currently in queue.
   * @param arrivalTime
   *          Time at which customer arrives
   */
  public ArrivalEvent(Customer customer, Shop shop, double arrivalTime) {
    super(arrivalTime);
    this.customer = customer;
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
    Counter counter = shop.availableCounter();
    // check if counter is availble since above method returns a counter
    // that is either availble or has the shortest queue
    if (counter.isAvailable()) {
      return new Event[] {
          // begin service
          new ServiceBeginEvent(this.customer, counter, this.shop, this.getTime())
      };
    } else {
      // if counter isn't available
      // check if can queue
      if (counter.canQueue()) {
        // TODO: return new queue event?
        return new Event[] {
            new CounterQueueEvent(this.customer, this.shop, this.getTime(), counter)
        };
      }
      // else check if shop queue is available
      else if (this.shop.canQueue()) {
        // TODO: return new queue event
        return new Event[] {
            new ShopQueueEvent(this.customer, this.shop, this.getTime())
        };
      } else {
        // if queue is full
        return new Event[] {
            new DepartureEvent(this.customer, this.getTime())
        };
      }
    }
  }

  /**
   * Returns the string representation of this event.
   * 
   * @return String representation of an arrival event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s arrived %s", this.customer, this.shop);
    return super.toString() + str;
  }
}
