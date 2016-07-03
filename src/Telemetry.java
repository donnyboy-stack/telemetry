/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.awt.*;
import java.util.ArrayList;
import java.lang.Thread;

class Telemetry implements Runnable {
    DataSourceInterface dataSource;
    ArrayList<DataCollectionInterface> dataCollections;
    DataSubscriberInterface dataSubscriber;

	public static void main (String[] args) {
        EventQueue.invokeLater(new Telemetry());
	}

    public Telemetry () {
        dataCollections = new ArrayList<DataCollectionInterface>();
        dataSubscriber  = new DataSubscriber();
        dataSource      = new PseudoRandomDataSource(dataSubscriber);

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
         * This is the main window which appears
         */
        AbstractMainView main = new MainView();

        /*
         * Controls the rendering of the main window interface
         */
        MainController controller = new MainController(main);

        /*
         * The graph to display the data
         */
        AbstractGraphPanel graph = new GraphPanel();
        controller.useGraphPanel(graph);

        /*
         * Options regarding which data to display
         */
        AbstractDataSelectPanel dataSelect = new DataSelectPanel();
        controller.useDataSelectPanel(dataSelect);

        /*
         * Display for the most recent values of the data being displayed
         */
        AbstractLiveDataPanel liveData = new LiveDataPanel();
        controller.useLiveDataPanel(liveData);

        /*
         * Add the line panels to the graph
         */
        controller.useLinePanels(getLinePanels());

        /*
         * Start the application
         */
        controller.run();

        Thread dataThread = new Thread(dataSource, "dataSourceThread");

        dataThread.start();
    }

    protected void registerDataType (String name, String units) {
        DataCollectionInterface collection = new DataCollection(name, units);

        dataCollections.add(collection);
        dataSubscriber.subscribe(collection);
    }

    protected AbstractLinePanel[] getLinePanels () {
        AbstractLinePanel[] panels = new AbstractLinePanel[dataCollections.size()];
        int i = 0;

        for (DataCollectionInterface collection : dataCollections) {
            panels[i++] = new LinePanel(collection);

            collection.setProvided(
                dataSource.provides(collection.getType())
            );

            collection.setEnabled(true);
        }

        return panels;
    }
}