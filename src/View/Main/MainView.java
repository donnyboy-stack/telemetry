/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;

class MainView extends AbstractMainView {
    protected final int VIEW_WIDTH   = 1000;
    protected final int VIEW_HEIGHT  = 700;
    protected final int GRAPH_HEIGHT = 400;

    protected final int PADDING      = 10;
    protected final int AXIS_PADDING = PADDING * 2;

    protected SpringLayout layout;

    protected Container contentPane;
    protected JLayeredPane layeredPane;

    protected AbstractGraphPanel graphPanel;
    protected AbstractDataSelectPanel dataSelectPanel;
    protected AbstractLiveDataPanel liveDataPanel;

    protected int depth = 1;

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
         * The minimum size of the window
         */
        setSize(new Dimension(VIEW_WIDTH, VIEW_HEIGHT));

        /*
         * This is a fixed size window
         */
        setResizable(false);

        /*
         * Setup the layout
         */
        layout = new SpringLayout();
        setLayout(layout);

        /*
         * Just some other things...
         */
        contentPane = getContentPane();

        configureLayeredPane();
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
            layeredPane.remove(
                layeredPane.getIndexOf((Component) graphPanel)
            );

        /*
         * Set the size of the graph
         */
        // panel.setSize(
        //     VIEW_WIDTH - AXIS_PADDING,
        //     GRAPH_HEIGHT - AXIS_PADDING
        // );

        panel.setBounds(
            0, 0,
            VIEW_WIDTH - AXIS_PADDING,
            GRAPH_HEIGHT - AXIS_PADDING
        );

        /*
         * Add the panel to the view
         */
        layeredPane.add(panel, depth++);
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

    public void useLinePanels (AbstractLinePanel[] panels) {
        for (AbstractLinePanel panel : panels) {
            // panel.setSize(
            //     VIEW_WIDTH - AXIS_PADDING,
            //     GRAPH_HEIGHT - AXIS_PADDING
            // );

            panel.setBounds(
                AbstractGraphPanel.FULL_INSET, 0,
                VIEW_WIDTH - AXIS_PADDING,
                (GRAPH_HEIGHT - AXIS_PADDING) - AbstractGraphPanel.FULL_INSET
            );

            layeredPane.add(panel, depth++);
        }
    }

    protected void configureLayeredPane () {
        layeredPane = new JLayeredPane();

        add(layeredPane);

        /*
         * The layeredPane will appear "PADDING" pixels from the
         * top side of the view
         */
        layout.putConstraint(
            SpringLayout.NORTH, layeredPane,
            PADDING,
            SpringLayout.NORTH, contentPane
        );

        /*
         * The layeredPane will appear 500 pixels down the view
         */
        layout.putConstraint(
            SpringLayout.SOUTH, layeredPane,
            GRAPH_HEIGHT,
            SpringLayout.NORTH, contentPane
        );

        /*
         * The layeredPane will appear "PADDING" pixels from the
         * left side of the view
         */
        layout.putConstraint(
            SpringLayout.WEST, layeredPane,
            PADDING,
            SpringLayout.WEST, contentPane
        );

        /*
         * The layeredPane will appear "PADDING" pixels from the
         * right side of the view
         */
        layout.putConstraint(
            SpringLayout.EAST, contentPane,
            PADDING,
            SpringLayout.EAST, layeredPane
        );
    }
}