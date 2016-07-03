/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

import java.util.HashMap;

class DataSubscriber implements DataSubscriberInterface {
    protected HashMap<String, DataCollectionInterface> channels;

    public DataSubscriber () {
        channels = new HashMap<String, DataCollectionInterface>();
    }

    public void subscribe (DataCollectionInterface collection) {
        channels.put(collection.getType(), collection);
    }

    public void broadcast (String type, double value) {
        if (channels.containsKey(type))
            channels.get(type).putData(value);
    }
}
