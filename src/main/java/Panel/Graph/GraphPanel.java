/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 *
 * @modified by Kai Gray <kai.a.gray@wmich.edu>
 * @date August 1, 2016
 */

package Panel.Graph;

import java.awt.*;

public class GraphPanel extends AbstractGraphPanel {
    final protected int SCALE_HASH_SIZE = 1;

    protected Graphics2D artist;

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        artist = (Graphics2D) g;

        drawBackground();

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

        int red = backgroundColor.getRed();
        int green = backgroundColor.getGreen();
        int blue = backgroundColor.getBlue();

        if(red+green+blue >= 382){
            artist.setColor(new Color(0, 0, 0));
        }
        else{
            artist.setColor(new Color(255, 255, 255));
        }

        /*
         * Draw the x-axis
         */
        artist.drawLine(
            0, xAxisInset,
            PANEL_WIDTH, xAxisInset
        );

        /*
         * Draw the y-axis
         */
        artist.drawLine(
            Y_AXIS_INSET, 0,
            Y_AXIS_INSET, PANEL_HEIGHT
        );

        drawScales();
    }

    protected void drawScales () {
        drawXScale();
        drawYScale();
    }

    protected void drawXScale () {
        int value    = X_AXIS_SCALE;
        int halfHash = SCALE_HASH_SIZE / 2;

        for (; value < PANEL_WIDTH; value += X_AXIS_SCALE) {
            artist.drawLine(
                AXIS_INSET + value, xAxisInset - SCALE_HASH_SIZE,
                AXIS_INSET + value, xAxisInset + SCALE_HASH_SIZE
            );
        }
    }

    protected void drawYScale () {
//        int posOffset = xAxisInset - Y_AXIS_SCALE;
//        int negOffset = xAxisInset + Y_AXIS_SCALE;
        int posOffset = xAxisInset;
        int negOffset = xAxisInset;

        // Grants code. Better way of scaling Y Axis, now works with variable range too!
        int numLabels = 16;
        int change = Y_AXIS_MAX/(numLabels);

        posOffset -= Y_AXIS_SCALE;
        negOffset += Y_AXIS_SCALE;

        artist.setFont(new Font("Monospaced", Font.PLAIN, 10));

        int cnt = 0;
//        while (posOffset > 0 || negOffset < PANEL_HEIGHT) {
        while (cnt <= numLabels) {
            cnt++;
            int drawNum;
            if (posOffset > 0) {
                drawYScaleHash(posOffset);

                posOffset -= Y_AXIS_SCALE;
            }
            if(posOffset % 20 == 0){
//                artist.drawString(" " + (Y_AXIS_MAX - posOffset + 40), Y_AXIS_PADDING, posOffset);
                drawNum = Y_AXIS_MAX - (numLabels-cnt)*change;
                int remain = drawNum % 10;
                if (remain < 5 && remain != 0) drawNum -= remain;
                else if (remain >= 5) drawNum += 10-remain;
                artist.drawString(String.format("%d", drawNum), Y_AXIS_PADDING, posOffset);
            }

            if (negOffset < PANEL_HEIGHT) {
                drawYScaleHash(negOffset);

                negOffset += Y_AXIS_SCALE;
            }
            if(negOffset % 20 == 0){
//                artist.drawString("-" + (Y_AXIS_MIN + negOffset), Y_AXIS_PADDING, negOffset + 10);
                drawNum = Y_AXIS_MIN + (numLabels-cnt)*change;
                int remain = drawNum % 10;
                if (remain < 5 && remain != 0) drawNum -= remain;
                else if (remain >= 5) drawNum += 10-remain;
                artist.drawString(String.format("%d", drawNum), Y_AXIS_PADDING, negOffset + 10);
            }
        }
    }

    protected void drawYScaleHash (int offset) {
        artist.drawLine(
            Y_AXIS_INSET - SCALE_HASH_SIZE, offset,
            Y_AXIS_INSET + SCALE_HASH_SIZE, offset
        );
    }

    protected void drawBackground(){
        artist.setColor(backgroundColor);
        artist.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void setBackgroundColor(Color color){
        backgroundColor = color;
        drawBackground();
    }
}
