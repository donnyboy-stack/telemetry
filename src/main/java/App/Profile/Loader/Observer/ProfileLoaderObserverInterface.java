/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App.Profile.Loader.Observer;

import App.Profile.ProfileInterface;

public interface ProfileLoaderObserverInterface {
    public void receiveProfile(ProfileInterface profile);
}
