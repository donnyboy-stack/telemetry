/**
 * Sunseeker Telemetry
 *
 * @author by Grant Reamy <grant.a.reamy@wmich.edu>
 * @date October 1, 2021
 */

package Frame.SaveProfile;

import App.Profile.ProfileInterface;
import Frame.AbstractFrame;
import Menu.Main.Observer.MainMenuObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class AbstractSaveProfileFrame extends AbstractFrame {
    final protected String FRAME_TITLE = "Save Profile?";

    final protected int FRAME_WIDTH  = 400;
    final protected int FRAME_HEIGHT = 125;

    protected List<MainMenuObserverInterface> observers;

    protected ProfileInterface profile;

    public AbstractSaveProfileFrame (ProfileInterface profile){
        this.profile = profile;
        this.observers = new ArrayList<MainMenuObserverInterface>();
    }
}
