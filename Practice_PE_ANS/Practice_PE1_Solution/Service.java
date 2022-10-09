public abstract class Service {

  public abstract int computeFare(Request request);


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Service)) {
      return false;
    }
    return this.toString().equals(o.toString());
  }
}
