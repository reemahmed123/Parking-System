//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        List<Thread> carThreads = new ArrayList();
        String inputFilePath = "CarSchedule.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));

            String line;
            try {
                while((line = br.readLine()) != null) {
                    String[] parts = line.split(", ");
                    int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                    int carId = Integer.parseInt(parts[1].split(" ")[1]);
                    int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                    int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);
                    Car car = new Car(carId, gateId, arriveTime, parkDuration, parkingLot);
                    Thread carThread = new Thread(car);
                    carThreads.add(carThread);
                }
            } catch (Throwable var15) {
                try {
                    br.close();
                } catch (Throwable var14) {
                    var15.addSuppressed(var14);
                }

                throw var15;
            }

            br.close();
        } catch (IOException var16) {
            IOException e = var16;
            e.printStackTrace();
        }

        Iterator var18 = carThreads.iterator();

        Thread carThread;
        while(var18.hasNext()) {
            carThread = (Thread)var18.next();
            carThread.start();
        }

        var18 = carThreads.iterator();

        while(var18.hasNext()) {
            carThread = (Thread)var18.next();

            try {
                carThread.join();
            } catch (InterruptedException var13) {
                Thread.currentThread().interrupt();
            }
        }

        parkingLot.printStatistics();
    }
}
