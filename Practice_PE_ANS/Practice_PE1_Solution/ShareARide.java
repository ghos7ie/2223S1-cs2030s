public class ShareARide extends Service {

  public static final int SURCHARGE = 500;
  public static final int RATE = 50;

  @Override
  public int computeFare(Request request) {
    int totalFare = RATE * request.getDistance() / request.getNumber();
    if (request.getTime() <= 900 && request.getTime() >= 600) {
      totalFare += SURCHARGE;
    }
    return totalFare;
  }

  @Override
  public String toString() {
    return "ShareARide";
  }
}
