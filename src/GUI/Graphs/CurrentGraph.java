/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/

package sunseeker.telemetry;


import java.util.ArrayList;
import java.util.List;

class CurrentGraph extends GraphInterface{

	private static DumbyReceiver data;

	//private List<Double> points;

	CurrentGraph () {
		data = new DumbyReceiver();
	}

	public void getPoint () {
		if(data.getHead().equals("amp"))
			this.addPoint(data.getMessage());
	}
}