public class Request {
  private final int distance;
  private final int number;
  private final int time;

  public Request(int distance, int number, int time) {
    this.distance = distance;
    this.number = number;
    this.time = time;
  }

  public int getDistance() {
    return this.distance;
  }

  public int getTime() {
    return this.time;
  }

  public int getNumber() {
    return this.number;
  }
}
