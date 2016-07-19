/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.lang.Thread;

class ShutdownController extends Thread {
    protected ProfileInterface profile;

    public ShutdownController (ProfileInterface profile) {
        this.profile = profile;
    }

    public void run () {
        if (profile.hasChanged()) {
            // Ask the user if they want to save the profile
        }
    }
}
