/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FileDialog;

import java.io.File;

class MainController implements ActionListener, MainMenuObserverInterface {
    /*
     * How frequently should the panels be refreshed?
     */
    final public static int REFRESH_DELAY = 250;

    /*
     * Title of the file chooser dialog
     */
    final protected String FILE_DIALOG_TITLE = "Choose where to save to";

    /*
     * Error messages and titles
     */
    final protected String ERROR_TITLE = "Error!";

    final protected String ERROR_CANNOT_SAVE = "Cannot save to that location, please choose another.";

    /*
     * The current profile
     */
    protected ProfileInterface profile;

    /*
     * The main frame
     */
    protected AbstractMainFrame frame;

    /*
     * The graph panel
     */
    protected AbstractGraphPanel graph;

    /*
     * The live data panel
     */
    protected AbstractLiveDataPanel liveData;

    /*
     * The data types being used
     */
    protected DataTypeCollectionInterface dataTypes;

    /*
     * Timer used to update the various panes
     */
    protected Timer timer;

    public MainController () {
        AbstractMainMenu menu = new MainMenu();

        frame      = new MainFrame();
        graph      = new GraphPanel();
        liveData   = new LiveDataPanel();

        menu.addObserver(this);

        frame.useMenu(menu);
        frame.useGraphPanel(graph);
        frame.useLiveDataPanel(liveData);

        timer = new Timer(REFRESH_DELAY, this);
    }

    public void start (ProfileInterface profile) {
        this.profile = profile;

        dataTypes = profile.getDataSource().getTypes();

        loadLinePanels();

        liveData.setTypes(dataTypes);

        frame.showFrame();

        timer.start();
    }

    public void actionPerformed (ActionEvent e) {
        graph.repaint();
        liveData.refresh();
    }

    public void doSaveProfile () {
        if (profile != null) {
            ProfileWriterInterface writer = new ProfileWriter();

            if (promptForProfileSaveLocation()) {
                while (!writer.writeProfile(profile)) {
                    if (profile.getFile() != null) {
                        JOptionPane.showMessageDialog(frame,
                            ERROR_CANNOT_SAVE,
                            ERROR_TITLE,
                            JOptionPane.WARNING_MESSAGE);
                    }

                    if (!promptForProfileSaveLocation())
                        break;
                }
            }
        }
    }

    public void doEditProfile () {
        System.out.println("Edit profile");
    }

    protected void loadLinePanels () {
        AbstractLinePanel[] lines = new AbstractLinePanel[dataTypes.size()];

        int index = 0;

        for (DataTypeInterface type : dataTypes.values())
            lines[index++] = new LinePanel(type);

        frame.useLinePanels(lines);
    }

    protected boolean promptForProfileSaveLocation () {
        FileDialog fileChooser = new FileDialog(frame, FILE_DIALOG_TITLE, FileDialog.SAVE);

        fileChooser.setVisible(true);

        File[] files = fileChooser.getFiles();

        if (files.length > 0) {
            profile.setFile(files[0]);
            return true;
        }

        return false;
    }
}
