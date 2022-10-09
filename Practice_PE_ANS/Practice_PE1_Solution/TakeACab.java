public class TakeACab extends Service {

  public static final int RATE = 33;

  @Override
  public int computeFare(Request request) {
    int totalFare = 200;
    totalFare += request.getDistance() * RATE;
    return totalFare;
  }

  @Override
  public String toString() {
    return "TakeACab";
  }
}
