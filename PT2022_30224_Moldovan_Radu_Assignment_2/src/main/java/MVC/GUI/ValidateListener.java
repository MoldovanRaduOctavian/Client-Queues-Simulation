package MVC.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidateListener implements ActionListener
{

    private SimulationFrame frame;

    public ValidateListener(SimulationFrame frame) { this.frame = frame; }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int time = 0, server = 0, client = 0, minArrival = 0, maxArrival = 0, minService = 0, maxService = 0;
        try {
            time = Integer.parseInt(frame.getTimeTextField().getText());
            server = Integer.parseInt(frame.getServerTextField().getText());
            client = Integer.parseInt(frame.getClientTextField().getText());
            minArrival = Integer.parseInt(frame.getMinArrivalTextField().getText());
            maxArrival = Integer.parseInt(frame.getMaxArrivalTextField().getText());
            minService = Integer.parseInt(frame.getMinServiceTextField().getText());
            maxService = Integer.parseInt(frame.getMaxServiceTextField().getText());
        }
        catch (Exception ex)
        {
            frame.getOkLabel().setText("INVALID INPUT!");
            return;
        }

        if (time <=0 || server <= 0 || client <=0 || minArrival <=0 || maxArrival <= 0 || minService <= 0 || maxService <=0
                || minArrival > maxArrival || minService > maxService || server > 5)
        {
            frame.getOkLabel().setText("INVALID INPUT!");
            return;
        }

        frame.getOkLabel().setText("Correct input");
    }
}
