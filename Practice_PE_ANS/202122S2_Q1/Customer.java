/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class Customer extends People {

  public Customer(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer: " + this.name;
  }
}

