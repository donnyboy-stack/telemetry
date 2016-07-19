/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public class InitialProfileLoader extends ProfileLoader implements ProfileLoaderGUIObserverInterface {
    protected DataSourceCollectionInterface dataSources;

    protected ProfileLoaderObserverInterface observer;

    public InitialProfileLoader (DataSourceCollectionInterface dataSources) {
        this.dataSources = dataSources;
    }

    public void loadProfile (ProfileLoaderObserverInterface observer) {
        ProfileLoaderGUI profileLoaderGui = new ProfileLoaderGUI(dataSources);

        this.observer = observer;

        profileLoaderGui.prompt(this);
    }

    public void loadSaved (String fileName) {

    }

    public void createNew (DataSourceInterface dataSource) {
        observer.receiveProfile(new Profile(dataSource));
    }
}
