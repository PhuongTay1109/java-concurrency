import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RaceConditionExample4 {

    public static void main(String[] args) {

        // Mặc dù ConcurrentHashMap đảm bảo các thao tác riêng lẻ như put, remove,
        // và containsKey là thread-safe (an toàn trong môi trường đa luồng),
        // nhưng chuỗi các thao tác này không phải là atomic
        // (không được thực hiện toàn bộ như một khối không thể bị gián đoạn).
        Map<String, String> sharedMap = new ConcurrentHashMap<>();
;
        Thread thread1 = new Thread(getRunnable(sharedMap));
        Thread thread2 = new Thread(getRunnable(sharedMap));

        thread1.start();
        thread2.start();
    }

    private static Runnable getRunnable(Map<String, String> sharedMap) {
        return () -> {
            for (int i = 0; i < 1000000; i++) {
                if(sharedMap.containsKey("key")) {
                    String val = sharedMap.remove("key");
                    if (val == null) {
                        System.out.println("Iteration: " + i + ": Value for 'key' was null");
                    }
                } else {
                    sharedMap.put("key", "value");
                }
            }
        };
    }
}
