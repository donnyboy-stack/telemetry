/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;

public class ProfileLoaderGUI extends JFrame {
    final protected String[] PROFILE_LOAD_OPTIONS = {
        "Create a new profile",
        "Load a saved profile"
    };

    protected List<String> dataSourceOptions;

    public ProfileLoaderGUI (DataSourceCollectionInterface dataSources) {
        /*
         * Set the title of the frame
         */
        setTitle("Choose a Profile");

        /*
         * Compile a list of data sources to be presented as options
         */
        loadDataSourceOptions(dataSources);

        /*
         * Preset the option to create or load
         */
        presentOptionsToLoadOrCreate();
    }

    public void prompt () {
        
    }

    protected void loadDataSourceOptions (DataSourceCollectionInterface dataSources) {
        dataSourceOptions = new ArrayList<String>();

        for (DataSourceInterface dataSource : dataSources.values())
            dataSourceOptions.add(dataSource.getName());
    }

    protected void presentOptionsToLoadOrCreate () {

    }

    protected void presentOptionsToLoad () {

    }

    protected void presentOptionsToCreate () {

    }

    protected void resetFrame () {
        /*
         * Get rid of all components in the frame
         */
        getContentPane().removeAll();
    }
}
