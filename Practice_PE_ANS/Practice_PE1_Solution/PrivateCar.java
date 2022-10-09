public class PrivateCar extends Car {

  public PrivateCar(String licensePlate, int waitingTime) {
    super(licensePlate, waitingTime);
    this.supportedServices = new Service[]{new JustRide(), new ShareARide()};
  }

  @Override
  public String toString() {
    return "PrivateCar " + super.toString();
  }
}
