package MVC.Business;

import MVC.Model.Server;
import MVC.Model.Task;

import java.util.ArrayList;

public class TimeStrategy implements Strategy
{

    @Override
    public int addTask(ArrayList<Server> servers, Task task)
    {
        int min = 0x7fffffff;
        int ret = 0;
        Server smin = null;

        for (Server s : servers)
            if (s.getWaitingPeriod().get() < min)
            {
                min = s.getWaitingPeriod().get();
                smin = s;
            }

        ret = smin.getWaitingPeriod().get();
        assert smin != null;
        smin.addTask(task);

        return ret;

    }
}
