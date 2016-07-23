/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public interface ProfileLoaderInterface {
    /*
     * Each config line is separated by this
     */
    final public String LINE_DELIMITER = "\n";

    /*
     * This will tell the loader which data source to use
     */
    final public String FIELD_DATA_SOURCE = "use_source";

    /*
     * This will tell the loader how to configure a data type
     */
    final public String FIELD_DATA_TYPE = "use_type";

    public ProfileInterface loadProfile(String data);
}
