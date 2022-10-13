class Or implements Cond {
  private Cond lVal;
  private Cond rVal;
  
  public Or(Cond lVal, Cond rVal) {
    this.lVal = lVal;
    this.rVal = rVal;
  }
  
  @Override
  public boolean eval() {
    return this.lVal.eval() || this.rVal.eval();
  }
  
  @Override
  public String toString() {
    return "(" + this.lVal + " | " + this.rVal + ")";
  }
  
  @Override
  public Cond neg() {
    return new And(lVal.neg(), rVal.neg());
  }
}