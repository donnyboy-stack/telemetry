/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 18, 2016
 */

package App.Profile;

import Data.Source.DataSourceInterface;
import Data.Type.DataTypeInterface;

import java.io.File;

public class Profile implements ProfileInterface {
    protected DataSourceInterface dataSource;

    protected File file;

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

    public void updateDataType (DataTypeInterface dataType) {
        changed = true;
    }

    public void setFile (File file) {
        this.file = file;
    }

    public File getFile () {
        return file;
    }

    public boolean hasChanged () {
        return changed;
    }
}
