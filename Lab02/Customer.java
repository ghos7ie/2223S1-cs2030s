/**
 * This is the Customer class.
 * It is used to track the id of the Customer, time of arrival and
 * period of service.
 * 
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class Customer {
  /**
   * The universal customer id - keeps track of number of customers created.
   */
  private static int count = 0;

  /**
   * The id of the customer.
   */
  private int id;

  /**
   * Period customer is served.
   */
  private double serviceTime;

  /**
   * Customer Constructor.
   * 
   * @param serviceTime Service time of customer.
   */
  public Customer(double serviceTime) {
    this.id = count++;
    this.serviceTime = serviceTime;
  }

  /**
   * Service time of customer.
   * 
   * @return Returns service time of customer.
   */
  public double getServiceTime() {
    return this.serviceTime;
  }

  /**
   * Return the string represenation of this customer.
   * 
   * @return A string consists of the Id of the customer.
   */
  @Override
  public String toString() {
    return "C" + this.id;
  }
}