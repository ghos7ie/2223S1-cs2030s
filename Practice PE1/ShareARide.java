public class ShareARide implements Services{
  private final double FARE = 50.0;
  private final double SURCHARGE = 500.0;
  private final int SURCHARGE_START = 600;
  private final int SURCHARGE_END = 900;

  public ShareARide(){}

  @Override
  public int computeFare(Request req){
    double actualFare = req.farePerPassenger(FARE);
    double total = req.getTotalFare(actualFare);
    if (req.isSurcharged(SURCHARGE_START, SURCHARGE_END) == true){
      total += SURCHARGE;
    }
    return (int) total;
  }


  @Override
  public String toString(){
    return "ShareARide";
  }
}
