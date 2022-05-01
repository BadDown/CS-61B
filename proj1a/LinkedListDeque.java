public class LinkedListDeque<T> {
    /*inner class Node*/
    public class Node {
        // point to the previous node
        private Node pre;
        // point to the next node
        private Node next;
        //the item of the Node
        private T item;

        /*constructor for Node.*/
        public Node(Node ppre,T iitem,Node nnext) {
            pre = ppre;
            item = iitem;
            next = nnext;
        }

        /*construtor for the sentinel node*/
        public Node(Node ppre,Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }

    /*the number of saving item in the Deque*/
    private int size;
    /*point to the front sentinel node*/
    private Node front_sentinel;
    /*point to the rear sentinel node*/
    private Node rear_sentinel;

    /*construtor for LinkedListDeque*/
    public LinkedListDeque() {
        size = 0;
        front_sentinel = new Node(null,null);
        rear_sentinel = new Node(null,null);
        front_sentinel.next = rear_sentinel;
        front_sentinel.pre = rear_sentinel;
        rear_sentinel.next = front_sentinel;
        rear_sentinel.pre = front_sentinel;
    }

    /*add an item to the front of the deque*/
    public void addFirst(T item) {
        Node newitem = new Node(front_sentinel,item,front_sentinel.next);
        front_sentinel.next.pre = newitem;
        front_sentinel.next = newitem;
        size++;
    }

    /*add an item to the back of the deque*/
    public void addLast(T item) {
        Node newitem = new Node(rear_sentinel.pre,item,rear_sentinel);
        rear_sentinel.pre.next = newitem;
        rear_sentinel.pre = newitem;
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
        Node pre = front_sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(pre.item + " ");
            pre = pre.next;
        }
    }

    /*remove and return the item at the front of the deque*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node pre = front_sentinel.next;
        front_sentinel.next = pre.next;
        pre.next.pre = front_sentinel;
        size--;
        return pre.item;
    }

    /*remove and return the item at the back of the deque*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node pre = rear_sentinel.pre;
        rear_sentinel.pre = pre.pre;
        pre.pre.next = rear_sentinel;
        size--;
        return pre.item;
    }

    /*get the item at the given index*/
    public T get(int index) {
        int i = 0;
        Node pre = front_sentinel.next;
        if (index >= size) {
            return null;
        }
        while (i != index) {
            i++;
            pre = pre.next;
        }
        return pre.item;
    }

    /*get the item at the fiven index with using the recursive funtion*/
    public T getRecursive(int index) {
        Node pre = front_sentinel.next;
        if (index >= size) {
            return null;
        }
        if(index == 0) {
            return pre.item;
        } else {
            pre = pre.next;
            return getRecursive(index - 1);
        }
    }

}