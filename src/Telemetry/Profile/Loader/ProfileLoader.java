/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public class ProfileLoader implements ProfileLoaderInterface {
    public ProfileInterface loadProfile (String data) {
        // Hard coding data source just for now
        return new Profile(new PseudoRandomDataSource());
    }
}
