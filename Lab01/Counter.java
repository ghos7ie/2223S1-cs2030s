/**
 * This class
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class Counter {
  /**
   * The universal counter id
   */
  private static int count = 0;

  /**
   * The id of the counter
   */
  private int id;

  /**
   * The availabilty of the counter
   * Set default value to true
   */
  private boolean available = true;

  /**
   * Constructor for a counter
   *
   * @param available Dictates the availability of the counter.
   *                  True (available) or False (not available)
   */
  public Counter() {
      this.id = count++;
  }

  /**
   * Get id of current counter
   */
  public int getId() {
      return this.id;
  }

  /**
   * Tell if current counter is available
   */
  public boolean isAvailable() {
      return this.available;
  }

  /**
   * Set availability of counter
   */
  public void setAvailable(boolean available) {
      this.available = available;
  }

  public static Counter getAvailableCounter(Counter[] counters) {
      for (Counter counter: counters) {
          if (counter.isAvailable()) {
              counter.setAvailable(false);
              return counter;
          }
      }
      return null;
  }

  @Override
  public String toString(){
    return String.valueOf(this.id);
  }
}