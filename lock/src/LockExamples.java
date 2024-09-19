import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExamples {

    public static void main(String[] args) {
        lockBasics();
    }

    private static void lockBasics() {
        Lock lock = new ReentrantLock(false);

        Runnable runnable = () -> {
            lockSleepUnlock(lock, 1000);
        };

        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        Thread thread3 = new Thread(runnable, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void lockSleepUnlock(Lock lock, int sleepTime) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " has acquired the lock");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " is releasing the lock");
            lock.unlock();
        }
    }
}
