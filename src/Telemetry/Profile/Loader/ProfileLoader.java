/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

public class ProfileLoader implements ProfileLoaderInterface {
    public ProfileInterface loadProfile (String data) {
        return new Profile();
    }
}
