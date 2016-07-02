/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/

package sunseeker.telemetry;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.BorderFactory;

class Panel extends DataInterface {

	Panel (String name) {
		setBorder(BorderFactory.createTitledBorder(name));
	}


}