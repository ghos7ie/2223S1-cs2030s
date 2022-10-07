public class JustRide implements Services {
  private final double FARE = 22.0;
  private final double SURCHARGE = 500.0;
  private final int SURCHARGE_START = 600;
  private final int SURCHARGE_END = 900;

  public JustRide(){}

  @Override
  public int computeFare(Request req){
   double total = req.getTotalFare(FARE);
   if (req.isSurcharged(SURCHARGE_START, SURCHARGE_END) == true){
    total += SURCHARGE;
   }
   return (int) total;
  }

  @Override
  public String toString(){
    return "JustRide";
  }
}
