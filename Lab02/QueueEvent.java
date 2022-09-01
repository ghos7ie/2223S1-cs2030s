/**
 * This class implements Event.
 * This class represents the event where the customer joins the
 * queue.
 *
 * @author Lewis Lye [14A]
 * @version CS2030S AY21/22 Semester 2
 */
class QueueEvent extends Event {

    /**
     * Customer that is joining the queue.
     */
    private Customer customer;
    /**
     * Queue obj that contains number of customers currently in queue.
     */
    private Queue customerQueue;

    /**
     * QueueEvent Constructor
     * 
     * @param customer
     *            Customer that is joining the queue.
     * @param customerQueue
     *            Queue obj that contains number of customers currently in queue.
     * @param queueTime
     *            Time that customer is joining the queue.
     */
    public QueueEvent(Customer customer, Queue customerQueue, double queueTime) {
        super(queueTime);
        this.customer = customer;
        this.customerQueue = customerQueue;
    }

    /**
     * Simulate Queue event.
     * Adds customer to customerQueue.
     * 
     * @return An array of new events to be scheduled by the simulator.
     *         Returns empty Event[] as nothing happens after joining queue.
     */
    @Override
    public Event[] simulate() {
        this.customerQueue.enq(this.customer);
        return new Event[] {};
    }

    /**
     * Returns the string representation of this event.
     * 
     * @return String representation of an queue event.
     */
    @Override
    public String toString() {
        String str = String.format(": %s joined queue %s", this.customer, this.customerQueue);
        return super.toString() + str;
    }
}
