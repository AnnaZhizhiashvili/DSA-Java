package SingleLinkedList;

public class SingleLinkedList {
    Node head;
    Node tail;
    int length = 0;

    public void push(int value) {
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        this.length++;
    }

    public Node pop() {
        if(head == null) {
            throw new IllegalStateException("List is empty");
        };
        Node prev = head;
        Node temp = head;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        tail = prev;
        prev.next = null;
        length--;
        if(length == 0) {
            tail = null;
            head = null;
        }
        return temp;


    }


    public void printList() {
        Node current = head;
        System.out.print("List: ");
        while(current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void unshift(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            tail = newNode;
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;

    }

    public Node shift() {
        if(head == null) {
            throw new IllegalStateException("List is empty");
        };
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            this.tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        };
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if(temp!= null) {
            temp.value = value;
            return true;
        } else {
            return false;
        }

    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if(index == 0) {
            unshift(value);
            return true;
        }
        if (index == length) {
            push(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);

        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if(index == 0) {
            return shift();
        }
        if(index == length - 1) {
            return pop();

        }
        Node prev = get(index - 1);
        Node temp = prev.next;
        prev.next = temp.next;
        length--;
        return temp;
    }

    public SingleLinkedList reverse() {
        Node temp = head;
        head = tail;
        tail = temp;

        Node prev = null;
        Node next = temp.next;

        while(next!= null) {
            temp.next = prev;
            prev = temp;
            temp = next;
            next = next.next;
        }
        return this;
    }


}
