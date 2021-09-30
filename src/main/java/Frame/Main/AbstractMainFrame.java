/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Frame.Main;

import Frame.AbstractFrame;
import Menu.Main.AbstractMainMenu;
import Panel.Graph.AbstractGraphPanel;
import Panel.Line.AbstractLinePanel;
import Panel.LiveData.AbstractLiveDataPanel;

public abstract class AbstractMainFrame extends AbstractFrame {
    /*
     * Frame title
     */
    final protected String FRAME_TITLE = "Telemetry";

    /*
     * Frame dimenstions
     */
    final public static int FRAME_WIDTH  = 1000;
    final public static int FRAME_HEIGHT = 700;

    abstract public void useMenu(AbstractMainMenu menu);

    abstract public void useGraphPanel(AbstractGraphPanel panel);

    abstract public void useLiveDataPanel(AbstractLiveDataPanel panel);

    abstract public void useLinePanels(AbstractLinePanel[] panels);
}