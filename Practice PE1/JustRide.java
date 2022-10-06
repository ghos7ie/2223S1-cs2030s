public class JustRide implements Ride {
  private static final int FARE = 22;
  private static final int SURCHARGE = 500;
  private static final int SURCHARGE_START_TIME = 600;
  private static final int SURCHARGE_END_TIME = 900;

  public JustRide(){
  }

  public int computeFare(Request req){
    int totalFare = req.totalFare(FARE);
    // if requests occurs during surcharge period, add SURCHARGE to total cost
    if (req.chargeSurcharge(SURCHARGE_START_TIME, SURCHARGE_END_TIME)){
      totalFare += SURCHARGE;
    }
    return totalFare;
  }

  @Override
  public String toString(){
    return "JustRide";
  }


}
