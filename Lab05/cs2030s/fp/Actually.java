package cs2030s.fp;

/** 
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T>{

  /**
   *  ok(res) returns a Success Object.
   *  @param res variable of type T to put into Success.
   *
   *  @return new Success().
   *
   */ 
  public static <T> Success<T> ok (T res){
    return new Success<> (res);
  }

  /**
   * err(exception) returns a Failure Object.
   * 
   * @param exception object of type exception.
   *
   * @return new Failure().
   */
  public static <T> Failure err(Exception exception){
    return new Failure(exception);
  }

  @Override
  public boolean equals (Object obj){
    if (obj == this){
      return true;
    }else{
      return false;
    }
  }

  static final class Success<T> extends Actually<T> {
     
    private final T value;
    
    /**
     * Constuctor for Success class.
     *
     * @param val value of type T.
     *
     */
    public Success (T val){
       this.value = val;
     }

    /**
     * Equals implementation for Success class.
     *
     * @param object type of Object.
     *
     * @return true if value of object is equal to value of Success.
     *         false i f not equal.
     */ 
    @Override
    public boolean equals (Object obj){
      if (obj instanceof Success){
        Success<?> some = (Success<?>) obj;
        if (some.value == null){
          return false;
        }
        else if (some.value == this.value){
          return true;
        }
        else{
          return false;
        }
      }
      else{
        return false;
      }
    }

    /**
     * Returns String representation of Success object.
     *
     * @return {@code <null>} if this.value is null.
     *         value.toString() if not null.
     */
     @Override
     public String toString(){
       if (this.value != null){
         return "<" + this.value.toString() + ">";
       } 
       else{
       return "<null>";
       }
     }
   }

   static final class Failure extends Actually<Object>{
     
     private final Exception obj;
     
     /**
      * Constructor for Failure class.
      *
      * @param obj obj of type Exception.
      *
      */ 
     public Failure (Exception obj){
       this.obj = obj;
     }

      
    /** 
     * Equals implementation for Failure class.
     *
     * @param object type of Object.
     *
     * @return true if getMessage() of object is equal to that of Failure.
     *         false if not equal.
     */   
    @Override
    public boolean equals (Object obj){
      if (obj instanceof Failure){
        Failure some = (Failure) obj;
        if (some.obj.getMessage() == this.obj.getMessage()){
          return true;
        }
        else{
          return false;
        }
      }
      else{
        return false;
      }
    }

     /**
      * Returns String representation of Failure object.
      *
      * @return value in formatted manner.
      */ 
     @Override
     public String toString(){
       return "[" + this.obj.getClass().toString() + "] " + this.obj.getMessage();
     }
  }
}
