package nilswildt.de;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class Brachialbiber {
	protected EV3ColorSensor colSensor;
	private NXTMotor eiMotor;
	private EV3GyroSensor gyro;
	protected GyroController gyCo;
	protected LineFollower lineFollower;
	private EV3LargeRegulatedMotor leftMotor;
	private EV3LargeRegulatedMotor rightMotor;
	private EV3TouchSensor touchSensor;

	private static int SCREENWIDTH = 17;
	private static int SCREENHIGHT = 7;

	/**
	 * Contructor
	 * 
	 * @param colSenPort
	 *            Port of the color sensor
	 * @param b
	 * @param gyroSenPort
	 */
	public Brachialbiber(Port colSensPort, Port gyroSensPort, Port touchSensorPort ,Port NXTSensPort,
			Port leftMotorPort, Port rightMotorPort) {
		colSensor = new EV3ColorSensor(colSensPort);
		gyro = new EV3GyroSensor(gyroSensPort);
		touchSensor = new EV3TouchSensor(touchSensorPort);
		eiMotor = new NXTMotor(NXTSensPort);
		gyCo = new GyroController(gyro, touchSensor, eiMotor);
		leftMotor = new EV3LargeRegulatedMotor(leftMotorPort);
		rightMotor = new EV3LargeRegulatedMotor(rightMotorPort);
		lineFollower = new LineFollower(colSensor, touchSensor, leftMotor, rightMotor);
		//System.out.println(Math.max(leftMotor.getMaxSpeed(), rightMotor.getMaxSpeed())/4.0f);
	}

	/**
	 * Druckt eine Text passend auf das Display
	 * 
	 * @param text
	 *            Text, der ausgedruckt werden soll
	 * @param Ypos
	 *            Zeile in der der Text beginnt
	 */

	public static void printer(String text, int Ypos) {
		String[] output = text.split(" ");
		int n = output.length;
		int currSplit = 0;
		int Xpos = 0;

		for (int i = Ypos; i < SCREENHIGHT && currSplit != n; i++) {
			LCD.drawString(output[currSplit], Xpos, i);
			Xpos += output[currSplit].length() + 2;
			currSplit++;
			while (currSplit != n
					&& Xpos + output[currSplit].length() <= SCREENWIDTH) {
				LCD.drawString(output[currSplit], Xpos, i);
				Xpos += output[currSplit].length() + 2;
				currSplit++;
			}
			System.out.println();
			Xpos = 0;
		}
	}
	
	public static void printer(String text) {
		String[] output = text.split(" ");
		int n = output.length;
		int currSplit = 0;
		int Xpos = 0;

		for (int i = 0; i < SCREENHIGHT && currSplit != n; i++) {
			LCD.drawString(output[currSplit], Xpos, i);
			Xpos += output[currSplit].length() + 2;
			currSplit++;
			while (currSplit != n
					&& Xpos + output[currSplit].length() <= SCREENWIDTH) {
				LCD.drawString(output[currSplit], Xpos, i);
				Xpos += output[currSplit].length() + 2;
				currSplit++;
			}
			System.out.println();
			Xpos = 0;
		}
	}

}
