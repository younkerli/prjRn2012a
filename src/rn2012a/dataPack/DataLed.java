package rn2012a.dataPack;

public class DataLed extends DataBox {

	private int leds;
	
	public int getLeds() {
		return leds;
	}

	public void setLeds(int leds) {
		this.leds = leds;
	}

	@Override
	public int getLen() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String toString() {
		return "DataLed [leds=" + leds + "]";
	}

}
