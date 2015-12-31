package rn2012a.frm;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import rn2012a.dataPack.DataTop;
import rn2012a.dataPack.TimeInfo;

public class TopMenuFrm extends GeneralFrame {

	private Logger logger = Logger.getLogger(TopMenuFrm.class);

	private DataTop data;

	public DataTop getData() {
		return data;
	}

	public void setData(DataTop data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TopMenuFrm [data=" + data + "]";
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0002;
	}

}
