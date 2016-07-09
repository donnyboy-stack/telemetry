/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

class MainController {
    protected AbstractMainFrame mainFrame;

    public MainController (AbstractMainFrame main) {
        mainFrame = main;
    }

    public void run () {
        mainFrame.showFrame();
    }

    public void useGraphPanel (AbstractGraphPanel panel) {
        mainFrame.useGraphPanel(panel);
    }

    public void useDataSelectPanel (AbstractDataSelectPanel panel) {
        mainFrame.useDataSelectPanel(panel);
    }

    public void useLiveDataPanel (AbstractLiveDataPanel panel) {
        mainFrame.useLiveDataPanel(panel);
    }

    public void useLinePanels(AbstractLinePanel[] panels) {
        mainFrame.useLinePanels(panels);
    }
}