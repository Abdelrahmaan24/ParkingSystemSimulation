import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(4);  // Initialize parking lot with 4 spots

        // Read car information from input file and start each car thread
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                // Create and start a car thread for each entry in the input
                Car car = new Car(carId, gateId, arrivalTime, parkingDuration, parkingLot);
                car.start();
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
    }
    }
}