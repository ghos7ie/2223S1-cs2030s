package cs2030s.fp;
/** 
 * @version CS2030S Lab 4
 *          AY22/23 Semester 1
 *
 * @author Lewis Lye [14A]
 */

public abstract class Actually<T<{

   static final class Success<T> extends Actually<T> {
   private ok (T res){
    this.value = res;
   } 

   }

   static final class Failure extends Actually<Object>{

  }
}
