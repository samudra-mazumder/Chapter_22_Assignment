import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int MAX_SIZE = 10;
    private static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                produce("Item " + i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                consume();
            }
        });

        producer.start();
        consumer.start();
    }

    private static synchronized void produce(String item) {
        while (queue.size() >= MAX_SIZE) {
            try {
                ProducerConsumer.class.wait(); // Use the class as the monitor object
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        ProducerConsumer.class.notify(); // Use the class as the monitor object
    }

    private static synchronized void consume() {
        while (queue.isEmpty()) {
            try {
                ProducerConsumer.class.wait(); // Use the class as the monitor object
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String item = queue.poll();
        System.out.println("Consumed: " + item);
        ProducerConsumer.class.notify(); // Use the class as the monitor object
    }
}