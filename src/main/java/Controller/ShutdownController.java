/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package Controller;

import App.Profile.ProfileInterface;
import Data.Source.*;

import java.lang.Thread;

// This handles when the user closes the application, run() method will be run when the user closes the application.
public class ShutdownController extends Thread {
    protected ProfileInterface profile;

    public ShutdownController (ProfileInterface profile) {
        this.profile = profile;
    }

    public void run () {
        if (profile.hasChanged()) {
            // Ask the user if they want to save the profile
            // TODO: Create new 'want to save profile?' panel to display here, and call profile writer if yes.
        }

        DataSourceInterface dataSource = profile.getDataSource();

        if (dataSource instanceof AbstractSerialDataSource) {
            dataSource.stop();
        }
    }
}
