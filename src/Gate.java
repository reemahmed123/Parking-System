//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class Gate {
    List<Thread> carsInQueue = new ArrayList();

    public Gate() {
    }

    public synchronized void add(Thread car) {
        this.carsInQueue.add(car);
    }

    public synchronized Thread getFirst() {
        return (Thread)this.carsInQueue.getFirst();
    }

    public synchronized void remove(Thread car) {
        this.carsInQueue.remove(car);
    }
}
