/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 18, 2016
 */

package sunseeker.telemetry;

public class Profile implements ProfileInterface {
    protected DataSourceInterface dataSource;

    protected boolean changed = false;

    public Profile (DataSourceInterface dataSource, boolean fromFile) {
        this.dataSource = dataSource;
        changed         = !fromFile;
    }

    public Profile (DataSourceInterface dataSource) {
        this(dataSource, true);
    }

    public DataSourceInterface getDataSource () {
        return dataSource;
    }

    public boolean hasChanged () {
        return changed;
    }
}
