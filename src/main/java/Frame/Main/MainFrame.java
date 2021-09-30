/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Frame.Main;

import Menu.Main.AbstractMainMenu;
import Panel.Graph.AbstractGraphPanel;
import Panel.Line.AbstractLinePanel;
import Panel.LiveData.AbstractLiveDataPanel;

import javax.swing.SpringLayout;
import javax.swing.JLayeredPane;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;

public class MainFrame extends AbstractMainFrame {
    protected SpringLayout layout;

    protected Container contentPane;
    protected JLayeredPane layeredPane;

    protected AbstractGraphPanel graphPanel;
    protected AbstractLiveDataPanel liveDataPanel;

    protected AbstractLinePanel[] linePanels;

    protected int depth = 1;

    public MainFrame () {
        setTitle(FRAME_TITLE);

        /*
         * Only need to build once
         */
        if (layout != null)
            return;

        /*
         * The app should not quit when this view is closed
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * The minimum size of the window
         */
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

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

    public void showFrame () {
        /*
         * When the window is opened draw the graph
         */
        if (graphPanel != null)
            graphPanel.repaint();

        super.showFrame();
    }

    public void useMenu (AbstractMainMenu menu) {
        setJMenuBar(menu);
    }

    public void useGraphPanel (AbstractGraphPanel panel) {
        /*
         * Remove the existing panel
         */
        if (graphPanel != null)
            layeredPane.remove(
                layeredPane.getIndexOf((Component) graphPanel)
            );

        graphPanel = panel;

        /*
         * Set the size of the graph
         */
        graphPanel.setBounds(
            0, 0,
            FRAME_WIDTH,
            AbstractGraphPanel.PANEL_HEIGHT
        );

        /*
         * Add the panel to the view
         */
        layeredPane.add(graphPanel, new Integer(depth++));
    }

    public void useLiveDataPanel (AbstractLiveDataPanel panel) {
        /*
         * Remove the existing panel
         */
        if (liveDataPanel != null)
            layeredPane.remove(
                layeredPane.getIndexOf((Component) liveDataPanel)
            );

        liveDataPanel = panel;

        /*
         * Set the size and position of the panel
         */
        int posX  = FRAME_WIDTH - AXIS_PADDING;
        int posY  = AbstractGraphPanel.PANEL_HEIGHT + PADDING;

        panel.setBounds(
            0, posY,
            FRAME_WIDTH - AXIS_PADDING,
            FRAME_HEIGHT - posY - (PADDING * 7)
        );

        /*
         * Add the panel to the view
         */
        layeredPane.add(liveDataPanel, new Integer(depth++));
    }

    public void useLinePanels (AbstractLinePanel[] panels) {
        removeLinePanels();

        for (AbstractLinePanel panel : panels) {
            panel.setBounds(
                AbstractGraphPanel.AXIS_INSET, 0,
                FRAME_WIDTH - AbstractGraphPanel.FULL_INSET,
                AbstractGraphPanel.PANEL_HEIGHT
            );

            layeredPane.add(panel, new Integer(depth++));
        }
    }

    protected void removeLinePanels () {
        if (linePanels == null)
            return;

        for (AbstractLinePanel panel : linePanels)
            layeredPane.remove(panel);

        depth -= linePanels.length;
    }

    protected void configureLayeredPane () {
        layeredPane = new JLayeredPane();

        contentPane.add(layeredPane);

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
            SpringLayout.SOUTH, contentPane,
            PADDING,
            SpringLayout.SOUTH, layeredPane
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