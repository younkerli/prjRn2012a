package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataEvent;

public class EventFrm extends GeneralFrame {

	private DataEvent data;

	public DataEvent getData() {
		return data;
	}

	public void setData(DataEvent data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EventFrm [data=" + data + "]";
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0009;
	}

	public boolean isDisplay() {
		int evtId = data.getEvent();
		if ((evtId > 10) && (evtId < 18)) {
			return true;
		}
		return false;
	}

}
