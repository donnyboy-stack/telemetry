/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.io.File;

public class FileProfileLoader extends AbstractProfileLoader {
    public ProfileInterface loadProfile (String fileName) {
        return loadProfile(new File(fileName));
    }

    public ProfileInterface loadProfile (File file) {
        unavailableDataTypes.clear();
        
        return new Profile(new PseudoRandomDataSource());
    }
}
