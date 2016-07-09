/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.lang.Runnable;

class Telemetry implements Runnable {
    DataSourceInterface dataSource;
    ArrayList<DataCollectionInterface<Double>> dataCollections;

	public static void main (String[] args) {
        EventQueue.invokeLater(new Telemetry());
	}

    public Telemetry () {
        dataCollections = new ArrayList<DataCollectionInterface<Double>>();

        /*
         * Add the known data types
         */
        registerDataType("speed", "mph");
        registerDataType("voltage", "volts");
        registerDataType("current", "amps");
        registerDataType("array", "watts");

        dataSource = new PseudoRandomDataSource(dataCollections);
    }

    public void run () {
        /*
         * This is the main frame which appears
         */
        AbstractMainFrame mainFrame = new MainFrame();

        /*
         * Controls the rendering of the main window interface
         */
        MainController mainController = new MainController(mainFrame);

        /*
         * The graph to display the data
         */
        AbstractGraphPanel graph = new GraphPanel();
        mainController.useGraphPanel(graph);

        /*
         * Options regarding which data to display
         */
        AbstractDataSelectPanel dataSelect = new DataSelectPanel();
        mainController.useDataSelectPanel(dataSelect);

        /*
         * Display for the most recent values of the data being displayed
         */
        AbstractLiveDataPanel liveData = new LiveDataPanel();
        mainController.useLiveDataPanel(liveData);

        /*
         * Add the line panels to the graph
         */
        mainController.useLinePanels(getLinePanels());

        /*
         * Create the data controller
         */
        DataController dataController = new DataController(dataCollections);

        dataController.promptForDataSource(mainFrame);
        dataController.start();

        /*
         * Start the application
         */
        mainController.start();
    }

    protected void registerDataType (String type, String units) {
        DataCollectionInterface<Double> collection = new DataCollection<Double>(type, units);

        dataCollections.add(collection);
    }

    protected AbstractLinePanel[] getLinePanels () {
        AbstractLinePanel[] panels = new AbstractLinePanel[dataCollections.size()];
        int i = 0;

        for (DataCollectionInterface collection : dataCollections) {
            AbstractLinePanel panel = new LinePanel(collection);

            collection.setProvided(
                dataSource.provides(collection.getType())
            );

            collection.setEnabled(true);

            panels[i++] = panel;
        }

        return panels;
    }
}