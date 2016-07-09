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
    protected AbstractDataTypeCollection dataTypes;

    protected MainController mainController;
    protected DataController dataController;

	public static void main (String[] args) {
        EventQueue.invokeLater(new Telemetry());
	}

    public Telemetry () {
        dataTypes = new DataTypeCollection();

        /*
         * Add the known data types
         */
        registerDataType("speed", "mph");
        registerDataType("voltage", "volts");
        registerDataType("current", "amps");
        registerDataType("array", "watts");
    }

    public void run () {
        /*
         * This is the main frame which appears
         */
        AbstractMainFrame mainFrame = new MainFrame();

        /*
         * Controls the rendering of the main window interface
         */
        mainController = new MainController(mainFrame);

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
        dataController = new DataController(dataTypes);

        getDataSource();

        /*
         * Start collecting data
         */
        dataController.start();

        /*
         * Start the application
         */
        mainController.start();
    }

    protected void registerDataType (String type, String units) {
        DataTypeInterface collection = new DataType(type, units);

        dataTypes.add(collection);
    }

    protected AbstractLinePanel[] getLinePanels () {
        AbstractLinePanel[] panels = new AbstractLinePanel[dataTypes.size()];
        int i = 0;

        for (DataTypeInterface type : dataTypes)
            panels[i++] = new LinePanel(type);

        return panels;
    }

    protected void getDataSource () {
        dataController.promptForDataSource(mainController.getFrame());

        checkDataTypes(dataController.getDataSource());
    }

    protected void checkDataTypes (DataSourceInterface dataSource) {
        for (DataTypeInterface type : dataTypes) {
            type.setProvided(
                dataSource.provides(type.getType())
            );

            type.setEnabled(true);
        }
    }
}