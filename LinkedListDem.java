import java.util.Scanner;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    /**
     * Insert a node at a specified position in the linked list.
     * @param data the data to be inserted
     * @param pos the position at which to insert the new node
     */
    public void insertAtPos(int data, int pos) {
        Node newNode = new Node(data);
        if (pos <= 0) {
            System.out.println("Invalid position. Position should be greater than 0.");
            return;
        }
        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        int count = 1;
        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Invalid position. Position exceeds the length of the list.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    /**
     * Delete a node at a specified position in the linked list.
     * @param pos the position of the node to be deleted
     */
    public void deleteAtPosition(int pos) {
        if (pos <= 0 || head == null) {
            System.out.println("Invalid position or empty list.");
            return;
        }
        if (pos == 1) {
            head = head.next;
            return;
        }
        Node current = head;
        int count = 1;
        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }
        if (current == null || current.next == null) {
            System.out.println("Invalid position.");
            return;
        }
        current.next = current.next.next;
    }

    /**
     * Delete the node that occurs after a given node in the linked list.
     * @param prevNode the node after which the next node will be deleted
     */
    public void deleteAfterNode(Node prevNode) {
        if (prevNode == null || prevNode.next == null) {
            System.out.println("Previous node is null or the last node.");
            return;
        }
        prevNode.next = prevNode.next.next;
    }

    /**
     * Search for a node with a specific value in the linked list.
     * @param key the value to search for
     * @return true if the value is found, false otherwise
     */
    public boolean searchNode(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Display the linked list.
     */
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

class Stack {
    private LinkedList list;

    public Stack() {
        this.list = new LinkedList();
    }

    /**
     * Push a new value onto the stack.
     * @param data the value to be pushed
     */
    public void push(int data) {
        list.insertAtPos(data, 1); // Pushing at the beginning of the list
    }

    /**
     * Pop the top value from the stack.
     * @return the value popped from the stack
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return Integer.MIN_VALUE; // indicating stack underflow
        }
        int popped = list.head.data;
        list.deleteAtPosition(1); // Popping the first element
        return popped;
    }

    /**
     * Peek at the top value of the stack.
     * @return the top value of the stack
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return Integer.MIN_VALUE; // indicating stack underflow
        }
        return list.head.data;
    }

    /**
     * Check if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.head == null;
    }
}

public class LinkedListDem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList list = new LinkedList();
        Stack stack = new Stack();
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1: Insert in LinkedList");
            System.out.println("2: Delete from LinkedList");
            System.out.println("3: Delete after a node in LinkedList");
            System.out.println("4: Search in LinkedList");
            System.out.println("5: Display LinkedList");
            System.out.println("6: Push to Stack");
            System.out.println("7: Pop from Stack");
            System.out.println("8: Peek Stack");
            System.out.println("9: Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter data to insert:");
                    int data = scanner.nextInt();
                    System.out.println("Enter position to insert at:");
                    int position = scanner.nextInt();
                    list.insertAtPos(data, position);
                    break;
                case 2:
                    System.out.println("Enter position to delete from:");
                    int delPosition = scanner.nextInt();
                    list.deleteAtPosition(delPosition);
                    break;
                case 3:
                    System.out.println("Enter the data of the node after which deletion will occur:");
                    int nodeData = scanner.nextInt();
                    Node current = list.head;
                    while (current != null && current.data != nodeData) {
                        current = current.next;
                    }
                    if (current != null) {
                        list.deleteAfterNode(current);
                    } else {
                        System.out.println("Node with the given data not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter value to search for:");
                    int searchValue = scanner.nextInt();
                    boolean found = list.searchNode(searchValue);
                    System.out.println("Search result: " + (found ? "Found" : "Not Found"));
                    break;
                case 5:
                    list.display();
                    break;
                case 6:
                    System.out.println("Enter data to push to stack:");
                    int stackData = scanner.nextInt();
                    stack.push(stackData);
                    break;
                case 7:
                    int poppedValue = stack.pop();
                    if (poppedValue != Integer.MIN_VALUE) {
                        System.out.println("Popped value: " + poppedValue);
                    }
                    break;
                case 8:
                    int peekValue = stack.peek();
                    if (peekValue != Integer.MIN_VALUE) {
                        System.out.println("Top of stack: " + peekValue);
                    }
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }

        scanner.close();
    }
}
