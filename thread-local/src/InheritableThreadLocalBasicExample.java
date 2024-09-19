public class InheritableThreadLocalBasicExample {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread = new Thread(() -> {
            System.out.println("Parent thread");
            threadLocal.set("Parent Thread - Thread Local");
            inheritableThreadLocal.set("Parent Threat - Inherited Thread");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread child = new Thread(() -> {
                System.out.println("Child thread");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
            });

            child.start();
        });

        thread.start();
    }
}
