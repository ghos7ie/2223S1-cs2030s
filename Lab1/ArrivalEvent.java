/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ArrivalEvent extends Event {
  
  private double arrivalTime;
  private double serviceTime;
  private int customerId;
  private Counter[] counters;


    /**
     * ArrivalEvent Constructor
     * @param arrivalTime Time that customer arrives (used in Event class:time)
     * @param serviceTime Time that customer is served
     * @param customerId Customer Id
     * @param counters
     */
    public ArrivalEvent(double arrivalTime, double serviceTime, int customerId, Counter[] counters){
      super(arrivalTime);
      this.serviceTime = serviceTime;
      this.customerId = customerId;
      this.counters = counters;
    }


    /**
     * Simulate Arrival event.
     */
    @Override
    public Event[] simulate() {
      // Pass entire counter array to encapsulate what is done to the counter
      Counter counter = Counter.getAvailableCounter(this.counters);
      if (counter == null) {
        // return DepatureEvent if no counters are available
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customerId)
        };
      } else {
        return new Event[] {
          new ServiceBeginEvent(this.getTime(), this.serviceTime, this.customerId, counter)};
      }
    }
  
    /**
     * Prints status of Event
     */
    @Override
    public String toString() {
      String str = String.format(": Customer %d arrives", this.customerId);
      return super.toString() + str;
    }
  }
  