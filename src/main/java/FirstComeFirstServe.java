import java.util.ArrayList;
import java.util.Comparator;

/**
 * class representing first come first serve algorithm
 */
public class FirstComeFirstServe {



    static ArrayList<Process> processes = new ArrayList<>();


    private int totalCompletionTime;

    public static void main(String[] args) {

        FirstComeFirstServe fcfs = new FirstComeFirstServe();
        fcfs.addProcesses();
        fcfs.runFCFS();
        fcfs.printProcessTime();
        System.out.println("avg turnatourn time : " + fcfs.getAvgTurnaroundTime());
        System.out.println("avg waiting time : " + fcfs.calculateAvgWaitingTime());
        System.out.println("total completion time : " + fcfs.getTotalCompletionTime());




    }


    /**
     * gets total completion time
     */
    public int getTotalCompletionTime() {
        return totalCompletionTime;
    }

    /**
     * sets total completion time to new completion time
     * @param totalCompletionTime
     */
    public void setTotalCompletionTime(int totalCompletionTime) {
        this.totalCompletionTime = totalCompletionTime;
    }

    /**
     * runs the first come firs serve algorithm
     */
    public void runFCFS() {

        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        int currentTime = 0;

        for (Process process : processes) {
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }


            process.setCompletionTime(currentTime + process.getBurstTime());


            process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());


            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());


            currentTime = process.getCompletionTime();
        }
        setTotalCompletionTime(currentTime);
    }


    /**
     * prints the time for the processes
     */
    public void printProcessTime(){
        for(Process process : processes) {
            System.out.println("pid " + process.getProcessID() + ", arrival time" + process.getArrivalTime() + ", burst time " + process.getBurstTime() + ", turnaroundtime " + process.getTurnaroundTime() + ", waiting time :" + process.getWaitingTime());
        }
    }


    /**
     * returns the tournaroundtime for a process
     * @param process the process to get turnaroundtime from
     * @return int the tournaroundtime
     */
    public int getTurnaroundTime(Process process){
        int turnAroundTime = process.getTurnaroundTime();
        return turnAroundTime;
    }

    /**
     * gets the average turnaroundtime from the processes.
     * @return int the average turnaroundtime
     */
    public int getAvgTurnaroundTime(){
        int average = 0;
        for(Process process: processes){
            average += getTurnaroundTime(process);
        }
        return average/ processes.size();
    }

    /**
     * returns the waiting time of a process-
     * @param process
     * @return
     */
    public int calculateWaitingTime(Process process){
        int waitingTime = process.getTurnaroundTime() - process.getBurstTime();
        return waitingTime;
    }


    /**
     * calculate average waiting time
     * @return the average waiting time
     */
    public int calculateAvgWaitingTime(){
        int average = 0;
        for(Process process: processes){
           average += calculateWaitingTime(process);
        }
        return average/ processes.size();
    }


    /**
     * add processes to process array.
     * nb priority doesnt matter here.
     */
    public void addProcesses(){
        processes.add(new Process(1, 0, 5, 3));
        processes.add(new Process(2, 1, 3, 1));
        processes.add(new Process(3, 2, 8, 4));
        processes.add(new Process(4, 3, 6, 2));

    }


}
