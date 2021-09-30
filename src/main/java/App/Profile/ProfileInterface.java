/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 18, 2016
 */

package sunseeker.telemetry;

import java.io.File;

public interface ProfileInterface {
    public DataSourceInterface getDataSource();

    public void updateDataType(DataTypeInterface dataType);

    public void setFile(File file);

    public File getFile();

    public boolean hasChanged();
}
