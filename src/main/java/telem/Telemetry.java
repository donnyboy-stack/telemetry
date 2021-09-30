/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

// Previous devs put everything in one package. There is no good solution to fix, without hours of work :(
package sunseeker.telemetry;

import java.awt.EventQueue;

class Telemetry {
    public static void main (String[] args) {
        // Creates and shows the GUI
        // Application implements runnable, and has a run() function, which is called by invokeLater()
        EventQueue.invokeLater(new Application());
	}
}
