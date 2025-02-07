//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Car implements Runnable {
    private final int carId;
    private final int gateId;
    private final int arriveTime;
    private final int parkDuration;
    private final ParkingLot parkingLot;

    public Car(int carId, int gateId, int arriveTime, int parkDuration, ParkingLot parkingLot) {
        this.carId = carId;
        this.gateId = gateId;
        this.arriveTime = arriveTime;
        this.parkDuration = parkDuration;
        this.parkingLot = parkingLot;
    }

    public void run() {
        try {
            Thread.sleep((long)this.arriveTime * 1000L);
            this.parkingLot.parkCar(this.carId, this.gateId, this.parkDuration, this.arriveTime);
        } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
        }

    }
}
