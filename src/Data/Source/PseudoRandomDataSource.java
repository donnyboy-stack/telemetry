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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class PseudoRandomDataSource extends TimerTask implements DataSourceInterface, Runnable {
    protected String[] providedTypes = {
        "speed", "voltage", "current", "array"
    };

    protected HashMap<String, DataTypeInterface> types;

    protected Timer scheduler;

    protected Random randGen;

    protected boolean scheduled;

    public PseudoRandomDataSource (AbstractDataTypeCollection dataTypes) {
        types     = new HashMap<String, DataTypeInterface>();
        scheduler = new Timer();
        randGen   = new Random();

        for (DataTypeInterface type : dataTypes)
            types.put(type.getType(), type);
    }

    public String getName () {
        return "Pseudo Random Data Source";
    }

    public String[] getTypes () {
        return providedTypes;
    }

    public boolean provides (String type) {
        Arrays.sort(providedTypes);

        return Arrays.binarySearch(providedTypes, type) >= 0;
    }

    public void run () {
        List<Double> data;
        double val;

        for (String type : providedTypes) {
            if (!types.containsKey(type))
                continue;

            data = types.get(type).getData();

            val = 500 * ((randGen.nextDouble() * 2) - 1);

            data.clear();
            data.add(val);
        }

        if (!scheduled)
            scheduleTask();
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
