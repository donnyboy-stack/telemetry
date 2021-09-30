/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.util.HashMap;
import java.util.Enumeration;
import java.io.IOException;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;

class SerialHelper {
    protected static HashMap<String, CommPortIdentifier> ports;

    public SerialHelper () {
        if (ports == null)
            ports = new HashMap<String, CommPortIdentifier>();
    }

    public HashMap getPorts () {
        // This is where we get all the ports on the system
        Enumeration allPorts = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier p;

        ports.clear();

        while (allPorts.hasMoreElements()) {
            // Here is where we go to get names of the serial ports and display them.
            // I think the modem should show up on the list when we plug it in.
            p = (CommPortIdentifier) allPorts.nextElement();
            System.out.println(p.getName());

            if (p.getPortType() == CommPortIdentifier.PORT_SERIAL)
                ports.put(p.getName(), p);
        }

        return ports;
    }

    public Object[] getPortNames () {
        return getPorts().keySet().toArray();
    }

    public CommPortIdentifier getIdentifier (String port) {
        try {
            return CommPortIdentifier.getPortIdentifier(port);
        } catch (NoSuchPortException e) {
            return null;
        }
    }
}