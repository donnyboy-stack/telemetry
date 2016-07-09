/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import java.util.Arrays;
import java.util.Random;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class PseudoRandomDataSource extends TimerTask implements DataSourceInterface, Runnable {
    protected String[] types = {
        "speed", "voltage", "current", "array"
    };

    protected HashMap<String, DataTypeInterface> collections;

    protected Timer scheduler;

    protected Random randGen;

    protected boolean scheduled;

    public PseudoRandomDataSource (ArrayList<DataTypeInterface> collections) {
        this.collections = new HashMap<String, DataTypeInterface>();

        for (DataTypeInterface collection : collections)
            this.collections.put(collection.getType(), collection);

        scheduler = new Timer();
        randGen = new Random();
    }

    public String[] getTypes () {
        return types;
    }

    public boolean provides (String type) {
        Arrays.sort(types);

        return Arrays.binarySearch(types, type) >= 0;
    }

    public void run () {
        double val;

        for (String type : types) {
            if (!collections.containsKey(type))
                continue;

            val = 500 * ((randGen.nextDouble() * 2) - 1);

            collections.get(type).offer(val);
        }

        if (!scheduled)
            scheduleTask();
    }

    public String getName () {
        return "Pseudo Random Data Source";
    }

    public void stop () {
        scheduler.cancel();
        scheduler.purge();

        scheduled = false;
    }

    protected void scheduleTask () {
        long delay = MainController.LINE_REFRESH_INTERVAL * 2;
        scheduler.scheduleAtFixedRate(this, delay, delay);

        scheduled = true;
    }
}
