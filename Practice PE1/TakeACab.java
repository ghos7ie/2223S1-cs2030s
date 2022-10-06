public class TakeACab implements Ride {
  private static final int FARE = 33;
  private static final int BOOKING_FEE = 200;

  public TakeACab(){
  }

  public int computeFare(Request req){
    int totalFare = req.totalFare(FARE);
    totalFare += BOOKING_FEE;
    return totalFare;
  }

  @Override
  public String toString(){
    return "TakeACab";
  }
}
