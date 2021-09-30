/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.lang.Runnable;

public class Application implements Runnable {
    DataController dataController;

    MainController mainController;

    InitialProfileLoader profileLoader;

    // This is implementing the run() method of Runnable interface. It is run in Telemetry.java in main method,
    // Pretty sure it is called by the line: EventQueue.invokeLater(new Application());
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
        profileLoader = new InitialProfileLoader(dataController.getDataSources());
        loadProfile();
    }

    protected void loadProfile () {
        // Creating an instance of ProfileLoaderObserverInterface, implementing the receiveProfile method...
        // It would be better to create a separate file which implements this. Might change later.
        profileLoader.loadProfile(new ProfileLoaderObserverInterface() {
            public void receiveProfile (ProfileInterface profile) {
                if (profile instanceof ProfileInterface) { //Seems like redundant check, as the parameter has to be that type
                    if (!dataController.start(profile.getDataSource())) {
                        loadProfile();
                        return;
                    }

                    // This runs the live graphing and data visuals you see (The main part of the program).
                    mainController.start(profile);

                    /*
                     * This will allow us to respond to the user shutting the app down
                     */
                    Runtime.getRuntime().addShutdownHook(new ShutdownController(profile));
                } else {
                    System.exit(1);
                }
            }
        });
    }
}
