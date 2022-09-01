/**
 * This class implements Event
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends Event {

  private double endTime;
  private int customerId;
  private Counter counter;
  private int counterId;

   /**
     * ServiceEndEvent Constructor
     * @param endTime Time that service ends
     * @param customerId Customer Id
     * @param counter
     */
    public ServiceEndEvent(double endTime, int customerId, Counter counter){
      super(endTime);
      this.customerId = customerId;
      this.counter = counter;
      this.counterId = counter.getId();
    }

  @Override
  public Event[] simulate() {
    // Pass entire counter object to encapsulate what is done to the counter
    this.counter.setAvailable(true);
    return new Event[] {
      new DepartureEvent(this.getTime(), this.customerId)
    };
  }

  /**
   * Prints status of Event
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d service done (by Counter %d)", this.customerId, this.counterId);
    return super.toString() + str;
  }
}
