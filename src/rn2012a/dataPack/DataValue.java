package rn2012a.dataPack;

public class DataValue extends DataBox {

	private float overcurrent1;
	private float over1delay;
	private float overcurrent2;
	private float over2delay;
	private float overcurrent3;
	private float over3delay;
	private float accelerate;
	private float accelerateDelay;
	private float doublication1;
	private float doublication2;
	private float doublication3;
	private float reverseTime;
	private float reverseStart;
	private float reverseRatio;
	private float zero;
	private float zeroDelay;
	private float pt_volt;
	private float pt_overvolt;
	private float pt_none;
	private float backDelay;
	private int boardState;

	public float getOvercurrent1() {
		return overcurrent1;
	}

	public void setOvercurrent1(int overcurrent1) {
		this.overcurrent1 = overcurrent1;
		this.overcurrent1 /= 100;
	}

	public float getOver1delay() {
		return over1delay;
	}

	public void setOver1delay(int over1delay) {
		this.over1delay = over1delay;
		this.over1delay /= 100;
	}

	public float getOvercurrent2() {
		return overcurrent2;
	}

	public void setOvercurrent2(int overcurrent2) {
		this.overcurrent2 = overcurrent2;
		this.overcurrent2 /= 100;
	}

	public float getOver2delay() {
		return over2delay;
	}

	public void setOver2delay(int over2delay) {
		this.over2delay = over2delay;
		this.over2delay /= 100;
	}

	public float getOvercurrent3() {
		return overcurrent3;
	}

	public void setOvercurrent3(int overcurrent3) {
		this.overcurrent3 = overcurrent3;
		this.overcurrent3 /= 100;
	}

	public float getOver3delay() {
		return over3delay;
	}

	public void setOver3delay(int over3delay) {
		this.over3delay = over3delay;
		this.over3delay /= 100;
	}

	public float getAccelerate() {
		return accelerate;
	}

	public void setAccelerate(int accelerate) {
		this.accelerate = accelerate;
		this.accelerate /= 100;
	}

	public float getAccelerateDelay() {
		return accelerateDelay;
	}

	public void setAccelerateDelay(int accelerateDelay) {
		this.accelerateDelay = accelerateDelay;
		this.accelerateDelay /= 100;
	}

	public float getDoublication1() {
		return doublication1;
	}

	public void setDoublication1(int doublication1) {
		this.doublication1 = doublication1;
		this.doublication1 /= 10;
	}

	public float getDoublication2() {
		return doublication2;
	}

	public void setDoublication2(int doublication2) {
		this.doublication2 = doublication2;
		this.doublication2 /= 10;
	}

	public float getDoublication3() {
		return doublication3;
	}

	public void setDoublication3(int doublication3) {
		this.doublication3 = doublication3;
		this.doublication3 /= 10;
	}

	public float getReverseTime() {
		return reverseTime;
	}

	public void setReverseTime(int reverseTime) {
		this.reverseTime = reverseTime;
	}

	public float getReverseStart() {
		return reverseStart;
	}

	public void setReverseStart(int reverseStart) {
		this.reverseStart = reverseStart;
		this.reverseStart /= 100;
	}

	public float getReverseRatio() {
		return reverseRatio;
	}

	public void setReverseRatio(int reverseRatio) {
		this.reverseRatio = reverseRatio;
		this.reverseRatio /= 100;
	}

	public float getZero() {
		return zero;
	}

	public void setZero(int zero) {
		this.zero = zero;
		this.zero /= 1000;
	}

	public float getZeroDelay() {
		return zeroDelay;
	}

	public void setZeroDelay(int zeroDelay) {
		this.zeroDelay = zeroDelay;
		this.zeroDelay /= 10;
	}

	public float getPt_volt() {
		return pt_volt;
	}

	public void setPt_volt(int pt_volt) {
		this.pt_volt = pt_volt;
		this.pt_volt /= 100;
	}

	public float getPt_overvolt() {
		return pt_overvolt;
	}

	public void setPt_overvolt(int pt_overvolt) {
		this.pt_overvolt = pt_overvolt;
		this.pt_overvolt /= 100;
	}

	public float getPt_none() {
		return pt_none;
	}

	public void setPt_none(int pt_none) {
		this.pt_none = pt_none;
		this.pt_none /= 100;
	}

	public float getBackDelay() {
		return backDelay;
	}

	public void setBackDelay(int backDelay) {
		this.backDelay = backDelay;
		this.backDelay /= 10;
	}

	public int getBoardState() {
		return boardState;
	}

	public void setBoardState(int boardState) {
		this.boardState = boardState;
	}

	@Override
	public int getLen() {
		return 2 * 21;
	}

	@Override
	public String toString() {
		return "DataValue [overcurrent1=" + overcurrent1 + ", over1delay=" + over1delay + ", overcurrent2="
				+ overcurrent2 + ", over2delay=" + over2delay + ", overcurrent3=" + overcurrent3 + ", over3delay="
				+ over3delay + ", accelerate=" + accelerate + ", accelerateDelay=" + accelerateDelay
				+ ", doublication1=" + doublication1 + ", doublication2=" + doublication2 + ", doublication3="
				+ doublication3 + ", reverseTime=" + reverseTime + ", reverseStart=" + reverseStart + ", reverseRatio="
				+ reverseRatio + ", zero=" + zero + ", zeroDelay=" + zeroDelay + ", pt_volt=" + pt_volt
				+ ", pt_overvolt=" + pt_overvolt + ", pt_none=" + pt_none + ", backDelay=" + backDelay + ", boardState="
				+ boardState + "]";
	}

}
