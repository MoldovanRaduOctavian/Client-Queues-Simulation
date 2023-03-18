package MVC.Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server()
    {
        tasks = new ArrayBlockingQueue<Task>(2048);
        waitingPeriod = new AtomicInteger(0);
    }


    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getServiceTime());
    }

    public String info()
    {
        String ret = "";

        for (Task t : this.tasks)
            ret += (t.toString() + " ");

        return ret;
    }

    @Override
    public synchronized void run()
    {
        while (true)
        {
            if (tasks.peek() != null)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (tasks.peek().getServiceTime() > 1)
                {
                    waitingPeriod.getAndDecrement();
                    tasks.peek().setServiceTime(tasks.peek().getServiceTime() - 1);

                }

                else
                    tasks.remove();
            }
        }
    }
}
