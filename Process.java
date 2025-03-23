import java.util.*;

/**
 * This class will store the information of a process.
 * This will include the process ID, arrival time, burst time, remaining time, priority, completion time, waiting time, and turnaround time.
 */
class Process {
    int processID;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int priority;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int processID, int arrivalTime, int burstTime, int priority) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}