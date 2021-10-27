package Frame.Error;

import Panel.Error.AbstractErrorPanel;
import Panel.Graph.AbstractGraphPanel;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends AbstractErrorFrame{

    // Use a errorDataPanel here?
    protected AbstractErrorPanel errorPanel;

    protected Container contentPane;
    protected JLayeredPane layeredPane;

    protected int depth = 1;

    public ErrorFrame(){
        setTitle(FRAME_TITLE);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        setResizable(false);

        contentPane = getContentPane();

        // Set a layout
        layeredPane = new JLayeredPane();

        contentPane.add(layeredPane);
    }

    public void useErrorPanel(AbstractErrorPanel panel){
        /*
         * Remove the existing panel
         */
        if (errorPanel != null)
            layeredPane.remove(
                    layeredPane.getIndexOf((Component) errorPanel)
            );


        errorPanel = panel;

        /*
         * Set the size of the graph
         */
        errorPanel.setBounds(
                0, 0,
                FRAME_WIDTH,
                FRAME_HEIGHT
        );

        /*
         * Add the panel to the view
         */
        layeredPane.add(errorPanel, new Integer(depth++));
    }
}
