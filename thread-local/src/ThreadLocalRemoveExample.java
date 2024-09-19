public class ThreadLocalRemoveExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            threadLocal.set("thread 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String value = threadLocal.get();
            System.out.println(value);

            threadLocal.remove();
            value = threadLocal.get();
            System.out.println(value);
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set("thread 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String value = threadLocal.get();
            System.out.println(value);
        });

        thread1.start();
        thread2.start();
    }
}
