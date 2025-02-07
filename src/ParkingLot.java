//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.concurrent.Semaphore;   built in java semaphore

class ParkingLot {
    private final MySemaphore parkingSpots = new MySemaphore(4); //implemented symaphore
    //private final Semaphore parkingSpots = new Semaphore(4);  built in java semaphore
    private final int totalSpots = 4;
    private int occupiedSpots = 0;
    private int totalCarsServed = 0;
    private static final Object gatesLock = new Object();
    private static final Object countLock = new Object();
    private final Map<Integer, Integer> gateCarCount = new HashMap();
    private static Gate[] gates = new Gate[3];

    public ParkingLot() {
    }

    public synchronized void park(int carId, int gateId, int parkDuration, int arrivalTime) {
        ++this.occupiedSpots;
        System.out.println("Car " + carId + " from Gate " + gateId + " parked. (Parking Status: " + this.occupiedSpots + " spots occupied)");
        gates[gateId - 1].remove(Thread.currentThread());
    }

    public synchronized void parkAfterWait(int carId, int gateId, int parkDuration, int arrivalTime , double waitingTime) {
        ++this.occupiedSpots;
        System.out.println("Car " + carId + " from Gate " + gateId + " parked after waiting for " + waitingTime + " units of time. (Parking Status: " + occupiedSpots + " spots occupied)");
        gates[gateId - 1].remove(Thread.currentThread());
    }

    public synchronized void leave(int carId, int gateId, int parkDuration, int arrivalTime) {
        --this.occupiedSpots;
        System.out.println("Car " + carId + " from Gate " + gateId + " left after " + parkDuration + " units of time. (Parking Status: " + this.occupiedSpots + " spots occupied)");
    }

    public void parkCar(int carId, int gateId, int parkDuration, int arrivalTime) {
        double waitStartTime = System.currentTimeMillis(); // Start waiting time
        try {
            boolean isParked = false;
            synchronized(gatesLock) {
                System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arrivalTime);
                gates[gateId - 1].add(Thread.currentThread());
                if (this.parkingSpots.tryAcquire()) {
                    this.park(carId, gateId, parkDuration, arrivalTime);
                    isParked = true;
                } else {
                    System.out.println("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
                }
            }

            if (!isParked) {
                while(true) {
                    if (gates[gateId - 1].getFirst() == Thread.currentThread()) {
                        this.parkingSpots.acquire();
                        double waitEndTime = System.currentTimeMillis(); // End waiting time
                        double waitingTime = Math.ceil((waitEndTime - waitStartTime)/1000); // Calculate waiting time in seconds
                        this.parkAfterWait(carId, gateId, parkDuration, arrivalTime,waitingTime);
                        break;
                    }
                }
            }

            Thread.sleep((long)parkDuration * 1000L);
            this.leave(carId, gateId, parkDuration, arrivalTime);
            this.parkingSpots.release();
            synchronized(countLock) {
                ++this.totalCarsServed;
                this.gateCarCount.put(gateId, (Integer)this.gateCarCount.getOrDefault(gateId, 0) + 1);
            }
        } catch (InterruptedException var11) {
            Thread.currentThread().interrupt();
        }

    }

    public void printStatistics() {
        System.out.println("Total Cars Served: " + this.totalCarsServed);
        System.out.println("Current Cars in Parking: " + (4 - this.parkingSpots.availablePermits()));
        Iterator var1 = this.gateCarCount.entrySet().iterator();

        while(var1.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry)var1.next();
            PrintStream var10000 = System.out;
            String var10001 = String.valueOf(entry.getKey());
            var10000.println("Gate " + var10001 + " served " + String.valueOf(entry.getValue()) + " cars.");
        }

    }

    static {
        gates[0] = new Gate();
        gates[1] = new Gate();
        gates[2] = new Gate();
    }
}
