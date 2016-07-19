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
    protected DataSourceCollectionInterface dataSources;

    protected DataSourceInterface dataSource;

    protected Thread dataThread;

    public DataController () {
        dataSources = new DataSourceCollection();

        /*
         * Register the known data source types
         */
        registerDataSource(new PseudoRandomDataSource());
        registerDataSource(new TenCarDataSource());
        registerDataSource(new SixteenCarDataSource());
    }

    public void start (DataSourceInterface source) {
        this.dataSource = source;

        /*
         * If the data source requires a seial connection,
         * lets get it one
         */
        if (dataSource instanceof AbstractSerialDataSource)
            promptForSerialPort();

        /*
         * Start loading the data
         */
        dataThread = new Thread(dataSource, "DataSourceThread");

        dataThread.start();
    }

    public void stop () {
        /*
         * We can only stop if a data source has been set
         */
        if (dataSource == null)
            return;

        try {
            dataSource.stop();
            dataThread.join();
        } catch (InterruptedException e) {
            System.out.println("Could not stop the data source thread...");
        }

        dataSource = null;
    }

    public void restart () {
        DataSourceInterface source = dataSource;

        stop();
        start(source);
    }

    public DataSourceCollectionInterface getDataSources () {
        return dataSources;
    }

    protected void registerDataSource (DataSourceInterface source) {
        dataSources.put(source.getName(), source);
    }

    protected void promptForSerialPort () {
        SerialHelper helper = new SerialHelper();

        String port = (String) JOptionPane.showInputDialog(
            null,
            "Choose which serial port you would like to connect to:",
            "Choose a Serial Port",
            JOptionPane.PLAIN_MESSAGE,
            null,
            helper.getPortNames(),
            null
        );

        ((AbstractSerialDataSource) dataSource).setPort(helper.getIdentifier(port));
    }
}