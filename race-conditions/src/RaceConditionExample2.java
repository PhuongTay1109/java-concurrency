public class RaceConditionExample2 {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread thread1 = new Thread(getIncrementingRunnable(counter));
        Thread thread2 = new Thread(getReadingRunnable(counter));

        thread1.start();
        thread2.start();
    }

    private static Runnable getIncrementingRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 1000000; i++) {
                counter.incAndGet();
            }
            System.out.println("Thread 1 final counter: " + counter.get());
        };
    }

    private static Runnable getReadingRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2 counter: " + counter.get());
            }
        };
    }
}
