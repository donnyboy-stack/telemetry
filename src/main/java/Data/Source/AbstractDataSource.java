/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package Data.Source;

import Data.Type.Collection.*;
import Data.Type.*;

abstract class AbstractDataSource implements DataSourceInterface {
    protected DataTypeCollectionInterface types;

    public AbstractDataSource () {
        types = new DataTypeCollection();
    }

    public DataTypeCollectionInterface getTypes () {
        return types;
    }

    protected DataTypeInterface registerDataType (String id, String name, String unit) {
        DataTypeInterface type = new DataType(name, unit);
        types.put(id, type);
        return type;
    }

    protected DataTypeInterface registerDataType (String name, String unit) {
        return registerDataType(name, name, unit);
    }

    protected void putValue (String type, double value) {
        if (types.containsKey(type))
            types.get(type).putValue(value);
    }
}
