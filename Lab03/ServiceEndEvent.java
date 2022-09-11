/**
 * This class implements Event.
 * This class represents the end of service for a customer.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends Event {

  /**
   * Customer that arrived.
   */
  private Customer customer;
  /**
   * Counter that is serving the customer.
   */
  private Counter counter;

  /**
   * Shop that customer is in.
   */
  private Shop shop;

  /**
   * ServiceEndEvent Constructor.
   * 
   * @param customer
   *          Customer that has been served.
   * @param counter
   *          Counter that is serving the customer.
   * @param shop
   *          Shop that customer is in
   * @param endTime
   *          Time that the service ended.
   */
  public ServiceEndEvent(Customer customer, Counter counter, Shop shop, double endTime) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  /**
   * Simulate Service End event.
   * Has multiple conditions to look for.
   * (1) Counter queue isn't full.
   * (2) Counter has next customer to serve from its own queue.
   * (3) Counter has next customer to serve from shop queue.
   * (4) Shop has a queue.
   * (5)
   * 
   * @return An array of new events to be scheduled by the simulator.
   *         I can write an essay on this hello.
   *         Returns Event[] containing DepartureEvent, ServiceBeginEvent,
   *         CounterQueueEvent if -- (1), (2), (4) present
   *         Returns Event[] containing DepartureEvent, ServiceBeginEvent
   *         if -- (1), (3) present
   *         Returns Event[] containing DepartureEvent, ServiceBeginEvent if --
   *         (2)
   * 
   */
  @Override
  public Event[] simulate() {
    // get next customer from counter queue
    Customer nextCustomerC = this.counter.nextCustomer();
    // get next customer from shop queue
    Customer nextCustomerS = this.shop.nextCustomer();
    // IF CAN ADD CUSTOMER TO COUNTER QUEUE
    if (this.counter.canQueue()) {
      // AND THERE IS A CUSTOMER IN SHOP QUEUE
      if (nextCustomerS != null) {
        // AND COUNTER CAN SERVE A CUSTOMER FROM ITS OWN QUEUE
        if (nextCustomerC != null) {
          // RETURN DEPART THE CURRENT CUSTOMER
          // + SERVICE FOR NEXT COUNTER CUSTOMER
          // + ADD SHOP CUSTOMER TO COUNTER QUEUE
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(nextCustomerC, this.counter, this.shop, this.getTime()),
              new CounterQueueEvent(nextCustomerS, this.getTime(), this.counter)
          };
        } else {
          // COUNTER DOESN'T HAVE CUSTOMER
          // RETURN DEPART THE CURRENT CUSTOMER
          // + SERVICE FOR THE NEXT SHOP CUSTOMER
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(nextCustomerS, this.counter, this.shop, this.getTime()),
          };
        }
      } else {
        // NO CUSTOMER IN SHOP QUEUE
        // BUT HAVE CUSTOMER IN COUNTER QUEUE
        if (nextCustomerC != null) {
          // RETURN DEPART CURRENT CUSTOMER
          // + SERVICE FOR COUNTER CUSTOMER
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(nextCustomerC, this.counter, this.shop, this.getTime()),
          };
        } else {
          // NO CUSTOMER TO SERVE ALR
          // RETURN DEPART CURRENT CUSTOMER
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime())
          };
        }
      }
    } else {
      // what to do if counter queue cannot be entered
      // IF COUNTER GOT CUSTOMER
      if (nextCustomerC != null) {
        // RETURN DEPART CURRENT CUSTOMER
        // + SERVICE FOR COUNTER CUSTOMER
        return new Event[] {
            new DepartureEvent(this.customer, this.getTime()),
            new ServiceBeginEvent(nextCustomerC, this.counter, this.shop, this.getTime()),
        };
      } else {
        // IF COUNTER NO CUSTOMER, BUT SHOP HAVE
        if (nextCustomerS != null) {
          // RETURN DEPART CURRENT CUSTOMER
          // + SERVICE FOR COUNTER CUSTOMER
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
              new ServiceBeginEvent(nextCustomerS, this.counter, this.shop, this.getTime()),
          };
        } else {
          // RETURN DEPART CURRENT CUSTOMER
          return new Event[] {
              new DepartureEvent(this.customer, this.getTime()),
          };
        }
      }
    }

  }

  /**
   * Prints status of Event.
   * 
   * @return Returns string representation of a ServiceEndEvent.
   */
  @Override
  public String toString() {
    String str = String.format(": %s service done (by %s)", this.customer, this.counter);
    return super.toString() + str;
  }
}
