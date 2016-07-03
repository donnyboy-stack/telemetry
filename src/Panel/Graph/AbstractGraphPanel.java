/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

abstract class AbstractGraphPanel extends AbstractPanel {
    final public static int AXIS_WIDTH   = 2;
    final public static int AXIS_INSET   = 30;
    final public static int FULL_INSET   = AXIS_WIDTH + AXIS_INSET;
    final public static int PANEL_HEIGHT = 400;
    final public static int PANEL_WIDTH  = AbstractMainView.VIEW_WIDTH - AbstractMainView.AXIS_PADDING;

    final public static int Y_AXIS_MIN   = -200; // Must be <= 0
    final public static int Y_AXIS_MAX   = 200;
    final public static int Y_AXIS_RANGE = Math.abs(Y_AXIS_MIN) + Y_AXIS_MAX;
    final public static int Y_AXIS_SCALE = 10;
    final public static int Y_AXIS_INSET = AXIS_INSET;

    final public static int X_AXIS_SCALE = PANEL_WIDTH / DataCollectionInterface.MAX_DATA_POINTS;
    final public static int X_AXIS_INSET = PANEL_HEIGHT - AXIS_INSET;

    final public static int MAX_POINTS   = PANEL_WIDTH / X_AXIS_SCALE;
}
