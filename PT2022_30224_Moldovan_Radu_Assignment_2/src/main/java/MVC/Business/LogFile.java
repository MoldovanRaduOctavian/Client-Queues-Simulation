package MVC.Business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFile
{
    private String path;
    private FileWriter fw;
    private PrintWriter p;

    public LogFile(String path) { this.path = path; }

    public void write(String s, boolean app)
    {
        try {
            fw = new FileWriter(path, app);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p = new PrintWriter(fw);

        p.println(s);
        p.close();
    }
}
