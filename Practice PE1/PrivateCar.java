public class PrivateCar extends Vehicle{

  public PrivateCar(String licensePlate, int timeTo){
    super(licensePlate, timeTo);
  }

  @Override
  public String toString(){
    String text = String.format("PrivateCar %s (%d min away)", super.licensePlate, super.timeTo);
    if(super.timeTo > 1){
      text = String.format("PrivateCar %s (%d mins away)", super.licensePlate, super.timeTo);
    }
    return text;
  }
}
