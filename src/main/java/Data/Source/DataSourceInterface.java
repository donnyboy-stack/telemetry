/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Data.Source;

import Data.Type.Collection.DataTypeCollectionInterface;

public interface DataSourceInterface extends Runnable {
    public String getName();

    public DataTypeCollectionInterface getTypes();

    public void stop();
}
