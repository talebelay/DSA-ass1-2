import java.util.Scanner;

class Tasks {
    private final String title;
    private final String description;
    private boolean completed;

    public Tasks(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }
}

class Nodes {
    private final Tasks task;
    private Nodes next;

    public Nodes(Tasks task) {
        this.task = task;
        this.next = null;
    }

    public Tasks getTask() {
        return task;
    }

    public Nodes getNext() {
        return next;
    }

    public void setNext(Nodes next) {
        this.next = next;
    }
}

class TodoList {
    private Nodes head;

    public TodoList() {
        this.head = null;
    }

    public void addToDo(Tasks task) {
        Nodes newNode = new Nodes(task);
        if (head == null) {
            head = newNode;
        } else {
            Nodes current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void markToDoAsCompleted(String title) {
        Nodes current = head;
        while (current != null) {
            if (current.getTask().getTitle().equals(title)) {
                current.getTask().markCompleted();
                System.out.println("Task '" + title + "' marked as completed.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Task '" + title + "' not found.");
    }

    public void viewToDoList() {
        Nodes current = head;
        if (current == null) {
            System.out.println("ToDo List is empty.");
        } else {
            System.out.println("ToDo List:");
            while (current != null) {
                Tasks task = current.getTask();
                System.out.println("- Title: " + task.getTitle());
                System.out.println("  Description: " + task.getDescription());
                System.out.println("  Completed: " + (task.isCompleted() ? "Yes" : "No"));
                System.out.println();
                current = current.getNext();
            }
        }
    }
}

public class ToDoLIstDem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList toDoList = new TodoList();

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a new task");
            System.out.println("2. Mark a task as completed");
            System.out.println("3. View ToDo List");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    toDoList.addToDo(new Tasks(title, description));
                    System.out.println("Task added.");
                    break;
                case 2:
                    System.out.print("Enter title of the task to mark as completed: ");
                    String taskTitle = scanner.nextLine();
                    toDoList.markToDoAsCompleted(taskTitle);
                    break;
                case 3:
                    toDoList.viewToDoList();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        scanner.close();
    }
}

