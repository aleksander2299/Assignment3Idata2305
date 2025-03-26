import java.util.ArrayList;
import java.util.Comparator;

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


    public int getTotalCompletionTime() {
        return totalCompletionTime;
    }

    public void setTotalCompletionTime(int totalCompletionTime) {
        this.totalCompletionTime = totalCompletionTime;
    }

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



    public void printProcessTime(){
        for(Process process : processes) {
            System.out.println("pid " + process.getProcessID() + ", arrival time" + process.getArrivalTime() + ", burst time " + process.getBurstTime() + ", turnaroundtime " + process.getTurnaroundTime() + ", waiting time :" + process.getWaitingTime());
        }
    }


    public int getTurnaroundTime(Process process){
        int turnAroundTime = process.getTurnaroundTime();
        return turnAroundTime;
    }

    public int getAvgTurnaroundTime(){
        int average = 0;
        for(Process process: processes){
            average += getTurnaroundTime(process);
        }
        return average/ processes.size();
    }

    public int calculateWaitingTime(Process process){
        int waitingTime = process.getTurnaroundTime() - process.getBurstTime();
        return waitingTime;
    }


    public int calculateAvgWaitingTime(){
        int average = 0;
        for(Process process: processes){
           average += calculateWaitingTime(process);
        }
        return average/ processes.size();
    }




    public void addProcesses(){
        processes.add(new Process(1, 0, 5, 3));
        processes.add(new Process(2, 1, 3, 1));
        processes.add(new Process(3, 2, 8, 4));
        processes.add(new Process(4, 3, 6, 2));

    }


}
