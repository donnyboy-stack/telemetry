/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Panel.Graph;

import Data.Type.DataTypeInterface;
import Frame.Main.AbstractMainFrame;
import Panel.AbstractPanel;

import java.awt.Color;

public abstract class AbstractGraphPanel extends AbstractPanel {
    final public static int AXIS_WIDTH   = 2;
    final public static int AXIS_INSET   = 40; // How far the axes(plural) is from the left side, default 30
    final public static int FULL_INSET   = AXIS_WIDTH + AXIS_INSET;
    final public static int PANEL_HEIGHT = 400;
    final public static int PANEL_WIDTH  = AbstractMainFrame.FRAME_WIDTH - AbstractMainFrame.AXIS_PADDING;

    public int Y_AXIS_MIN   = -200; // Must be <= 0, was -180
    public int Y_AXIS_MAX   = 200; // 180
    public int Y_AXIS_RANGE = Math.abs(Y_AXIS_MIN) + Y_AXIS_MAX;
    final public static int Y_AXIS_SCALE = 10; // 10
    final public static int Y_AXIS_INSET = AXIS_INSET;
    final public static int Y_AXIS_PADDING = 2;

    final public static int X_AXIS_SCALE = (PANEL_WIDTH - AXIS_INSET) / DataTypeInterface.MAX_DATA_POINTS;
    final public static int X_AXIS_INSET = PANEL_HEIGHT - AXIS_INSET;

    final public static int MAX_POINTS   = PANEL_WIDTH / X_AXIS_SCALE;

    protected int xAxisInset;
    protected double ratio;

    protected Color backgroundColor = new Color(255, 255, 255);

    public AbstractGraphPanel () {
        setBackground(Color.WHITE);

        /*
         * Calculate the x-axis inset from top of panel
         */
        xAxisInset = (int) ((float) PANEL_HEIGHT * (Y_AXIS_MAX / (float) Y_AXIS_RANGE));

        /*
         * Calculate the y axis ratio
         */
        ratio = PANEL_HEIGHT / (double) Y_AXIS_RANGE;
    }
    // Gets the position of data point based on range of Y Axis
    public int getYPos (double value) {
        double pos = 0;
        Y_AXIS_RANGE = Math.abs(Y_AXIS_MIN) + Y_AXIS_MAX;
        ratio = PANEL_HEIGHT / (double) Y_AXIS_RANGE;

        if (value > 0){
            pos = Y_AXIS_MAX * (value / Y_AXIS_MAX);
        }

        if (value < 0){
            pos = Y_AXIS_MIN * (value / Y_AXIS_MIN);
        }

        return xAxisInset - (int) (pos * ratio);
    }

    public void setYMin(int min){
        Y_AXIS_MIN = min;
    }
    public void setYMax(int max){
        Y_AXIS_MAX = max;
    }

    public void setBackgroundColor(Color color){
        backgroundColor = color;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }
}
