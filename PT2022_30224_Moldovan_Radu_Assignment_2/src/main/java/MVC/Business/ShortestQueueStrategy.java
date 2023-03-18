package MVC.Business;

import MVC.Model.Server;
import MVC.Model.Task;

import java.util.ArrayList;

public class ShortestQueueStrategy implements Strategy
{

    @Override
    public int addTask(ArrayList<Server> servers, Task task)
    {
        int min = 0x7fffffff;
        int ret = 0;
        Server aux = null;

        for (Server s : servers)
            if (s.getTasks().size() < min)
            {
                min = s.getTasks().size();
                aux = s;
            }

        ret = aux.getWaitingPeriod().get();
        aux.addTask(task);

        return ret;
    }
}
