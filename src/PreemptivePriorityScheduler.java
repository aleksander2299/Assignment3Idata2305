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
        ProcessesInitializer();
        SimulateScheduler();

    private class ProcessesInitializer {
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
         * Will later set up class which will have a fake timer to simulate the scheduler. and go trough each of the processes in the arraylist.
         * This will then be used to calculate the waiting time, turnaround time, and completion time for each process.
         * Since the for loop will go trough each based on its values and decrease burst time
         */
    private class SimulateScheduler {
        int currentTime = 0;
        int totalCompletionTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalProcesses = processes.size();
        int lowestPriorityId = null;

        while (!processes.isEmpty()){
            /**
             * Will go trough a for loop to find the lowest priority process that has arrived.
             */
            for (i = 0; i < totalProcesses; i++) {
                Process process = processes.get(i);
                if ((process.priority < processes.get(lowestPriorityId).priority) && (process.arrivalTime >= currentTime)) {
                    lowestPriorityId = i;
                }
            }

            if (lowestPriorityId != null) {
                Process process = processes.get(lowestPriorityId);
                process.completionTime =(currentTime + process.burstTime);
                process.turnaroundTime = (process.completionTime - process.arrivalTime);
                process.waitingTime = (process.turnaroundTime - process.burstTime);
                currentTime += 1; //process.getCompletionTime();
                process.remainingTime -= 1;

                if (process.remainingTime == 0) {
                    totalCompletionTime += process.completionTime;
                    totalWaitingTime += process.waitingTime;
                    System.out.println("Process " + processes.ProcessID + " has completed. With a waiting time of " + process.waitingTime + " and a turnaround time of " + process.turnaroundTime);
                    processes.remove(lowestPriorityId);
                }

            } else {
                currentTime += 1;
            }

        }
        System.out.println("The total completion time is: " + totalCompletionTime);
        System.out.println("The average waiting time is: " + totalWaitingTime/totalProcesses);
    }

    }
}
