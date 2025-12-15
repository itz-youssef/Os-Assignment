public class Main {
    private class Process {
        private String id;
        private int burstTime;
        private int arrivalTime;
        private int priority;
        public Process(String id, int burstTime, int arrivalTime, int quantum) {
            this.id = id;
            this.burstTime = burstTime;
            this.arrivalTime = arrivalTime;
            this.priority = quantum;
        }
        public String getId() {return id;}
        public int getBurstTime() {return burstTime;}
        public int getArrivalTime() {return arrivalTime;}
        public int getPriority() {return priority;}
        public void setBurstTime(int burstTime) {this.burstTime = burstTime;}
        public void setArrivalTime(int arrivalTime) {this.arrivalTime = arrivalTime;}
        public void setPriority(int priority) {this.priority = priority;}
    }
}
