public class Request {
  
  private int distance;
  private int noOfPassengers;
  private int timeOfRequest;

  public Request(int distance, int noOfPassengers, int timeOfRequest){
    this.distance = distance;
    this.noOfPassengers = noOfPassengers;
    this.timeOfRequest = timeOfRequest;
  }

  public int totalFare(int fare){
    return distance * fare;
  }
  
  public double totalFare(double fare){
    double dist = this.distance;
    return dist * fare;
  }

  public boolean chargeSurcharge(int startTime, int endTime){
    return (this.timeOfRequest <= endTime) && (this.timeOfRequest >= startTime);
  }

  public double farePerPassenger(int fare){
    return fare/noOfPassengers;
  } 

}
