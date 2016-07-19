/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

public class ProfileLoader implements ProfileLoaderInterface {
    /*
     * Each config line is separated by this
     */
    final protected String LINE_DELIMITER = "\n";

    /*
     * This will tell the loader which data source to use
     */
    final protected String FIELD_DATA_SOURCE = "use_source";

    /*
     * This will tell the loader how to configure a data type
     */
    final protected String FIELD_DATA_TYPE = "use_type";

    protected List<String> unavailableDataTypes;

    public ProfileLoader () {
        unavailableDataTypes = new ArrayList<String>();
    }

    public ProfileInterface loadProfile (String data) {
        unavailableDataTypes.clear();

        // Hard coding data source just for now
        return new Profile(new PseudoRandomDataSource());
    }
}
