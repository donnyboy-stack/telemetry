/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.lang.Runnable;

class Application implements Runnable {
    public void run () {
        /*
         * Create the data controller
         */
        DataController dataController = new DataController();

        /*
         * Create the main controller
         */
        MainController mainController = new MainController();

        /*
         * Allow the user to select their profile
         */
        InitialProfileLoader profileLoader = new InitialProfileLoader(dataController.getDataSources());

        profileLoader.loadProfile(new ProfileLoaderObserverInterface() {
            public void receiveProfile (ProfileInterface profile) {
                if (profile instanceof ProfileInterface) {
                    /*
                     * This will allow us to respond to the user shutting the app down
                     */
                    Runtime.getRuntime().addShutdownHook(new ShutdownController(profile));

                    dataController.start(profile.getDataSource());
                    mainController.start(profile);
                } else {
                    System.exit(1);
                }
            }
        });
    }
}
