public class TakeACab implements Services{
  private final double FARE = 33.0;
  private final double BOOKING_FEE = 200.0;
  
  public TakeACab(){}

  @Override
  public int computeFare(Request req){
    double total = req.getTotalFare(FARE);
    total += BOOKING_FEE;
    return (int) total;
  }

  @Override
  public String toString(){
    return "TakeACab";
  }
}
