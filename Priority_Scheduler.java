//import java.util.*;
//
//class PriorityScheduler {
//    private ArrayList<Process> processes;
//    private int contextSwitching;
//    private int agingThreshold;
//    private Vector<String> executionOrder;
//
//    public PriorityScheduler(ArrayList<Process> processes,
//                             int contextSwitching,
//                             int agingThreshold) {
//
//        this.processes = new ArrayList<>();
//        for (Process p : processes) {
//            this.processes.add(new Process(
//                    p.getName(),
//                    p.getArrivalTime(),
//                    p.getBurstTime(),
//                    p.getPriority(),
//                    p.getQuantumTime()
//            ));
//        }
//
//        this.contextSwitching = contextSwitching;
//        this.agingThreshold = agingThreshold;
//        this.executionOrder = new Vector<>();
//    }
//
//    public void simulate() {
//
//        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
//
//        ArrayList<Process> readyQueue = new ArrayList<>();
//        ArrayList<Process> completed = new ArrayList<>();
//        Map<Process, Integer> waitingTracker = new HashMap<>();
//
//        int currentTime = 0;
//        int index = 0;
//        Process running = null;
//
//        for (Process p : processes) {
//            waitingTracker.put(p, 0);
//        }
//
//        while (completed.size() < processes.size()) {
//
//            // add arrived processes
//            while (index < processes.size()
//                    && processes.get(index).getArrivalTime() <= currentTime) {
//                readyQueue.add(processes.get(index));
//                index++;
//            }
//
//            // apply aging
//            applyAging(readyQueue, waitingTracker);
//
//            if (readyQueue.isEmpty()) {
//                currentTime++;
//                continue;
//            }
//
//            Process selected = selectHighestPriority(readyQueue);
//
//            if (running != null && running != selected) {
//                currentTime += contextSwitching;
//            }
//
//            running = selected;
//            executionOrder.add(selected.getName());
//
//            // execute 1 time unit
//            selected.setRemainingTime(selected.getRemainingTime() - 1);
//            currentTime++;
//
//            // update waiting time
//            for (Process p : readyQueue) {
//                if (p != selected) {
//                    waitingTracker.put(p, waitingTracker.get(p) + 1);
//                }
//            }
//
//            // check new arrivals during execution
//            while (index < processes.size()
//                    && processes.get(index).getArrivalTime() <= currentTime) {
//                readyQueue.add(processes.get(index));
//                index++;
//            }
//
//            // if process finished
//            if (selected.getRemainingTime() == 0) {
//                readyQueue.remove(selected);
//                completed.add(selected);
//
//                int turnaround = currentTime - selected.getArrivalTime();
//                int waiting = turnaround - selected.getBurstTime();
//
//                selected.setTurnaroundTime(turnaround);
//                selected.setWaitingTime(waiting);
//
//                running = null;
//            }
//        }
//
//        displayResults(completed);
//    }
//
//
//    private void applyAging(ArrayList<Process> readyQueue,
//                            Map<Process, Integer> waitingTracker) {
//
//        for (Process p : readyQueue) {
//            int waited = waitingTracker.get(p);
//
//            if (waited >= agingThreshold && p.getPriority() > 1) {
//                p.setPriority(p.getPriority() - 1); // تحسين الأولوية
//                waitingTracker.put(p, 0);
//            }
//        }
//    }
//
//    private Process selectHighestPriority(ArrayList<Process> readyQueue) {
//
//        Process best = readyQueue.get(0);
//
//        for (Process p : readyQueue) {
//            if (p.getPriority() < best.getPriority()) {
//                best = p;
//            } else if (p.getPriority() == best.getPriority()
//                    && p.getArrivalTime() < best.getArrivalTime()) {
//                best = p;
//            }
//        }
//
//        return best;
//    }
//
//    private void displayResults(ArrayList<Process> completed) {
//
//        System.out.println("\n=== Priority Scheduling (Preemptive + Aging) ===");
//        System.out.print("Execution Order: ");
//        executionOrder.forEach(p -> System.out.print(p + " "));
//        System.out.println("\n");
//
//        double totalWT = 0, totalTAT = 0;
//
//        for (Process p : completed) {
//            System.out.println("Process " + p.getName()
//                    + " | Waiting: " + p.getWaitingTime()
//                    + " | Turnaround: " + p.getTurnaroundTime());
//
//            totalWT += p.getWaitingTime();
//            totalTAT += p.getTurnaroundTime();
//        }
//
//        System.out.printf("\nAverage Waiting Time: %.2f\n",
//                totalWT / completed.size());
//        System.out.printf("Average Turnaround Time: %.2f\n",
//                totalTAT / completed.size());
//    }
//
//    // test
//    public static void main(String[] args) {
//
//        ArrayList<Process> processes = new ArrayList<>();
//
//        processes.add(new Process("P1", 0, 8, 3, 0));
//        processes.add(new Process("P2", 1, 4, 1, 0));
//        processes.add(new Process("P3", 2, 2, 4, 0));
//        processes.add(new Process("P4", 3, 1, 2, 0));
//        processes.add(new Process("P5", 4, 3, 5, 0));
//
//        PriorityScheduler scheduler =
//                new PriorityScheduler(processes, 1, 5);
//
//        scheduler.simulate();
//    }
//}
