/**
 * @author Stunoon
 * @date 2022/5/15 15:30
 * @apiNote
 */
public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    boolean isEmpty();

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
