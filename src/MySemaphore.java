public class MySemaphore {
        private int permits;

        public MySemaphore(int permits) {
            this.permits = permits;
        }

        // Acquire a permit, blocking if no permits are available
        public synchronized void acquire() throws InterruptedException {
            while (permits == 0) {
                wait();
            }
            permits--;
        }

        // Try to acquire a permit without blocking
        public synchronized boolean tryAcquire() {
            if (permits > 0) {
                permits--;
                return true;
            }
            return false;
        }

        // Release a permit, notifying waiting threads
        public synchronized void release() {
            permits++;
            notifyAll();  // Notify one or all waiting threads that a permit is available
        }

        // Get the number of available permits
        public synchronized int availablePermits() {
            return permits;
        }
    }

