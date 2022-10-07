public class Request {
  private int distance;
  private int noOfPassengers;
  private int time;

  public Request(int distance, int noOfPassengers, int time){
    this.distance = distance;
    this.noOfPassengers = noOfPassengers;
    this.time = time;
  }

  // test
  public double getTotalFare(double fare){
    return fare * this.distance;
  }

  public boolean isSurcharged(int startTime, int endTime){
    return ((time >= startTime) && (time <= endTime));
  }


  public double farePerPassenger(double fare){
    return fare/this.noOfPassengers;
  }

  public int compareTime(Request req){
    if (this.time > req.time){
      return 1;
    } else if (this.time == req.time){
      return 1;
    } else{
      return -1;
    }
  }
}
