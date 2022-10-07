public class Cab extends Vehicle {
 
  public Cab(String licensePlate, int timeTo){
    super(licensePlate, timeTo);
  }

  @Override
  public String toString(){
    String text = String.format("Cab %s (%d min away)", super.licensePlate, super.timeTo);
    if(super.timeTo > 1){
      text = String.format("Cab %s (%d mins away)", super.licensePlate, super.timeTo);
    }
    return text;
  }
}
