/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/


package sunseeker.telemetry;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Color;



class MainInterface extends JFrame{

	//interface components
	private Container contentPane;
	private JMenuBar menuBar;
	private JPanel dataLabels;
	private JTextArea log;
	private JTabbedPane graphTabs;

	private SpringLayout layout;
	
	//interface config
	final public int WIDTH = 1000;
	final public int HEIGHT = 750;
	final public int PADDING = 10;

	//Receiver
	IReceiver data;
	private double graphData;


	MainInterface () {
		contentPane = new JPanel();
		layout = new SpringLayout();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sunseeker Telemetry");
        //this.getContentPane().setBackground(Color.BLACK);
        setLocation(500,0);
        setLayout(layout);

        contentPane = getContentPane();

        createMenuBar();
		createGraph();
		createDataLabels();
		createLog();

		data = new DumbyReceiver();

	}

	public void addMenu (JMenu menu) {
        menuBar.add(menu);
        setMinimumSize(new Dimension(50, 20));
    }

    private void createMenuBar () {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        contentPane.add(menuBar);

        layout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, contentPane);
    }

	public void createGraph () {

		graphTabs = new JTabbedPane(JTabbedPane.TOP);

		VoltageGraph volt = new VoltageGraph();
		CurrentGraph amp = new CurrentGraph();

		graphTabs.addTab("Voltage", volt);
		graphTabs.addTab("Current", amp);

		contentPane.add(graphTabs);

		graphTabs.setPreferredSize(new Dimension(800 ,500));

		layout.putConstraint(SpringLayout.NORTH, graphTabs, PADDING, SpringLayout.SOUTH, menuBar);
		fullWidthConstraint(graphTabs);
	}

	public void createDataLabels () {
		dataLabels = new DataInterface();

		dataLabels.setPreferredSize(new Dimension(200,300));

		contentPane.add(dataLabels);

		layout.putConstraint(SpringLayout.NORTH, dataLabels, PADDING, SpringLayout.SOUTH, graphTabs);
		layout.putConstraint(SpringLayout.WEST, dataLabels, PADDING, SpringLayout.WEST, contentPane);


	}

	public void createLog () {
		log = new ConsoleInterface();

		log.setPreferredSize(new Dimension(500, 300));

		contentPane.add(log);

		layout.putConstraint(SpringLayout.NORTH, log, PADDING, SpringLayout.SOUTH, graphTabs);
		layout.putConstraint(SpringLayout.SOUTH, log, PADDING * -1, SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.EAST, log, PADDING * -1, SpringLayout.EAST, contentPane);
	}


	public void fullWidthConstraint (Component comp) {
		layout.putConstraint(SpringLayout.WEST, comp, PADDING, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, comp, PADDING * -1, SpringLayout.EAST, contentPane);
	}

	public int getFrameWidtht () {
		return (int) this.getContentPane().getSize().getWidth();
	}

	public int getFrameHeight () {
		return (int) this.getContentPane().getSize().getHeight();
	}

}