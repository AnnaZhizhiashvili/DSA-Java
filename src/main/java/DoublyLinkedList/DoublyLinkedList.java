package DoublyLinkedList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    DoublyLinkedList(int value) {
        head = new Node(value);
        tail = head;
        length++;
    }

    public DoublyLinkedList push(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        };
        this.length++;
        return this;
    }

    public Node pop() {
        if(this.length == 0) {
            throw new IllegalStateException("List is empty");
        }
        Node temp = this.tail;
        if (this.length == 1) {
            this.head = temp;
            this.tail = temp;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
            temp.prev = null;
        };
        this.length--;
        return temp;
    }


    public DoublyLinkedList unshift(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.length++;
        return this;
    }

    public Node shift() {
        Node temp = this.head;
        if (this.length == 0) {
            throw new IllegalStateException("List is empty");
        } else {
            this.head = this.head.next;
            this.head.prev = null;
            temp.next = null;
        }
        this.length++;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        };
        Node temp = this.head;
        if (index < this.length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = this.tail;
            for (int i = this.length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;

    }

    public boolean set(int index, int value) {
        Node temp = this.get(index);
        if (temp!= null) {
            temp.value = value;
            return true;
        } else {
            return false;
        }
    }

    public DoublyLinkedList insert(int index, int value) {
        if (index == 0) return this.unshift(value);
        if (index == this.length - 1) return this.push(value);
        if(index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

        Node before = this.get(index - 1);
        Node after = before.next;
        Node newNode = new Node(value);

        before.next = newNode;
        after.prev = newNode;
        newNode.next = after;
        newNode.prev = before;
        this.length++;
        return this;
    }

    public Node remove(int index) {
        if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();
        if (index == 0) return this.shift();
        if (index == this.length - 1) return this.pop();

        Node temp = this.get(index);
        temp.next.prev = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        this.length--;
        return temp;
    }

}
