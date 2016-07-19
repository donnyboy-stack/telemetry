/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

abstract class AbstractDataSource implements DataSourceInterface {
    protected DataTypeCollectionInterface types;

    public AbstractDataSource () {
        types     = new DataTypeCollection();
    }

    public DataTypeCollectionInterface getTypes () {
        return types;
    }

    protected DataTypeInterface registerDataType (String name, String unit) {
        DataTypeInterface type = new DataType(name, unit);
        types.put(name, type);
        return type;
    }

    protected void putValue (String type, double value) {
        if (types.containsKey(type))
            types.get(type).putValue(value);
    }
}
