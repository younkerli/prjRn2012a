package rn2012a.frm;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class GeneralFrame {

	protected final short MINLEN = 2 + 2 + 2 + 2 + 4 + 4 + 1 + 2;

	public final short HEAD = (short) 0x55aa;

	public final short TAIL = (short) 0xaa55;

	public final short RESRV = 0;
	
	protected final int OFFSET = 2 + 2 + 2 + 2 + 4;

	private int devId;

	private int cmdId;

	public int getDevId() {
		return devId;
	}

	public void setDevId(int devId) {
		this.devId = devId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	public int getCmdId() {
		return cmdId;
	}

	public abstract short getLength(Charset charset);

	public abstract short getCommandId();

	public int getTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date markTime = null;
		try {
			markTime = sdf.parse("2000-00-00 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long timeSpan = (System.currentTimeMillis() - markTime.getTime()) / 1000;
		return (int) timeSpan;
	}

	public abstract String toString();
	
}
