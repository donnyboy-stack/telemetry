/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

interface DataCollectionInterface {
    final public static int MAX_DATA_POINTS = 50;
    
    public String getName();

    public String getUnits();

    public double getMostRecent();

    public double[] getData();

    public void putData(double value);

    public int count();
}
