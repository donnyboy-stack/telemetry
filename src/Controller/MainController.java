/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;

class MainController {
    final public static int LINE_REFRESH_INTERVAL = 250;

    protected AbstractMainFrame frame;

    ProfileInterface profile;

    public MainController () {
        frame = new MainFrame();
    }

    public void start (ProfileInterface profile) {
        this.profile = profile;

        frame.showFrame();
    }
}
