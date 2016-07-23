/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import java.util.Random;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class PseudoRandomDataSource extends AbstractDataSource {
    /*
     * How frequently should new data be generated in milliseconds
     */
    final protected int DELAY = 500;

    /*
     * Data types provided by this source
     */
    final protected String TYPE_SPEED   = "Solar Car Speed";
    final protected String TYPE_VOLTAGE = "Battery Voltage";

    /*
     * Schedules generation of new data
     */
    protected Timer scheduler;

    /*
     * Random number generator
     */
    protected Random randGen;

    /*
     * Have we scheduled the generator?
     */
    protected boolean scheduled;

    public PseudoRandomDataSource () {
        scheduler = new Timer();
        randGen   = new Random();
        scheduled = false;

        registerDataType(TYPE_SPEED, "mph");
        registerDataType(TYPE_VOLTAGE, "amps");
    }

    public String getName () {
        return "Pseudo Random Number Generator";
    }

    public void run () {
        for (String type : types.keySet())
            putValue(type, 150 * ((randGen.nextDouble() * 2) - 1));

        if (!scheduled)
            scheduleTask();
    }

    public void stop () {
        scheduler.cancel();

        /*
         * Necessary so that the same source may be used again
         */
        scheduler.purge();
    }

    protected void scheduleTask () {
        final DataSourceInterface data = this;

        scheduler.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                data.run();
            }
        }, DELAY, DELAY);

        scheduled = true;
    }
}
