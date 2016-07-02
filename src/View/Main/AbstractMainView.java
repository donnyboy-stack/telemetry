/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

abstract class AbstractMainView extends AbstractView {
    abstract public void useGraphPanel(AbstractGraphPanel panel);

    abstract public void useDataSelectPanel(AbstractDataSelectPanel panel);

    abstract public void useLiveDataPanel(AbstractLiveDataPanel panel);
}