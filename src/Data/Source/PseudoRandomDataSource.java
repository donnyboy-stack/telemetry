/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.util.Arrays;
import java.util.Random;
import java.lang.Runnable;

class PseudoRandomDataSource implements DataSourceInterface, Runnable {
    protected String[] types = {
        "speed"
    };

    protected DataSubscriberInterface subscriber;

    public PseudoRandomDataSource (DataSubscriberInterface subscriber) {
        this.subscriber = subscriber;
    }

    public String[] getTypes () {
        return types;
    }

    public boolean provides (String type) {
        Arrays.sort(types);

        return Arrays.binarySearch(types, type) >= 0;
    }

    public void run () {
        Random rand = new Random();

        while (true) {
            double val = 200 * ((rand.nextDouble() * 2) - 1);

            subscriber.broadcast("speed", val);

            wait(1);
        }
    }

    private void wait(int m) {
        long x = 0;
        int i = 0;
        while (i++ < m) {
            while(x < 1000000000) { x++; }
            x = 0;
        }
    }
}
