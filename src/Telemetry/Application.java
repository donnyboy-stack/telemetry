/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.lang.Runnable;

class Application implements Runnable {
    protected DataController dataController;
    protected MainController mainController;
    
    protected AbstractGraphPanel graphPanel;
    protected AbstractDataSelectPanel dataSelectPanel;
    protected AbstractLiveDataPanel liveDataPanel;

    public void run () {
        /*
         * Create the data controller
         */
        dataController = new DataController();

        /*
         * Create the main controller
         */
        mainController = new MainController();

        /*
         * Allow the user to select their profile
         */
        ProfileLoader profileLoader = new InitialProfileLoader(dataController.getDataSources());

        ((InitialProfileLoader) profileLoader).loadProfile(new ProfileLoaderObserverInterface() {
            public void receiveProfile (ProfileInterface profile) {
                dataController.start(profile.getDataSource());
                mainController.start(profile);
            }
        });

        // Some of what's commented out below might be reused.... keeping for now

        // /*
        //  * This is the main frame which appears
        //  */
        // AbstractMainFrame mainFrame = new MainFrame();

        // /*
        //  * Controls the rendering of the main window interface
        //  */
        // mainController = new MainController(mainFrame);

        // /*
        //  * The graph to display the data
        //  */
        // graphPanel = new GraphPanel();
        // mainController.useGraphPanel(graphPanel);

        // /*
        //  * Options regarding which data to display
        //  */
        // dataSelectPanel = new DataSelectPanel();
        // mainController.useDataSelectPanel(dataSelectPanel);

        // /*
        //  * Display for the most recent values of the data being displayed
        //  */
        // liveDataPanel = new LiveDataPanel();
        // mainController.useLiveDataPanel(liveDataPanel);

        // /*
        //  * Create the data controller and get the source
        //  */
        // dataController = new DataController(mainFrame);

        // makeAwareOfTypes();

        // /*
        //  * Start collecting data
        //  */
        // dataController.start();

        // /*
        //  * Start the application
        //  */
        // mainController.start();
    }
}
