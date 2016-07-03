/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.awt.*;
import java.util.ArrayList;

class Telemetry implements Runnable {
    DataSourceInterface dataSource;

    ArrayList<DataType> dataTypes;

	public static void main (String[] args) {
        EventQueue.invokeLater(new Telemetry());
	}

    public Telemetry () {
        dataSource = new FileDataSource();
        dataTypes  = new ArrayList<DataType>();

        /*
         * Add the known data types
         */
        dataTypes.add(new DataType("speed", new DataCollection()));
        dataTypes.add(new DataType("voltage", new DataCollection()));
        dataTypes.add(new DataType("current", new DataCollection()));
        dataTypes.add(new DataType("array", new DataCollection()));
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
    }

    protected AbstractLinePanel[] getLinePanels () {
        AbstractLinePanel[] panels = new AbstractLinePanel[dataTypes.size()];
        int i = 0;

        for (DataType type : dataTypes) {
            panels[i++] = new LinePanel(type);

            type.setProvided(
                dataSource.provides(type.getName())
            );

            type.setEnabled(true);
        }

        return panels;
    }
}