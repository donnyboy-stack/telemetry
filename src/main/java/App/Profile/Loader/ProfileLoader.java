/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App.Profile.Loader;

import App.Profile.ProfileInterface;
import Data.Source.Collection.DataSourceCollectionInterface;

import java.util.List;
import java.util.ArrayList;

public class ProfileLoader extends AbstractProfileLoader {
    public ProfileLoader (DataSourceCollectionInterface dataSources) {
        super(dataSources);
    }
    
    public ProfileInterface loadProfile (String data) {
        reset();

        // Hard coding data source just for now
        return buildProfile();
    }
}
