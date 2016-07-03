/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.awt.Color;

class LinePanel extends AbstractLinePanel {
    int[] data = {10, 12, 33, 55, 7, 90, 40};

    public LinePanel () {
        /*
         * Need to see the other lines and graph
         */
        setOpaque(false);
    }
}
