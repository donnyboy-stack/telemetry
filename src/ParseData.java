/**
* Sunseeker Telemety
*
* Telemetry 2016
*
* @author Kai Gray <kai.a.gray@wmich.edu>
*/


package sunseeker.telemetry;


class ParseData {

	private IReceiver receiver;
	//telemetry values
	private String head;
	private String message;

	private double batTemp;
	private double motor1Temp;
	private double motor2Temp;
	private double voltage;
	private double busCurrent;
	private double speed;

	private double avgVolt;
	private double avgCurrent;


	ParseData () {
		this.receiverMode(2);
	}

	ParseData (int m) {
		this.receiverMode(m);
	}

	//reciever mode select
	public void receiverMode (int type) {
		switch(type) {
			case 0:
				receiver = new DefaultReceiver();
				break;
			case 1:
				receiver = new BatteryReceiver();
				break;
			case 2:
				receiver = new DumbyReceiver();
				break;
		}
	}

	


	public String getHead () {
		return this.head;
	}

	public String getMessage () {
		return this.message;
	}

	public double getVoltage () {
		return this.voltage;
	}

	public double getCurrent () {
		return this.busCurrent;
	}

	public double getBatTemp () {
		return this.batTemp;
	}

	public double getMotor1Temp () {
		return this.motor1Temp;
	}

	public double getMotor2Temp () {
		return this.motor2Temp;
	}

	public double getSpeed () {
		return this.speed;
	}

	private void setVolatage (double volt) {
		this.voltage = volt;
	}

	private void setCurrent (double bus) {
		this.busCurrent = bus;
	}

	private void setBatTemp (double batTemp) {
		this.batTemp = batTemp;
	}

	private void setMotor1Temp (double motorTemp) {
		this.motor1Temp = motorTemp;
	}

	private void setMotor2Temp (double motorTemp) {
		this.motor2Temp = motorTemp;
	}

	private void setSpeed (double speed) {
		this.speed = speed;
	}
}