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

        initializeProcesses(processes, scanner, userInput);
        simulateScheduler(processes);

        scanner.close();
    }

    /**
     * This method will initialize the processes.
     * @param processes the processes that will be initialized.
     * @param scanner the scanner that will be used to get user input.
     * @param userInput a boolean that will determine if the user wants to provide input.
     */
    private static void initializeProcesses(ArrayList<Process> processes, Scanner scanner, boolean userInput) {
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
        }
        else {
            processes.add(new Process(1, 0, 5, 3));
            processes.add(new Process(2, 1, 3, 1));
            processes.add(new Process(3, 2, 8, 4));
            processes.add(new Process(4, 3, 6, 2));
        }
    }

        /**
         * This method will simulate the scheduler.
         * @param processes the processes that will be scheduled.
         */
    private static void simulateScheduler(ArrayList<Process> processes) {
        int currentTime = 0;
        int totalCompletionTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalProcesses = processes.size();
        int lowestPriorityId;

        while (!processes.isEmpty()){
            lowestPriorityId = -1;
            /**
             * Will go trough a for loop to find the lowest priority process that has arrived.
             */
            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);
                if (process.arrivalTime <= currentTime) {
                    if (lowestPriorityId == -1 || process.priority < processes.get(lowestPriorityId).priority) {
                        lowestPriorityId = i;
                    }
                }
            }

            /**
             * If there is a process that has arrived, then the scheduler will run the process.
             */
            if (lowestPriorityId != -1) {
                Process process = processes.get(lowestPriorityId);
                currentTime += 1;
                process.remainingTime -= 1;
                /**
                 * If the process has completed, then the completion time, waiting time, and turnaround time will be calculated.
                 */
                if (process.remainingTime == 0) {
                    process.completionTime = currentTime;
                    process.turnaroundTime = process.completionTime - process.arrivalTime;
                    process.waitingTime = process.turnaroundTime - process.burstTime;
                    totalWaitingTime += process.waitingTime;
                    System.out.println("Process " + process.processID + " has completed. With a waiting time of " + process.waitingTime + " and a turnaround time of " + process.turnaroundTime);
                    processes.remove(lowestPriorityId);
                    if (processes.isEmpty()) {
                        totalCompletionTime = process.completionTime;
                    }
                }
            }
            else {
                currentTime += 1;
            }

        }
        System.out.println("The total completion time is: " + totalCompletionTime);
        System.out.println("The average waiting time is: " + (double)totalWaitingTime/totalProcesses);
    }
}

