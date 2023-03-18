package MVC.Business;

import MVC.GUI.QueueFrame;
import MVC.Model.Server;
import MVC.Model.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class SimulationManager implements Runnable
{
    private Scheduler scheduler;
    private QueueFrame frame;
    private ArrayList<Task> tasks;
    private SelectionPolicy selectionPolicy;
    private Strategy strategy;

    private final int timeLimit;
    private final int minArrivalTime;
    private final int maxArrivalTime;
    private final int maxProcessingTime;
    private final int minProcessingTime;
    private final int numberOfServers;
    private final int numberOfClients;
    private int currentTime;
    private int peakClients = 0;
    private int peakTime = 0;
    private float avgTime = 0;
    private LogFile log;

    // Schimbam constructor ul ca sa nu aiba asa multi parametri cand facem gui
    public SimulationManager(int timeLimit, int minArrivalTime,
                             int maxArrivalTime, int minProcessingTime,
                             int maxProcessingTime, int numberOfClients,
                             int numberOfServers, SelectionPolicy policy)
    {
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.log = new LogFile("queue_log.txt");
        this.log.write("", false);

        this.strategy = (policy == SelectionPolicy.TIME)?(new TimeStrategy()):(new ShortestQueueStrategy());
        this.scheduler = new Scheduler(this.numberOfServers, this.strategy);
        this.frame = new QueueFrame("Simulation");
        this.frame.setVisible(true);
        this.tasks =  new ArrayList<Task>();

        this.generateRandomTasks();

        for (Thread t : this.scheduler.getThreads())
            t.start();

    }

    public void generateRandomTasks()
    {
        for (int i=1; i<=numberOfClients; i++)
        {
            int r1 = ThreadLocalRandom.current().nextInt(minArrivalTime, maxArrivalTime +1);
            int r2 = ThreadLocalRandom.current().nextInt(minProcessingTime, maxProcessingTime + 1);
            tasks.add(new Task(i, r1, r2));
        }

        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
    }

    public String info()
    {
        String ret = "";
        for (Task t : tasks)
            ret += t.toString() + " ";

        return ret;
    }

    public void updateLog()
    {

        log.write("Time: " + currentTime, true);
        log.write("Unassigned clients: " + info(), true);
        for (int i = 0; i < scheduler.getServers().size(); i++)
            log.write("Q" + i + ": " + scheduler.getServers().get(i).info(), true);
        log.write("\n\n", true);
    }

    public void updateFrame()
    {
        this.frame.getWTextField().setText(this.info());

        for (int i=0; i<numberOfServers; i++)
            this.frame.getQueues()[i].setText(scheduler.getServers().get(i).info());

        this.frame.getTimeLabel().setText(String.valueOf(currentTime));
    }

    public boolean checkEnd()
    {
        if (currentTime >= timeLimit)
            return true;

        int ok = 1;

        for (Server s : this.scheduler.getServers())
            if (!s.getTasks().isEmpty()) {
                ok = 0;
                break;
            }

        return tasks.isEmpty() && ok == 1;

    }

    public void updatePeak() {
        int sum = 0;
        for (Server s : this.scheduler.getServers())
            sum += s.getTasks().size();

        if (sum > peakClients) {
            peakClients = sum;
            peakTime = currentTime;
        }
    }

    public float computeServiceTime()
    {
        float ret = 0;

        for (Task t : tasks)
            ret += t.getServiceTime();
        return ret/tasks.size();
    }

    @Override
    public synchronized void run()
    {
        float wait = computeServiceTime();
        do {
            ArrayList<Task> aux = new ArrayList<Task>();
            for (Task t : tasks) {
                if (t.getArrivalTime() == currentTime) {
                    avgTime += scheduler.dispatchTask(t);
                    aux.add(t);
                }
            }
            tasks.removeAll(aux);
            this.updatePeak();
            this.updateLog();
            this.updateFrame();
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!checkEnd());
        this.updateFrame();
        String s = "Simulation ended!\nPeak of " + peakClients + " clients at hour " + peakTime + "\nAverage service time: " + wait + "\nAverage wait time: " + avgTime/numberOfClients;
        JOptionPane.showMessageDialog(this.frame, s);
        log.write(s, true);
    }

    public ArrayList<Task> getTasks() { return this.tasks; }
}
