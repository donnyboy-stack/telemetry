/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;

class GraphPanel extends AbstractGraphPanel {
    final protected int SCALE_HASH_SIZE = 5;

    Graphics2D artist;

    int width = 0;
    int height = 0;

    public GraphPanel () {
        /*
         * Set the background color
         */
        setBackground(Color.WHITE);
    }

    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();

        super.paintComponent(g);

        artist = (Graphics2D) g;

        /*
         * These give us our scale
         */
        drawAxes();
    }

    protected void drawAxes () {
        artist.setStroke(new BasicStroke(
            AXIS_WIDTH,
            BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER
        ));

        /*
         * Draw the x-axis
         */
        artist.drawLine(
            0, height - AXIS_INSET,
            width, height - AXIS_INSET
        );

        /*
         * Draw the y-axis
         */
        artist.drawLine(
            AXIS_INSET, 0,
            AXIS_INSET, height
        );

        drawScales();
    }

    protected void drawScales () {
        drawXScale();
        drawYScale();
    }

    protected void drawXScale () {
        int scale    = 30;
        int value    = scale;
        int offset   = AXIS_INSET + AXIS_WIDTH;
        int halfHash = SCALE_HASH_SIZE / 2;

        for (; value < width; value += scale) {
            artist.drawLine(
                offset + value, height - (offset + halfHash),
                offset + value, height - (offset - halfHash)
            );
        }
    }

    protected void drawYScale () {
        int scale    = 30;
        int offset   = AXIS_INSET + AXIS_WIDTH;
        int value    = height - offset - scale;
        int halfHash = SCALE_HASH_SIZE / 2;

        for (; value > 0; value -= scale) {
            artist.drawLine(
                offset - halfHash, value,
                offset + halfHash, value
            );
        }
    }

    protected void drawLines () {
        
    }
}
