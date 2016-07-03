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

class LinePanel extends AbstractLinePanel {
    protected Color[] colors = {
        Color.BLUE,
        Color.RED,
        Color.ORANGE,
        Color.MAGENTA,
        Color.YELLOW,
        Color.CYAN
    };

    protected int[] data = {10, 11, 99, 134, 43, 79, 72, 14, 88, 64, 30, 0, 1, 37};

    protected int width = 0;
    protected int height = 0;

    protected static int colorCount = 0;
    protected Color color;

    protected Graphics2D artist;

    public LinePanel () {
        /*
         * Need to see the other lines and graph
         */
        setOpaque(false);

        /*
         * Determine which color we'll be using for this line
         */
        color = colors[colorCount % colors.length];
        colorCount++;
    }

    public void paintComponent (Graphics g) {
        width = getWidth();
        height = getHeight();

        super.paintComponent(g);

        artist = (Graphics2D) g;

        drawSegments();
    }

    protected void drawSegments () {
        int i;
        int scale = 30;

        artist.setStroke(new BasicStroke(
            LINE_WIDTH,
            BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER
        ));

        artist.setColor(color);

        for (i = 1; i < data.length; i++) {
            drawSegment((i-1) * scale, data[i-1], i * scale, data[i]);
        }
    }

    protected void drawSegment (int x1, int y1, int x2, int y2) {
        artist.drawLine(
            x1, height - y1,
            x2, height - y2
        );
    }
}
