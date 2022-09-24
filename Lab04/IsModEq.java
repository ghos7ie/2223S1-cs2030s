/**
 * A non-generic Immutator with parameter
 * div and mod that takes in an integer val
 * and return the boolean value by checking
 * if the given value is equal to mod when
 * divided by div.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

class IsModEq implements Immutator<Boolean, Integer> {

  private Integer div;
  private Integer check;

  /**
   * Constructor for IsModEq.
   * 
   * @param div   Integer to be divided by.
   * @param check Integer to be checked against.
   */
  public IsModEq(Integer div, Integer check) {
    this.div = div;
    this.check = check;
  }

  /**
   * Check if val % div is equal to check.
   * 
   * @param val Value that is to be mod by div.
   * @return True if remainder == check.
   *         Else return false.
   */
  @Override
  public Boolean invoke(Integer val) {
    try {
      return val % div == check;
    } catch (ArithmeticException e) {
      System.out.println("Cannot divide by zero.");
    }
    return Boolean.parseBoolean(null);
  }
}