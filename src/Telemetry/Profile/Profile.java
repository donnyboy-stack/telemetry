/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 18, 2016
 */

package sunseeker.telemetry;

public class Profile implements ProfileInterface {
    protected DataTypeCollectionInterface dataTypes;

    public Profile () {
        dataTypes = new DataTypeCollection();
    }

    public DataTypeCollectionInterface getDataTypes () {
        return dataTypes;
    }
}
