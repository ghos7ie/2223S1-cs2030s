/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceBeginEvent extends Event {
  
  private double serviceTime;
  private int customerId;
  private Counter counter;
  private int counterId;

   /**
     * ServiceBeginEvent Constructor
     * @param arrivalTime Time that customer arrives
     * @param serviceTime Time that customer is served
     * @param customerId Customer Id
     * @param counter
     */
    public ServiceBeginEvent(double arrivalTime, double serviceTime, int customerId, Counter counter){
      super(arrivalTime);
      this.serviceTime = serviceTime;
      this.customerId = customerId;
      this.counter = counter;
      this.counterId = counter.getId();
    }


  /**
   * Simulate Service Begin event.
   * @return An array of new events to be scheduled by the simulator.
   */
  @Override
  public Event[] simulate() {
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] {
      new ServiceEndEvent(endTime, this.customerId, this.counter)
    };
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d service begin (by Counter %d)",
        this.customerId, this.counterId);
    return super.toString() + str;
  }
}
