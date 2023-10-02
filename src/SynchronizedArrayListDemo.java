
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedArrayListDemo {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add(j);
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }

        System.out.println("Size of the list: " + list.size());
    }
}
