import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will simulate a preemptive priority scheduler.
 */
public class PreemptivePriorityScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
         * Set to true if the user wants to provide input.
         */
        boolean userInput = false;

        ArrayList<Process> processes = new ArrayList<>();

        if (userInput) {
            int Processes = scanner.nextInt();
            for (int i = 0; i < Processes; i++) {
                System.out.println("Enter the arrival time for process " + (i + 1) + ": ");
                int arrivalTime = scanner.nextInt();
                System.out.println("Enter the burst time for process " + (i + 1) + ": ");
                int burstTime = scanner.nextInt();
                System.out.println("Enter the priority for process " + (i + 1) + ": ");
                int priority = scanner.nextInt();
                processes.add(new Process(i + 1, arrivalTime, burstTime, priority));
            }
        } else {
            processes.add(new Process(1, 0, 5, 3));
            processes.add(new Process(2, 1, 3, 1));
            processes.add(new Process(3, 2, 8, 4));
            processes.add(new Process(4, 3, 6, 2));
        }
        /**
         * Will later set up class which will have a fake timer to simulate the scheduler. and go trough each of the processes in the arraylist.
         * This will then be used to calculate the waiting time, turnaround time, and completion time for each process.
         * Since the for loop will go trough each based on its values and decrease burst time
         */
    }
}
