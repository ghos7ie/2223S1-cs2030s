package cs2030s.fp;

/** 
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T>{

  private final T value;

  public static <T> Success<T> ok (T res){
    return new Success<> (res);
  }

  public static <T> Failure err(Exception exception){
    return new Failure(exception);
  }

   static final class Success<T> extends Actually<T> {
     
     private final T value;

     public Success<T> (value){
       this.value = value;
     }

     @Override
     public String toString(){
       return "<" + this.value.toString() + ">";
     }
   }

   static final class Failure extends Actually<Object>{
      
     private final Object obj;
     
     public Failure (Object obj){
       this.obj = obj;
     }

     @Override
     public String toString(){
       return "[" + this.obj.getClass() + "] " + this.obj.getMessage();
     }
  }
}
