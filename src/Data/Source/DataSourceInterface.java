/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 2, 2016
*/

package sunseeker.telemetry;

interface DataSourceInterface {
    public String[] getTypes();

    public boolean provides(String type);
}
