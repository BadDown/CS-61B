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
        public Node(Node ppre, T iitem, Node nnext) {
            pre = ppre;
            item = iitem;
            next = nnext;
        }

        /*construtor for the sentinel node*/
        public Node(Node ppre, Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }

    /*the number of saving item in the Deque*/
    private int size;
    /*point to the front sentinel node*/
    private Node frontsentinel;
    /*point to the rear sentinel node*/
    private Node rearsentinel;

    /*construtor for LinkedListDeque*/
    public LinkedListDeque() {
        size = 0;
        frontsentinel = new Node(null,null);
        rearsentinel = new Node(null,null);
        frontsentinel.next = rearsentinel;
        frontsentinel.pre = rearsentinel;
        rearsentinel.next = frontsentinel;
        rearsentinel.pre = frontsentinel;
    }

    /*add an item to the front of the deque*/
    public void addFirst(T item) {
        Node newitem = new Node(frontsentinel, item, frontsentinel.next);
        frontsentinel.next.pre = newitem;
        frontsentinel.next = newitem;
        size++;
    }

    /*add an item to the back of the deque*/
    public void addLast(T item) {
        Node newitem = new Node(rearsentinel.pre, item, rearsentinel);
        rearsentinel.pre.next = newitem;
        rearsentinel.pre = newitem;
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
        Node pre = frontsentinel.next;
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
        Node pre = frontsentinel.next;
        frontsentinel.next = pre.next;
        pre.next.pre = frontsentinel;
        size--;
        return pre.item;
    }

    /*remove and return the item at the back of the deque*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node pre = rearsentinel.pre;
        rearsentinel.pre = pre.pre;
        pre.pre.next = rearsentinel;
        size--;
        return pre.item;
    }

    /*get the item at the given index*/
    public T get(int index) {
        int i = 0;
        Node pre = frontsentinel.next;
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
        Node pre = frontsentinel.next;
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return pre.item;
        } else {
            pre = pre.next;
            return getRecursive(index - 1);
        }
    }

}
