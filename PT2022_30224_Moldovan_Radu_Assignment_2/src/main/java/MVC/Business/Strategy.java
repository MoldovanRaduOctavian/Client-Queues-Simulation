package MVC.Business;

import MVC.Model.Server;
import MVC.Model.Task;

import java.util.ArrayList;

public interface Strategy
{
    int addTask(ArrayList<Server> servers, Task task);
}
