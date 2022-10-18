import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

class Bool implements Cond {
  private Memo<Boolean> val;

  public Bool(Constant<Boolean> val) {
    this.val = Memo.from(val);
  }

  @Override
  public boolean eval() {
    return this.val.get();
  }

  @Override
  public String toString() {
    return this.val.transform(s -> s.toString().substring(0, 1)).toString();
  }

  @Override
  public Cond neg() {
    return new Not(this);
  }
}