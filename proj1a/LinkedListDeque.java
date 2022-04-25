public class LinkedListDeque<T> {

    public class Node {
        private Node pre;
        private Node next;
        private T item;

        public Node(Node front,T n,Node rear) {
            this.item = n;
            this.pre = front;
            this.next = rear;
        }

        public Node() {
            pre = null;
            next = null;
        }
    }

    private int size;
    private Node sential;
    public LinkedListDeque() {
        sential = new Node();
        sential.pre = sential;
        sential.next = sential;
    }


    public void addFirst(T item) {
        Node newList = new Node(sential,item,sential.next);
        sential.next.pre = newList;
        sential.next = newList;
    }

    public void addLast(T item) {
        Node newList = new Node(sential.pre,item,sential);
        sential.pre.next = newList;
        sential.pre = newList;
    }

    public boolean isEmpty() {
        return size;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node PrintNode = sential;
        for (int n = 0;n < size;n++) {
            PrintNode = PrintNode.next;
            System.out.print(PrintNode.item + ' ');
        }
    }

    public T removeFirst() {
        if (!size) {
            return null;
        }
        Node remove = sential.next;
        sential.next.next.pre = sential;
        sential.next = sential.next.next;
        return remove.item;
    }

    public T removeLast() {
        if (!size) {
            return null;
        }
        Node remove = sential.pre;
        sential.pre.pre.next = sential;
        sential.pre = sential.pre.pre;
        return remove.item;
    }

    public T get(int index) {
        Node getitem = sential;
        if (size && index < size) {
            for (int n = 0; n - 1 != index; n++) {
                getitem=getitem.next;
                if (n == index) {
                    return getitem.item;
                }
            }
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        Node pre = sential.next;
        if (size && index < size) {
            if (!index) {
                return pre.item;
            } else {
                pre = pre.next;
                return getRecursive(index - 1);
            }
        } else {
            return null;
        }
    }
}
