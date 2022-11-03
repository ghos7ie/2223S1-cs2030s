/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */

abstract class Equipment {
  
  public boolean inUse;

  public void setInUse(boolean b) {
    this.inUse = b;
  }

  public boolean isInUse() {
    return this.inUse;
  }

  public abstract void repair();

}

