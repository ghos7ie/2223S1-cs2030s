/**
 * A non-generic Action to print the String
 * representation of the object.
 *
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

// Must define Action<Object> so that the
// class knows what it can work with.
class Print implements Action<Object> {

  /**
   * This method will print out the string representation
   * of the item class.
   * 
   * @param item The item that is going to be printed.
   * 
   */
  public void call(Object item) {
    System.out.println(item);
  }
}
