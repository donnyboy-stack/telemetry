/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Dimension;

import java.util.List;
import java.util.ArrayList;

public class ProfileLoaderGUI extends JFrame {
    final protected int FRAME_WIDTH  = 450;
    final protected int FRAME_HEIGHT = 300;

    final protected String[] PROFILE_LOAD_OPTIONS = {
        "Create a new profile",
        "Load a saved profile"
    };

    final protected String LOAD_PROFILE_BUTTON_TEXT = "Choose a file";

    protected List<String> dataSourceOptions;

    protected JButton loadProfile;

    public ProfileLoaderGUI (DataSourceCollectionInterface dataSources) {
        /*
         * Set the title of the frame
         */
        setTitle("Choose a Profile");

        /*
         * Set the size of the frame
         */
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        /*
         * Quit the application if this window is closed
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Compile a list of data sources to be presented as options
         */
        loadDataSourceOptions(dataSources);

        /*
         * Add the option to create or load a profile
         */
        addOptionToChooseLoadOrCreate();

        /*
         * Add the options to load a profile
         */
        addOptionsToLoad();

        /*
         * Add the options to create a profile
         */
        addOptionsToCreate();
    }

    public void prompt (ProfileLoaderGUIObserverInterface observer) {
        setVisible(true);
    }

    protected void loadDataSourceOptions (DataSourceCollectionInterface dataSources) {
        dataSourceOptions = new ArrayList<String>();

        for (DataSourceInterface dataSource : dataSources.values())
            dataSourceOptions.add(dataSource.getName());
    }

    protected void addOptionToChooseLoadOrCreate () {
        JComboBox loadOrCreate = new JComboBox<String>(PROFILE_LOAD_OPTIONS);

        add(loadOrCreate);
    }

    protected void addOptionsToLoad () {
        loadProfile = new JButton(LOAD_PROFILE_BUTTON_TEXT);

        add(loadProfile);
    }

    protected void addOptionsToCreate () {

    }

    protected void resetFrame () {
        /*
         * Get rid of all components in the frame
         */
        getContentPane().removeAll();
    }
}
