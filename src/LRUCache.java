class Node {
    int key;
    char value;
    Node next;
    Node previous;

    Node (int key, char value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.previous = null;
    }
}

class LRUCache {
    int maxLength;
    Node head;

    LRUCache (int maxLength) {
        this.maxLength = maxLength;
        this.head = null;
    }

    private int getLength () {
        int count = 0;
        Node node = this.head;

        while (node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    private Node getNodeByKey (int key) {
        Node node = this.head;

        while (node != null) {
            if (key == node.key) {
                return node;
            }

            node = node.next;
        }

        return node;
    }

    private void shiftNodeToTop (Node node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        }

        node.previous = this.head.previous;
        node.next = this.head;
        this.head.previous = node;
        this.head = node;
    }

    private void removeTailNode () {
        Node node = this.head;

        while (node.next != null) {
            node = node.next;
        }

        Node previous = node.previous;
        previous.next = null;
    }

    void print () {
        Node node = this.head;

        while (node.next != null) {
            System.out.print("( " + node.key + " , " + node.value + " ) --> ");
            node = node.next;
        }

        System.out.print("( " + node.key + " , " + node.value + " )");
        System.out.println();
    }

    void put (int key, char value) {
        Node node = this.getNodeByKey(key);

        if (node != null) {
            node.value = value;
            this.shiftNodeToTop(node);
            return;
        }

        node = new Node(key, value);

        if (this.getLength() == maxLength) {
            this.removeTailNode();
        }

        if (this.head != null) {
            node.next = this.head;
            this.head.previous = node;
        }

        this.head = node;
    }

    Node get (int key) {
        Node node = this.getNodeByKey(key);

        if (node == null) {
            return node;
        }

        this.shiftNodeToTop(node);

        return node;
    }
}

class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        System.out.println("put (key=1, value=A) into the cache:");
        cache.put(1, 'A');
        cache.print();

        System.out.println("put (key=2, value=B) into the cache:");
        cache.put(2, 'B');
        cache.print();

        System.out.println("put (key=3, value=C) into the cache:");
        cache.put(3, 'C');
        cache.print();

        System.out.println("get (key=2) from the cache:");
        cache.get(2);
        cache.print();

        System.out.println("get (key=4) from the cache:");
        cache.get(4);
        cache.print();

        System.out.println("put (key=4, value=D) into the cache:");
        cache.put(4, 'D');
        cache.print();

        System.out.println("put (key=3, value=E) into the cache:");
        cache.put(3, 'E');
        cache.print();

        System.out.println("get (key=4) from the cache:");
        cache.get(4);
        cache.print();

        System.out.println("put (key=1, value=A) into the cache:");
        cache.put(1, 'A');
        cache.print();

    }
}

