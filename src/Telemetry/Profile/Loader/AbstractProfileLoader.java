/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractProfileLoader implements ProfileLoaderInterface {
    protected DataSourceCollectionInterface dataSources;

    protected List<String> unavailableDataTypes;

    protected DataSourceInterface dataSource;

    public AbstractProfileLoader (DataSourceCollectionInterface dataSources) {
        this.dataSources = dataSources;

        unavailableDataTypes = new ArrayList<String>();
    }

    protected void reset () {
        unavailableDataTypes.clear();

        dataSource = null;
    }

    protected boolean processLine (String line) {
        String[] parts = line.split(",");

        /*
         * Ignore empty lines
         */
        if (parts.length <= 0)
            return true;

        switch (parts[0]) {
            case ProfileLoaderInterface.FIELD_DATA_SOURCE:
                if (parts.length == 2)
                    return loadDataSource(parts[1]);
            case ProfileLoaderInterface.FIELD_DATA_TYPE:
                if (parts.length == 5)
                    return loadDataType();
        }

        return true;
    }

    protected ProfileInterface buildProfile () {
        if (dataSource != null)
            return new Profile(dataSource);

        return null;
    }

    private boolean loadDataSource (String name) {
        if (dataSources.containsKey(name)) {
            dataSource = dataSources.get(name);
            return true;
        }

        return false;
    }

    private boolean loadDataType () {
        return true;
    }
}
