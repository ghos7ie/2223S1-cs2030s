/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
class Trainer extends People {

  private Customer trainee;
  private Equipment equipment;
  private boolean isTraining;

  public Trainer(String name) {
    this.name = name;
    this.isTraining = false;
  }

  @Override
  public String toString() {
    return "Trainer: " + this.name;
  }

  public String getStatus() {
    if (isTraining) {
      return "Trainer: " + this.name + " training " + this.trainee.toString();
    } else {
      return "Trainer: " + this.name + " not training";
    }
  }

  public void startTraining(Customer customer, Equipment equipment) throws CannotTrainException {
    if (!isTraining && !equipment.isInUse()) {
      this.trainee = customer;
      this.equipment = equipment;
      this.isTraining = true;
      this.equipment.setInUse(true);
    } else {
      throw new CannotTrainException("Cannot Train!");
    }
  }

  public void stopTraining() {
    this.isTraining = false;
    this.equipment.setInUse(false);
  }


}


