import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// Task class to represent individual tasks
class Task {
    private String name;
    private Date dueDate;
    private int priority;

    public Task(String name, Date dueDate, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }
}

// TaskScheduler class to manage tasks and perform operations
public class TaskScheduler {
    private List<Task> tasks;

    public TaskScheduler() {
        tasks = new ArrayList<>();
    }

    public void addTask(String name, Date dueDate, int priority) {
        Task newTask = new Task(name, dueDate, priority);
        tasks.add(newTask);
        System.out.println("Task added: " + newTask.getName());
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                System.out.println("Name: " + task.getName() +
                                   ", Due Date: " + task.getDueDate() +
                                   ", Priority: " + task.getPriority());
            }
        }
    }

    public void notifyTaskDueSoon(int daysBeforeDue) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysBeforeDue);
        Date dueSoonDate = calendar.getTime();

        for (Task task : tasks) {
            if (task.getDueDate().before(dueSoonDate)) {
                System.out.println("Task due soon: " + task.getName() +
                                   ", Due Date: " + task.getDueDate());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Notify Tasks Due Soon");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter due date (yyyy-MM-dd): ");
                    String dueDateString = scanner.nextLine();
                    Date dueDate = null;
                    try {
                        dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                        continue;
                    }
                    System.out.print("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scheduler.addTask(name, dueDate, priority);
                    break;
                case 2:
                    scheduler.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter number of days before due for notification: ");
                    int daysBeforeDue = scanner.nextInt();
                    scheduler.notifyTaskDueSoon(daysBeforeDue);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }

        scanner.close(); // Close the Scanner object
    }
}