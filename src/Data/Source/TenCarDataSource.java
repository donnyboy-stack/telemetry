/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

class TenCarDataSource extends AbstractSerialDataSource {
    public TenCarDataSource (AbstractDataTypeCollection types) {
        super(types);

        providedTypes = new String[] {
            "speed"
        };
    }

    public String getName () {
        return "2010 Car Data Source";
    }

    public void stop () {

    }

    public void pause () {

    }

    public void run () {
        
    }
}
