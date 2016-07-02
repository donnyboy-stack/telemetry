/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.awt.*;

class Telemetry implements Runnable {
	public static void main (String[] args) {
        EventQueue.invokeLater(new Telemetry());
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
         * Start the application
         */
        controller.run();
    }
}