/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.util.Random;

class DataCollection implements DataCollectionInterface {
    protected String name;
    protected String unit;

    protected double[] data = new double[MAX_DATA_POINTS];

    protected int numValues = 0;

    public DataCollection () {
        int i;

        Random rand = new Random();

        for (i = 0; i < MAX_DATA_POINTS; i++) {
            putData(200 * (rand.nextDouble() - 0.5));
        }
    }

    public String getName () {
        return name;
    }

    public String getUnits () {
        return unit;
    }

    public double getMostRecent () {
        return data[0];
    }

    public double[] getData () {
        return data;
    }

    public void putData (double value) {
        int i;
        double hold;

        for (i = 0; i < data.length; i++) {
            hold = data[i];
            data[i] = value;
            value = hold;
        }

        numValues++;
    }

    public int count () {
        return numValues;
    }
}
