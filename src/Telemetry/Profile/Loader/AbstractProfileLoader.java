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
    protected List<String> unavailableDataTypes;

    public AbstractProfileLoader () {
        unavailableDataTypes = new ArrayList<String>();
    }
}
