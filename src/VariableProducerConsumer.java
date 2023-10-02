import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VariableProducerConsumer {
    private static final int MAX_SIZE = 10;
    private static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of producer threads: ");
        int numProducers = scanner.nextInt();
        System.out.print("Enter the number of consumer threads: ");
        int numConsumers = scanner.nextInt();

        for (int i = 0; i < numProducers; i++) {
            Thread producer = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    produce("Item " + j);
                }
            });
            producer.start();
        }

        for (int i = 0; i < numConsumers; i++) {
            Thread consumer = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    consume();
                }
            });
            consumer.start();
        }
    }

    private static synchronized void produce(String item) {
        while (queue.size() >= MAX_SIZE) {
            try {
                VariableProducerConsumer.class.wait(); // Use the class as the monitor object
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        VariableProducerConsumer.class.notify(); // Use the class as the monitor object
    }

    private static synchronized void consume() {
        while (queue.isEmpty()) {
            try {
                VariableProducerConsumer.class.wait(); // Use the class as the monitor object
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String item = queue.poll();
        System.out.println("Consumed: " + item);
        VariableProducerConsumer.class.notify(); // Use the class as the monitor object
    }
}