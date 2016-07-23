/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.io.File;

public interface ProfileLoaderGUIObserverInterface {
    public void loadSaved(File file);

    public void createNew(DataSourceInterface dataSource);

    public void canceled();
}
