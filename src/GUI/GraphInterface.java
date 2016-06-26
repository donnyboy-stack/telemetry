/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/

package sunseeker.telemetry;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.util.ArrayList;
import java.util.List;


class GraphInterface extends JPanel {

	protected List<Double> dataPoints;

	final public int HEIGHT = 450;
	final public int WIDTH  = 900;

	final public int PADDING  = 10;
	final public int LPADDING = 10;

	final private static Stroke GRAPH_STROKE = new BasicStroke(2f);
	final public int RADIUS = 3;
	final public int CONST = 10;

	final public int numberYDivisions = 20;
	private int xPoint = 0, yPoint = 0;
	private int xBase = 0, yBase = 0;
	private int width = 0, height = 0;
	private String str = new String();

	private Color pointColor = new Color(10,10,10,100);
	private Color lineColor = new Color(100, 100, 100, 100);
	final private Color gridColor = new Color(200, 200, 200, 200);

	GraphInterface () {
		dataPoints = new ArrayList<>();
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
	}

	//Draw Graph
	@Override
	protected void paintComponent (Graphics g) {
		int i = 0;
		int xStr = 0, yStr = 0, xEnd = 0, yEnd = 0;

		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double xScale = ((width - PADDING - LPADDING) / (dataPoints.size() -1));
		double yScale = ((getHeight() - PADDING - LPADDING) / this.getMax());

		List<Point> graphPoints = new ArrayList<>();
		for(i = 0; i < dataPoints.size(); i++){
			xEnd = (int) (i * xScale + PADDING + LPADDING);
			yEnd = (int) ((this.getMax() - dataPoints.get(i)) * yScale + PADDING);
			graphPoints.add(new Point(xEnd, yEnd));
		}

        //fill white background
		graph.setColor(Color.WHITE);
		graph.fillRect((PADDING + LPADDING), PADDING, getWidth() - (2 * PADDING) - LPADDING, getHeight()  - (2 * PADDING) - LPADDING);
        //switch to black for axis
		graph.setColor(Color.BLACK);

        //draw y hash and grid
		for (i = 0; i < numberYDivisions + 1; i++) {

			xStr = PADDING + LPADDING;
			xEnd = RADIUS + xStr;
			yStr = getHeight() - ((i * (getHeight() - PADDING * 2 - LPADDING)) / numberYDivisions + xStr);
                //int yEnd = yStr;
			if (this.dataPoints.size() > 0) {
				graph.setColor(gridColor);
				graph.drawLine(PADDING + LPADDING + 1 + RADIUS, yStr, width - PADDING, yStr);
				graph.setColor(Color.BLACK);
				String yLabel = ((((int) (this.getMax() * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "");
				FontMetrics metrics = graph.getFontMetrics();
				int labelWidth = metrics.stringWidth(yLabel);
				graph.drawString(yLabel, xStr - labelWidth - 5, yStr + (metrics.getHeight() / 2) - 3);
			}
			graph.drawLine(xStr, yStr, xEnd, yStr);
		}

        //draw x hash and grid
		for (i = 0; i < this.dataPoints.size(); i++) {
			if (this.dataPoints.size() > 1) {
				xStr = (i * (width - PADDING * 2 - LPADDING)) / (this.dataPoints.size() - 1) + PADDING + LPADDING;
                    //int xEnd = xStr;
				yStr = height - PADDING - LPADDING;
				yEnd = yStr - RADIUS;
				if ((i % ((int) ((dataPoints.size() / 20.0)) + 1)) == 0) {
					graph.setColor(gridColor);
					graph.drawLine(xStr, getHeight() - PADDING - LPADDING - 1 - RADIUS, xStr, PADDING);
					graph.setColor(Color.BLACK);
					String xLabel = i + "";
					FontMetrics metrics = graph.getFontMetrics();
					int labelWidth = metrics.stringWidth(xLabel);
					graph.drawString(xLabel, xStr - labelWidth / 2, yStr + metrics.getHeight() + 3);
				}
				graph.drawLine(xStr, yStr, xStr, yEnd);
			}
		}

        // create x and y axes 
		graph.drawLine(PADDING + LPADDING, getHeight() - PADDING - LPADDING, PADDING + LPADDING, PADDING);
		graph.drawLine(PADDING + LPADDING, getHeight() - PADDING - LPADDING, width - PADDING, getHeight() - PADDING - LPADDING);

        //re-creates all previous dataPoints and lines during update
		Stroke oldStroke = graph.getStroke();
		graph.setColor(lineColor);
		graph.setStroke(GRAPH_STROKE);
		for (i = 0; i < graphPoints.size() - 1; i++) {
			xStr = graphPoints.get(i).x;
			yStr = graphPoints.get(i).y;
			xEnd = graphPoints.get(i + 1).x;
			yEnd = graphPoints.get(i + 1).y;
			graph.drawLine(xStr, yStr, xEnd, yEnd);
		}

        //creates point
		graph.setStroke(oldStroke);
		graph.setColor(pointColor);
		for (i = 0; i < graphPoints.size(); i++) {
                //point location
			int x = graphPoints.get(i).x - RADIUS / 2;
			int y = graphPoints.get(i).y - RADIUS / 2;
                //point size
			graph.fillOval(x, y, RADIUS, RADIUS);
		}


	}

	public void addPoint (double point) {
		this.dataPoints.add(point);
		invalidate();
		repaint();
	}

	private double getMax() {
        double max = 0;

        for(int i = 0; i < this.dataPoints.size(); i++) {
            if(this.dataPoints.get(i) > max)
                max = this.dataPoints.get(i);
        }
        return max;
    }

	public List<Double> getDatadataPoints () {
		return this.dataPoints;
	}

	private void setDatadataPoints (List<Double> dataPoints) {
		this.dataPoints = dataPoints;
	}

	protected void setHeight (int height) {
		if(height > HEIGHT) {
			this.height = (height - PADDING - LPADDING);
		}
		else{
			this.height = (HEIGHT - PADDING - LPADDING);
		}
			
	}

	protected void setWidth (int width) {
		if(height > HEIGHT) {
			this.width = (width - PADDING - LPADDING);
		}
		else{
			this.width = (WIDTH - PADDING - LPADDING);
		}
	}

	public int getHeight () {
		return this.height;
	}

	public int getWidth () {
		return this.width;
	}

	protected void setLineColor (int a, int b, int c, int d) {
		lineColor = new Color(a,b,c,d);
	}

	public Color getLineColor () {
		return this.lineColor;
	}


}