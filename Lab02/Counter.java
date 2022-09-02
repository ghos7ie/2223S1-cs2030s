/**
 * This is the Counter class. This class is responsible for tracking
 * the availability of the counters.
 * 
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class Counter {
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

  /**
   * Counter Constructor.
   */
  public Counter() {
    this.id = count++;
  }

  /**
   * Tell if current counter is available.
   * 
   * @return Returns availability of a counter.
   */
  private boolean isAvailable() {
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
   * Returns the first available counter.
   * 
   * @param counters
   *          Array of counters shop has.
   * @return Returns the counter that is available.
   *         Else it returns null as no counter is available.
   */
  public static Counter getAvailableCounter(Counter[] counters) {
    for (Counter counter : counters) {
      if (counter.isAvailable()) {
        counter.makeUnavailable();
        return counter;
      }
    }
    return null;
  }

  /**
   * Returns the string representation of this counter.
   * 
   * @return String representation of a counter.
   */
  @Override
  public String toString() {
    return "S" + this.id;
  }
}