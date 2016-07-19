/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public class InitialProfileLoader extends ProfileLoader {
    protected DataSourceCollectionInterface dataSources;

    public InitialProfileLoader (DataSourceCollectionInterface dataSources) {
        this.dataSources = dataSources;
    }

    public void loadProfile (ProfileLoaderObserverInterface observer) {
        ProfileLoaderGUI profileLoaderGui = new ProfileLoaderGUI(dataSources);

        profileLoaderGui.prompt();
    }
}
