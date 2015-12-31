package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.TimeInfo;

public class TimeFrm extends GeneralFrame {

	private TimeInfo data;

	public TimeInfo getData() {
		return data;
	}

	public void setData(TimeInfo data) {
		this.data = data;
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0006;
	}

	@Override
	public String toString() {
		return "TimeFrm [time=" + data + "]";
	}

}
