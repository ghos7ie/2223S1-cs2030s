/**
 * This class
 * 
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */

class Customer {
    /**
     * The universal counter id - keeps track of number of customers created
     */
    private static int count = 0;

    /**
     * The id of the customer
     */
    private int id;

    /**
     * The time of customer's arrival
     */
    private double arrivalTime;

    /**
     * How long customer will be served
     */
    private double serviceTime;

    public Customer(double arrivalTime, double serviceTime) {
        this.id = count++;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public double getArrivalTime(){
        return this.arrivalTime;
    }

    public double getServiceTime(){
        return this.serviceTime;
    }

    @Override
    public String toString(){
        return String.valueOf(this.id);
    }
}