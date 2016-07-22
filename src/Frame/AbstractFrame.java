/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import javax.swing.JFrame;

abstract class AbstractFrame extends JFrame {
    final public static int PADDING      = 10;
    final public static int AXIS_PADDING = PADDING * 2;

    public void showFrame () {
        setVisible(true);
    }

    public void hideFrame () {
        setVisible(false);
    }
}