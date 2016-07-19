/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 18, 2016
 */

package sunseeker.telemetry;

public class Profile implements ProfileInterface {
    protected DataSourceInterface dataSource;

    public DataSourceInterface getDataSource () {
        return dataSource;
    }
}
