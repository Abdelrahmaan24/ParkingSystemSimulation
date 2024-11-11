import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore parkingSpots;
    private int parkedCars = 0;
    private int totalCarsServed = 0;

    public ParkingLot(int totalSpots) {
        parkingSpots = new Semaphore(totalSpots);
    }

    public synchronized void parkCar(String carInfo) {
        parkedCars++;
        totalCarsServed++;
        System.out.println(carInfo + " parked. (Parking Status: " + parkedCars + " spots occupied)");
    }


    public synchronized void leaveCar(String carInfo, int parkDuration) {
        parkedCars--;
        System.out.println(carInfo + " left after "+ parkDuration+" units of time " + "(Parking Status: " + parkedCars + " spots occupied)");
    }

    public void acquireSpot(String carInfo) throws InterruptedException {

        if (parkingSpots.tryAcquire()){
            parkCar(carInfo);
        }
        else {
            System.out.println(carInfo + " waiting for a spot");
            while (true){
                if (parkingSpots.tryAcquire()){
                    parkCar(carInfo);
                    break;
                }
            }
        }
    }

    public void releaseSpot(String carInfo , int parkingDuration) {
        leaveCar(carInfo, parkingDuration);
        parkingSpots.release();
    }

    public int getTotalCarsServed() {
        return totalCarsServed;
    }
}
