/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class Treadmill extends Equipment {

  private double speed;

  

  public Treadmill() {
    this.inUse = false;
  }

  public double getSpeed() {
    return this.speed;
  }

  public void setSpeed(double newSpeed) {
    this.speed = newSpeed;
  }

  @Override
  public void repair() {
    this.speed = 0;
  }
  
  @Override
  public String toString() {
    return "Treadmill: " + this.speed + " km/h";
  }
}
