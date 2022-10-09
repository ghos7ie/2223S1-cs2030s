/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class Dumbbell extends Equipment {

  
  private final double weight;
  private int numRepair;
  public Dumbbell(double weight) {
    this.weight = weight;
    this.inUse = false;
    this.numRepair = 0;
  }

  public double getWeight() {
    return this.weight;
  }
  
  @Override
  public void repair() {
    numRepair++;
  }

  @Override
  public String toString() {
    return "Dumbbell: " + this.weight + " kg";
  }


  public int getRepairCount() {
    return this.numRepair;
  }

}

