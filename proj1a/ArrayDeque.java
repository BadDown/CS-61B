public class ArrayDeque<T> {
    /*the number of saving item in the Deque*/
    private int size;
    /*the length of the array*/
    private int length;
    /*point to the array*/
    private T[] array;
    /*front index*/
    private int front;
    /*rear index*/
    private int rear;

    /*construtor for ArrayDeque*/
    public ArrayDeque() {
        size = 0;
        length = 8;
        array = (T[]) new Object[length];
        front = 0;
        rear = 0;
    }

    /*
    * front always stand in the position which has no data.
    *
    * rear always stand in the position which has data.
    *
    *
    *
    * */



    /*Check if the array needs to be resized*/
    private void checkArray() {
        if (length <= 16) {
            return;
        }
        if (length == size) {
            resize();
        }
        if (size <= (length + 3) * 0.25) {
            resize();
        }
    }

    /*resize the array*/
    private void resize() {
        if (size == length) {
            T[] newArray = (T[]) new Object[length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            front = 0;
            rear = size - 1;
            length = length * 2;
            array = newArray;
        } else {
            T[] newArray = (T[]) new Object[length / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            front = 0;
            length = length / 2;
            rear = size - 1;
            array = newArray;
        }
    }

    /*return index + 1*/
    private int plusOne(int index) {
        if (index == length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    /*return index - 1*/
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        } else {
            return index - 1;
        }
    }

    /*add an item to the front of the deque*/
    public void addFirst(T item) {
        checkArray();
        array[front] = item;
        size++;
        front = minusOne(front);
    }

    /*add an item to the back of the deque*/
    public void addLast(T item) {
        checkArray();
        rear = plusOne(rear);
        array[rear] = item;
        size++;
    }

    /*return true if deque is empty*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*return the number of items in the deque*/
    public int size() {
        return size;
    }

    /*print the items in the deque from first to last*/
    public void printDeque() {
        int pre = front;
        while (pre != rear) {
            pre = plusOne(pre);
            System.out.print(array[pre] + " ");
        }
    }

    /*remove and return the item at the front of the deque*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        front = plusOne(front);
        T ret = array[front];
        size--;
        checkArray();
        return ret;
    }

    /*remove and return the item at the back of the deque*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = array[rear];
        rear = minusOne(rear);
        size--;
        checkArray();
        return ret;
    }

    /*get the item at the given index*/
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int pre = minusOne(front);
        for (int i = 0; i < index; i++) {
            pre = minusOne(pre);
        }
        return array[pre];
    }
}
