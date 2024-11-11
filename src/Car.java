public class Car extends Thread {
    private final int carId;
    private final int gateId;
    private final int arrivalTime;
    private final int parkingDuration;
    private final ParkingLot parkingLot;

    public Car(int carId, int gateId, int arrivalTime, int parkingDuration, ParkingLot parkingLot) {
        this.carId = carId;
        this.gateId = gateId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            // Simulate car arrival time
            Thread.sleep(arrivalTime * 1000L);

            String carInfo = "Car " + carId + " from Gate " + gateId;
            System.out.println(carInfo + " arrived at time " + arrivalTime);

            // Attempt to acquire a parking spot
            parkingLot.acquireSpot(carInfo);

            // Simulate the time the car spends in the parking lot
            Thread.sleep(parkingDuration * 1000L);

            // Leave the parking spot
            parkingLot.releaseSpot(carInfo , this.parkingDuration);

        } catch (InterruptedException e) {
            System.out.println("Car " + carId + " was interrupted.");
        }
    }
}
