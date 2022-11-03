/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class Gym {

  private static int capacity;
  private static int num;

  public Gym(int capacity) {
    Gym.capacity = capacity;
    Gym.num = 0;
  }

  @Override
  public String toString() {
    return "Gym Capacity: " + Gym.num + "/" + Gym.capacity;
  }

  public void enter(People p) {
    if (Gym.num < Gym.capacity) {
      Gym.num++;
      System.out.println(p.toString() + " can enter");
    } else {
      System.out.println(p.toString() + " cannot enter");
    }
  }
}
