/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 8, 2016
*/

package sunseeker.telemetry;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Thread;
import javax.swing.JOptionPane;

class DataController {
    protected ArrayList<DataCollectionInterface<Double>> dataCollections;

    protected HashMap<String, DataSourceInterface> dataSources;

    protected DataSourceInterface dataSource;

    protected Thread dataThread;

    public DataController (ArrayList<DataCollectionInterface<Double>> collections) {
        dataCollections = collections;

        dataSources = new HashMap<String, DataSourceInterface>();

        /*
         * Register the known data source types
         */
        registerDataSource(new PseudoRandomDataSource(dataCollections));
    }

    public void start () {
        if (dataSource == null)
            return;        

        /*
         * Start loading the data
         */
        dataThread = new Thread(dataSource, "DataSourceThread");

        dataThread.start();
    }

    public void stop () {
        try {
            dataSource.stop();
            dataThread.join();
        } catch (InterruptedException e) {
            System.out.println("Could not stop the data source thread...");
        }
    }

    public void restart () {
        stop();
        start();
    }

    public void promptForDataSource (JFrame parent) {
        String source = (String) JOptionPane.showInputDialog(
            parent,
            "Choose a source for the data:",
            "Choose a Data Source",
            JOptionPane.PLAIN_MESSAGE,
            null,
            dataSources.keySet().toArray(),
            dataSource
        );

        dataSource = dataSources.get(source);
    }

    protected void registerDataSource (DataSourceInterface source) {
        dataSources.put(source.getName(), source);
    }
}