public class Cab extends Car {

  public Cab(String licensePlate, int waitingTime) {
    super(licensePlate, waitingTime);
    this.supportedServices = new Service[]{new JustRide(), new TakeACab()};
  }

  @Override
  public String toString() {
    return "Cab " + super.toString();
  }
}
