abstract class Vehicle {
  protected String licensePlate;
  // in Minutes
  protected int timeTo;

  public Vehicle(String licensePlate, int timeTo){
    this.licensePlate = licensePlate;
    this.timeTo = timeTo;
  }
}
