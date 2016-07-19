/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.lang.Runnable;

class Application implements Runnable {
    protected MainController mainController;
    protected DataController dataController;
    
    protected AbstractGraphPanel graphPanel;
    protected AbstractDataSelectPanel dataSelectPanel;
    protected AbstractLiveDataPanel liveDataPanel;

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
        graphPanel = new GraphPanel();
        mainController.useGraphPanel(graphPanel);

        /*
         * Options regarding which data to display
         */
        dataSelectPanel = new DataSelectPanel();
        mainController.useDataSelectPanel(dataSelectPanel);

        /*
         * Display for the most recent values of the data being displayed
         */
        liveDataPanel = new LiveDataPanel();
        mainController.useLiveDataPanel(liveDataPanel);

        /*
         * Create the data controller and get the source
         */
        dataController = new DataController(mainFrame);

        makeAwareOfTypes();

        /*
         * Start collecting data
         */
        dataController.start();

        /*
         * Start the application
         */
        mainController.start();
    }

    protected void makeAwareOfTypes () {
        DataTypeCollectionInterface types = dataController.getDataSource().getTypes();

        mainController.setTypes(types);
    }
}
