/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

class GraphPanel extends AbstractGraphPanel {
    final protected int AXIS_WIDTH = 3;

    Graphics2D artist;

    int width = 0;
    int height = 0;

    public GraphPanel () {
        /*
         * The graph will have a white background
         */
        setBackground(Color.WHITE);
    }

    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();

        super.paintComponent(g);

        artist = (Graphics2D) g;

        drawAxes();
    }

    protected void drawAxes () {
        artist.setStroke(new BasicStroke(
            AXIS_WIDTH,
            BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER
        ));

        artist.drawLine(
            0, height,
            width, height
        );

        artist.drawLine(
            0, 0,
            0, height
        );
    }
}
