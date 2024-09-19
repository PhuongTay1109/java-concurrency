public class ThreadLocalInitialValueExample {
    static class MyObject {
        public MyObject() {}
    }
    public static void main(String[] args) {
        ThreadLocal<MyObject> threadLocal1 = new ThreadLocal<>() {
            @Override
            protected MyObject initialValue() {
                return new MyObject();
            }
        };

        ThreadLocal<MyObject> threadLocal2 = ThreadLocal.withInitial(MyObject::new);

        Thread thread1 = new Thread(() -> {
            System.out.println("thread local 1 " + threadLocal1.get());
            System.out.println("thread local 2 " + threadLocal2.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread local 1 " + threadLocal1.get());
            System.out.println("thread local 2 " + threadLocal2.get());
        });

        // Diffenent thread do not share initial value

        thread1.start();
        thread2.start();
    }
}
