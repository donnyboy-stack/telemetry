/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 21, 2016
 */

package Frame.EditProfile;

import App.Profile.ProfileInterface;
import Frame.AbstractFrame;

public abstract class AbstractEditProfileFrame extends AbstractFrame {
    /*
     * Frame title
     */
    final protected String FRAME_TITLE = "Edit Profile";

    /*
     * Frame dimensions
     */
    final protected int FRAME_WIDTH  = 500;
    final protected int FRAME_HEIGHT = 300;

    /*
     * The profile being edited
     */
    protected ProfileInterface profile;

    public AbstractEditProfileFrame (ProfileInterface profile) {
        this.profile = profile;
    }
}
