/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

abstract class AbstractSerialDataSource extends AbstractDataSource {
    public AbstractSerialDataSource (AbstractDataTypeCollection types) {
        super(types);
    }
}
