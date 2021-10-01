/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App.Profile.Loader;

import App.Profile.Profile;
import App.Profile.ProfileInterface;
import Data.Source.Collection.DataSourceCollectionInterface;
import Data.Source.DataSourceInterface;
import Data.Type.Collection.DataTypeCollectionInterface;
import Data.Type.DataTypeInterface;

import java.util.List;
import java.util.ArrayList;

import java.awt.Color;

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
                if (parts.length == 6)
                    return loadDataType(parts[1], parts[2], parts[3], parts[4], parts[5]);
        }

        return true;
    }

    protected ProfileInterface buildProfile () {
        if (dataSource != null){
            return new Profile(dataSource);
        }

        return null;
    }

    private boolean loadDataSource (String name) {
        if (dataSources.containsKey(name)) {
            dataSource = dataSources.get(name);
            return true;
        }

        return false;
    }

    private boolean loadDataType (String id, String name, String units, String color, String enabled) {
        if (dataSource != null) {
            DataTypeCollectionInterface types = dataSource.getTypes();

            if (types.containsKey(id)) {
                DataTypeInterface type = types.get(id);

                type.setDisplayName(name);
                type.setDisplayUnits(units);
                type.setColor(new Color(Integer.parseInt(color)));
                System.out.println("Enabled? "+ Boolean.parseBoolean(enabled));
                type.setEnabled(Boolean.parseBoolean(enabled));

                return true;
            }
        }

        return false;
    }
}
