package MVC.GUI;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame
{
    private JPanel contentPane;

    private JPanel inputPanel;
    private JLabel timeLabel;
    private JTextField timeTextField;
    private JLabel serverLabel;
    private JTextField serverTextField;
    private JLabel clientLabel;
    private JTextField clientTextField;
    private JLabel minArrivalLabel;
    private JTextField minArrivalTextField;
    private JLabel maxArrivalLabel;
    private JTextField maxArrivalTextField;
    private JLabel minServiceLabel;
    private JTextField minServiceTextField;
    private JLabel maxServiceLabel;
    private JTextField maxServiceTextField;
    private ButtonGroup strButtonGroup;
    private JRadioButton timeButton;
    private JRadioButton shortButton;

    private JLabel statusLabel;
    private JLabel okLabel;

    private JButton validateButton;
    private JButton startButton;

    public SimulationFrame(String name)
    {
        super(name);
        this.prepareGui();
    }

    private void prepareGui()
    {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane = new JPanel(new GridLayout(1, 1));
        this.prepareInputPanel();
        this.setContentPane(this.contentPane);
    }

    public void prepareInputPanel()
    {
        this.inputPanel = new JPanel();
        this.inputPanel.setLayout(new GridLayout(10, 2));

        this.timeLabel = new JLabel("Simulation time: ", JLabel.CENTER);
        this.inputPanel.add(this.timeLabel);

        this.timeTextField = new JTextField();
        this.inputPanel.add(this.timeTextField);

        this.serverLabel = new JLabel("Number of servers: ", JLabel.CENTER);
        this.inputPanel.add(this.serverLabel);

        this.serverTextField = new JTextField();
        this.inputPanel.add(this.serverTextField);

        this.clientLabel = new JLabel("Number of clients: ", JLabel.CENTER);
        this.inputPanel.add(this.clientLabel);

        this.clientTextField = new JTextField();
        this.inputPanel.add(this.clientTextField);

        this.minArrivalLabel = new JLabel("Minimum arrival time: ", JLabel.CENTER);
        this.inputPanel.add(this.minArrivalLabel);

        this.minArrivalTextField = new JTextField();
        this.inputPanel.add(this.minArrivalTextField);

        this.maxArrivalLabel = new JLabel("Maximum arrival time: ", JLabel.CENTER);
        this.inputPanel.add(this.maxArrivalLabel);

        this.maxArrivalTextField = new JTextField();
        this.inputPanel.add(this.maxArrivalTextField);

        this.minServiceLabel = new JLabel("Minimum service time: ", JLabel.CENTER);
        this.inputPanel.add(this.minServiceLabel);

        this.minServiceTextField = new JTextField();
        this.inputPanel.add(this.minServiceTextField);

        this.maxServiceLabel = new JLabel("Maximum service time: ", JLabel.CENTER);
        this.inputPanel.add(this.maxServiceLabel);

        this.maxServiceTextField = new JTextField();
        this.inputPanel.add(this.maxServiceTextField);

        this.strButtonGroup = new ButtonGroup();
        this.timeButton = new JRadioButton("Shortest waiting time", true);
        this.shortButton = new JRadioButton("Shortest queue");
        this.strButtonGroup.add(this.timeButton);
        this.strButtonGroup.add(this.shortButton);
        this.inputPanel.add(timeButton);
        this.inputPanel.add(shortButton);

        this.statusLabel = new JLabel("Input status: ", JLabel.CENTER);
        this.okLabel = new JLabel("", JLabel.CENTER);
        this.inputPanel.add(this.statusLabel);
        this.inputPanel.add(this.okLabel);

        this.startButton = new JButton("Start simulation");
        this.startButton.addActionListener(new Controller(this));
        this.inputPanel.add(this.startButton);

        this.validateButton = new JButton("Validate input");
        this.validateButton.addActionListener(new ValidateListener(this));
        this.inputPanel.add(this.validateButton);

        this.contentPane.add(this.inputPanel);

    }



    public JTextField getTimeTextField() { return this.timeTextField; }
    public JTextField getServerTextField() { return this.serverTextField; }
    public JTextField getClientTextField() { return this.clientTextField; }
    public JTextField getMinArrivalTextField() { return this.minArrivalTextField; }
    public JTextField getMaxArrivalTextField() { return this.maxArrivalTextField; }
    public JTextField getMinServiceTextField() { return this.minServiceTextField; }
    public JTextField getMaxServiceTextField() { return this.maxServiceTextField; }
    public JLabel getOkLabel() { return this.okLabel; }
    public JRadioButton getTimeButton() { return this.timeButton; }
}
