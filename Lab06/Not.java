class Not implements Cond {
  private Cond val;
  
  public Not(Cond val) {
    this.val = val;
  }
  
  @Override
  public boolean eval() {
    return !this.val.eval();
  }
  @Override
  public String toString() {
    return "!(" + this.val + ")";
  }
  
  @Override
  public Cond neg() {
    return this.val;
  }
}