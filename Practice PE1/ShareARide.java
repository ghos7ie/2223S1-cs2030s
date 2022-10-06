public class ShareARide implements Ride{
  private static final int FARE = 50;
  private static final int SURCHARGE = 500;
  private static final int SURCHARGE_START_TIME = 600;
  private static final int SURCHARGE_END_TIME = 900;

  public ShareARide(){
  }

  public int computeFare(Request req){
    int actualFare = FARE;
    double farePerPassenger = req.farePerPassenger(actualFare);
    double totalFare = req.totalFare(farePerPassenger);
    if (req.chargeSurcharge(SURCHARGE_START_TIME, SURCHARGE_END_TIME) == true){
      totalFare += SURCHARGE;
    }
    return (int)totalFare;
  }
 
  @Override
  public String toString(){
    return "ShareARide";
  }
}
