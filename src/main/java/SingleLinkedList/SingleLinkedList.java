package SingleLinkedList;

import java.util.HashSet;

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

    public Node findMiddle() {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        };
        return false;
    }

    public Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public Node partitionList(int pivot) {
        Node lessThanList = new Node(0);
        Node moreThanList = new Node(0);

        Node lessThanHead = lessThanList;
        Node moreThanHead = moreThanList;
        Node curr = head;

        while(curr != null) {
            if (curr.value < pivot) {
                lessThanList.next = curr;
                lessThanList = lessThanList.next;
            } else {
                moreThanList.next = curr;
                moreThanList = moreThanList.next;
            }
            curr = curr.next;

            lessThanHead = lessThanHead.next;
            moreThanHead = moreThanHead.next;
            lessThanHead.next = moreThanHead;
            moreThanHead.next = null;
            head = lessThanHead;
        }
        return head;

    }

    // write remove duplicates method, but duplicates are not necessarily neighbors, they are not sorted;
    public void removeDuplicates() {
        HashSet<Integer> values = new HashSet<>();
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (values.contains(current.value)) {
                prev.next = current.next;
                length -= 1;
            } else {
                values.add(current.value);
                prev = current;
            }
            current = current.next;
        }

    }

    public int binaryToDecimal() {
        Node curr = head;
        int i = length - 1;
        int decimal = 0;
        while (curr!= null) {
            decimal += Math.pow(2, i) * curr.value;
            curr = curr.next;
            i--;
        }
        return decimal;
    }

    public void reverseBetween(int m, int n) {
        if(this.head == null || m > n || m < 1 || n > this.length) return;
        Node dummy = new Node(0);
        dummy.next = this.head;
        Node pre = dummy;

        for(int i = 1; i < m; i++) {
            pre = pre.next;
        }

        Node current = pre.next;

        for(int i = m; i < n; i++) {
            Node next = current.next;
            current.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        this.head = dummy.next;
    }


}
