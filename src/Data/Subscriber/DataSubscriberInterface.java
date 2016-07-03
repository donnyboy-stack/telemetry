/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

interface DataSubscriberInterface {
    public void subscribe(DataCollectionInterface collection);

    public void broadcast(String type, double value);
}
