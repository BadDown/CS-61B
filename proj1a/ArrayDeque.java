public class ArrayDeque<T> {
    private int size;
    private int length;
    private T[] array;
    private int front;
    private int rear;
    private double UsageFactor = 0.25;


    public ArrayDeque() {
    array = (T[]) new Object[8];
    size = 0;
    length = 8;
    front = 0;
    rear = 0;
    }

    /*
    *
    *   front always stands a blank space.
    *
    *
    * */
    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
        }
        array[front] = item;
        size++;
        front = Minusone(front);
    }


    /*
    *
    *   rear always stands a space where has data.
    *
    *
    *
    *
    * */
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        rear = Plusone(rear);
        array[rear] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int pre = front;
        for (int n=0 ; n < size ; n++) {
            System.out.println(array[pre]);
            pre = Minusone(pre);
       }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (UsageFactor >= size / length) {
            shrink();
        }
        T ret = array[front];
        front = Plusone(front);
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (UsageFactor >= size / length) {
            shrink();
        }
        rear = Minusone(rear);
        T ret = array[rear];
        size--;
        return ret;
    }

    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        int pre = front;
        for (int i = 0; i < index; i++) {
            pre = Minusone(pre);
        }
        return array[pre];
    }

    public int Minusone(int pre) {
        if (pre == 0){
            pre = length-1;
        } else {
            pre--;
        }
        return pre;
    }

    public int Plusone(int pre， int module) {
        index %=module;
        if (pre == length-1) {
            pre = 0;
        } else {
            pre++;
        }
        return pre;
    }

    public void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != rear) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        front = length;
        rear = ptr2;
        array = newArray;
        length *= 2;
    }

    public void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != rear) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        rear = ptr2;
        array = newArray;
        length /= 2;
    }
}