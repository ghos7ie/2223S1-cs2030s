public class Booking implements Comparable<Booking> {
    private final Car car;
    private Service service;
    private final int fare;
    public Booking(Car car, Service service, Request request) throws IllegalArgumentException {
        this.car = car;
        this.service = service;

        if (!car.canProvide(service)) {
            throw new IllegalArgumentException(car + " does not provide the "
                    + service + " service.");
        }

        this.fare = service.computeFare(request);
    }
    @Override
    public int compareTo(Booking other) {
        if (this.fare == other.fare) {
            return this.car.compareTo(other.car);
        }
        return Integer.compare(this.fare, other.fare);
    }
}
