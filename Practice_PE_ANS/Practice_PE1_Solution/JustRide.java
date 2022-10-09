public class JustRide extends Service {

  public static final int RATE = 22;
  public static final int SURCHARGE = 500;

  @Override
  public int computeFare(Request request) {
    int totalFare = 0;
    if (request.getTime() <= 900 && request.getTime() >= 600) {
      totalFare = SURCHARGE;
    }
    totalFare += RATE * request.getDistance();
    return totalFare;
  }
  
  @Override
  public String toString() {
    return "JustRide";
  }
}
