/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public interface ProfileLoaderGUIObserverInterface {
    public void loadSaved(String fileName);

    public void createNew(DataSourceInterface dataSource);

    public void canceled();
}
