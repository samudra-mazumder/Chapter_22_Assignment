import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentStack<T> {
    private ConcurrentLinkedDeque<T> stack = new ConcurrentLinkedDeque<>();

    public void push(T item) {
        stack.push(item);
    }

    public T pop() {
        return stack.poll();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}