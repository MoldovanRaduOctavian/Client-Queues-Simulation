package MVC.GUI;

import javax.swing.*;
import java.awt.*;

public class QueueFrame extends JFrame
{
    private JPanel contentPane;

    private JPanel qPanel;
    private JLabel wLabel;
    private JTextField wTextField;
    private JLabel q1Label;
    private JLabel q2Label;
    private JLabel q3Label;
    private JLabel q4Label;
    private JLabel q5Label;
    private JTextField[] queues;
    private JLabel simLabel;
    private JLabel timeLabel;

    public QueueFrame(String s)
    {
        super(s);
        this.prepareGui();
    }

    public void prepareGui()
    {
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane = new JPanel(new GridLayout(1, 1));
        this.prepareQPanel();
        this.setContentPane(this.contentPane);
    }

    private void prepareQPanel()
    {
        this.qPanel = new JPanel(new GridLayout(7, 2));

        this.wLabel = new JLabel("Unassigned clients: ", JLabel.CENTER);
        this.qPanel.add(this.wLabel);

        this.wTextField = new JTextField();
        this.wTextField.setEditable(false);
        this.qPanel.add(this.wTextField);

        this.queues = new JTextField[5];
        for (int i=0; i<5; i++)
        {
            this.queues[i] = new JTextField();
            this.queues[i].setText("Unused");
            this.queues[i].setEditable(false);
        }

        this.q1Label = new JLabel("Queue 1: ", JLabel.CENTER);
        this.qPanel.add(this.q1Label);
        this.qPanel.add(this.queues[0]);


        this.q2Label = new JLabel("Queue 2: ", JLabel.CENTER);
        this.qPanel.add(this.q2Label);
        this.qPanel.add(this.queues[1]);

        this.q3Label = new JLabel("Queue 3: ", JLabel.CENTER);
        this.qPanel.add(this.q3Label);
        this.qPanel.add(this.queues[2]);

        this.q4Label = new JLabel("Queue 4: ", JLabel.CENTER);
        this.qPanel.add(this.q4Label);
        this.qPanel.add(this.queues[3]);

        this.q5Label = new JLabel("Queue 5: ", JLabel.CENTER);
        this.qPanel.add(this.q5Label);
        this.qPanel.add(this.queues[4]);

        this.simLabel = new JLabel("Current simulation time: ", JLabel.CENTER);
        this.qPanel.add(this.simLabel);

        this.timeLabel = new JLabel("", JLabel.CENTER);
        this.qPanel.add(this.timeLabel);

        this.contentPane.add(this.qPanel);

    }

    public JTextField[] getQueues() { return this.queues; }
    public JTextField getWTextField() { return this.wTextField; }
    public JLabel getTimeLabel() { return this.timeLabel; }
    public void setTimeLabel(String s) { this.timeLabel.setText(s); }
}
