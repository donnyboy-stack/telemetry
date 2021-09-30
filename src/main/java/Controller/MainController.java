/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package Controller;

import App.Profile.ProfileInterface;
import App.Profile.Writer.*;
import Data.Type.Collection.DataTypeCollectionInterface;
import Data.Type.DataTypeInterface;
import Frame.EditProfile.*;
import Frame.Main.*;
import Menu.Main.*;
import Menu.Main.Observer.MainMenuObserverInterface;
import Panel.Graph.*;
import Panel.Line.*;
import Panel.LiveData.*;

import javax.swing.Timer;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FileDialog;

import java.io.File;

public class MainController implements ActionListener, MainMenuObserverInterface {
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

        frame    = new MainFrame();
        graph    = new GraphPanel();
        liveData = new LiveDataPanel();

        menu.addObserver(this);

        frame.useMenu(menu);
        frame.useGraphPanel(graph);
        frame.useLiveDataPanel(liveData);

        timer = new Timer(REFRESH_DELAY, this);
    }

    public void start (ProfileInterface profile) {
        this.profile = profile;

        dataTypes = profile.getDataSource().getTypes();

        // Draws to screen the data from source, with line graphs
        // This uses classes from Frame and Panel folders.
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
        AbstractEditProfileFrame editProfile = new EditProfileFrame(profile, graph);

        editProfile.showFrame();
    }

    protected void loadLinePanels () {
        AbstractLinePanel[] lines = new AbstractLinePanel[dataTypes.size()];

        int index = 0;

        for (DataTypeInterface type : dataTypes.values())
            lines[index++] = new LinePanel(type, graph);

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
