/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0246189R
 */
class CannotTrainException extends Exception {

  public CannotTrainException(String message) {
    super(message);
  }

  public CannotTrainException() {
    super("Cannot Train!");
  }

}


