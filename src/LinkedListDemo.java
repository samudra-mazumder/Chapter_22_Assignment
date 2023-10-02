import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        int numThreads = 4;

        Runnable addTask = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        Runnable removeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                list.removeLast();
            }
        };

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            if (i % 2 == 0) {
                threads[i] = new Thread(addTask);
            } else {
                threads[i] = new Thread(removeTask);
            }
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final size of the list: " + list.size());
    }
}