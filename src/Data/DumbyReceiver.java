/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/

package sunseeker.telemetry;


import java.util.Random;

class DumbyReceiver implements IReceiver{

	public static Random rnd = new Random();
	private String head;
	private double message;

	DumbyReceiver () {
		head = new String();
		message = 0;
	}
	
	public void getData (String data) {
		head = genHead();
		message = genMsg();
	}

	public String getHead () {
		return this.head;
	}

	public Double getMessage () {
		return this.message;
	}

	public static String genHead () {
		String name;

		if(rnd.nextInt() == 0)
			name = "volt";
		else
			name = "amp";
		
		return name;
	}

	public static double genMsg () {
		return rnd.nextDouble();
	}

	public static int genMan (int in) {
		return in;
	}

}