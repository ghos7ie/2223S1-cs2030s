import cs2030s.fp.Constant;

class Bool implements Cond {
  private Boolean val;
  
  public Bool(Constant<Boolean> val) {
    this.val = val.init();
  }
  
  @Override
  public boolean eval() {
    return this.val;
  }
  
  @Override
  public String toString() {
    return this.val.toString().substring(0, 1);
  }
  
  @Override
  public Cond neg() {
    return new Not(this);
  }
}