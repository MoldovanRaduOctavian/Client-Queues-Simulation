package MVC.Business;

import MVC.Model.Server;
import MVC.Model.Task;

import java.util.ArrayList;

public class Scheduler
{
    private ArrayList<Server> servers;
    private ArrayList<Thread> threads;
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers, Strategy strategy)
    {
        this.servers = new ArrayList<Server>();
        this.threads = new ArrayList<Thread>();
        this.maxNoServers = maxNoServers;
        this.strategy = strategy;

        for (int i=0; i<this.maxNoServers; i++)
        {
            Server s = new Server();
            servers.add(s);
            threads.add(new Thread(s));
        }
    }

    public int dispatchTask(Task t) { return this.strategy.addTask(servers, t); }

    public ArrayList<Server> getServers() { return this.servers; }
    public ArrayList<Thread> getThreads() { return this.threads; }
}
