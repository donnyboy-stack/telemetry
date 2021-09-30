/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App;

import App.Profile.Loader.*;
import App.Profile.Loader.Observer.ProfileLoaderObserverInterface;
import App.Profile.ProfileInterface;
import Controller.*;

import java.lang.Runnable;
// This class is the main class run after Telemetry, and instantiates and runs all of the controllers, which run
// all parts of the application itself.
public class Application implements Runnable {
    // Handles getting the intended serial port and connecting to that port.
    DataController dataController;

    // Handles changing of windows and running the GUI itself
    MainController mainController;

    // Handles creating a default profile (if 'create new profile' is selected), or loading from a file (if selected)
    InitialProfileLoader profileLoader;

    // This is implementing the run() method of Runnable interface. It is run in Telemetry.java in main method,
    // run will be called by the line EventQueue.invokeLater(new Application());
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
        // profileLoader.loadProfile takes an object of the ProfileLoaderObserverInterface, though instead of having
        // a class that implements this, line 51 creates an object and implements the required object from the interface.
        profileLoader.loadProfile(new ProfileLoaderObserverInterface() {
            public void receiveProfile (ProfileInterface profile) {
                // dataController.start returns true if everything ran without any problem.
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
            }
        });
    }
}
