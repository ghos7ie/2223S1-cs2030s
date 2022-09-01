/**
 * This class implements Event.
 * This class represents the event where the customer joins the queue.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class QueueEvent extends Event {

    private Customer customer;
    private Queue customerQueue;

    /**
     * QueueEvent Constructor
     * 
     * @param customer Customer obj
     * @param customerQueue Queue obj
     * @param queueTime Time that customer joins the queue
     */
    public QueueEvent(Customer customer, Queue customerQueue, double queueTime) {
        super(queueTime);
        this.customer = customer;
        this.customerQueue = customerQueue;
    }

    @Override
    public Event[] simulate() {
        return new Event[] {};
    }

    @Override
    public String toString() {
        String str = String.format(": %s joined queue %s", this.customer, this.customerQueue);
        return super.toString() + str;
    }
}
