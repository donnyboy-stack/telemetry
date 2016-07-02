/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.Container;

class MainView extends AbstractMainView {
    protected final int VIEW_WIDTH   = 1000;
    protected final int VIEW_HEIGHT  = 700;
    protected final int GRAPH_HEIGHT = 400;

    protected final int PADDING = 10;

    protected SpringLayout layout;

    protected Container contentPane;

    protected AbstractGraphPanel graphPanel;
    protected AbstractDataSelectPanel dataSelectPanel;
    protected AbstractLiveDataPanel liveDataPanel;

    public MainView () {
        /*
         * Only need to build once
         */
        if (layout != null) return;

        /*
         * The app should not quit when this view is closed
         */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /*
         * The view starts out in full screen
         */
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*
         * The minimum size of the window
         */
        setMinimumSize(new Dimension(VIEW_WIDTH, VIEW_HEIGHT));

        /*
         * Setup the layout
         */
        layout = new SpringLayout();
        setLayout(layout);

        /*
         * Just some other things...
         */
        contentPane = getContentPane();
    }

    public void showView () {
        /*
         * When the window is opened draw the graph
         */
        if (graphPanel != null)
            graphPanel.repaint();

        super.showView();
    }

    public void useGraphPanel (AbstractGraphPanel panel) {
        /*
         * Remove the existing panel
         */
        if (graphPanel != null)
            remove(graphPanel);

        /*
         * Add the panel to the view
         */
        add(graphPanel = panel);

        /*
         * The graph will appear "PADDING" pixels from the
         * top side of the view
         */
        layout.putConstraint(
            SpringLayout.NORTH, panel,
            PADDING,
            SpringLayout.NORTH, contentPane
        );

        /*
         * The graph will appear 500 pixels down the view
         */
        layout.putConstraint(
            SpringLayout.SOUTH, panel,
            GRAPH_HEIGHT,
            SpringLayout.NORTH, contentPane
        );

        /*
         * The graph will appear "PADDING" pixels from the
         * left side of the view
         */
        layout.putConstraint(
            SpringLayout.WEST, panel,
            PADDING,
            SpringLayout.WEST, contentPane
        );

        /*
         * The graph will appear "PADDING" pixels from the
         * right side of the view
         */
        layout.putConstraint(
            SpringLayout.EAST, contentPane,
            PADDING,
            SpringLayout.EAST, panel
        );
    }

    public void useDataSelectPanel (AbstractDataSelectPanel panel) {
        /*
         * Remove the existing panel
         */
        if (dataSelectPanel != null)
            remove(dataSelectPanel);

        /*
         * Add the panel to the view
         */
        add(dataSelectPanel = panel);
    }

    public void useLiveDataPanel (AbstractLiveDataPanel panel) {
        /*
         * Remove the existing panel
         */
        if (liveDataPanel != null)
            remove(liveDataPanel);

        /*
         * Add the panel to the view
         */
        add(liveDataPanel = panel);
    }
}