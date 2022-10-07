public class Booking implements Comparable<Booking>{
  private Vehicle vehicle;
  private Services service;
  private Request request;
  private final String[] SERVICE_MAP = {"JustRide", "TakeACab", "ShareARide"};

  public Booking(Vehicle vehicle,Services service, Request request){
    if (this.vehicle instanceof Cab){
      if(this.service.toString() == SERVICE_MAP[2]){
        throw new IllegalArgumentException(
            this.vehicle.toString() + " does not provide the " 
            + SERVICE_MAP[2] + "service");
      }
    }
    if(this.vehicle instanceof PrivateCar){
      if(this.service.toString() == SERVICE_MAP[1]){
       throw new IllegalArgumentException(
           this.vehicle.toString() + " does not provide the "
           + SERVICE_MAP[1] + "service");
    }
    this.vehicle = vehicle;
    this.service = service;
    this.request = request;
   }
  }

  @Override
  public int compareTo(Booking booking){
    int thisFare = this.service.computeFare(this.request);
    int thatFare = booking.service.computeFare(booking.request);
    if (thisFare > thatFare){
      return 1;
    } else if (thisFare == thatFare){
      return this.request.compareTime(booking.request);
    } else{
      return -1;
    }
  }
 }
      
