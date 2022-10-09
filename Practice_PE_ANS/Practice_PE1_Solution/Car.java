public abstract class Car implements Comparable<Car> {
  private final String licensePlate;
  private final int waitingTime;
  Service[] supportedServices;

  Car(String licensePlate, int waitingTime) {
    this.licensePlate = licensePlate;
    this.waitingTime = waitingTime;
  }

  public boolean canProvide(Service service) {
    for (Service supportedService : supportedServices) {
      if (service.equals(supportedService)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String min = "mins";
    if (waitingTime == 1) {
      min = "min";
    }
    return licensePlate + " (" + waitingTime + " " + min +" away)";
  }

  @Override
  public int compareTo(Car car) {
    return Integer.compare(this.waitingTime, car.waitingTime);
  }
}
