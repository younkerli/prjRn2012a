package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataLed;

public class LedFrm extends GeneralFrame {

	private DataLed data;
	
	public DataLed getData() {
		return data;
	}

	public void setData(DataLed data) {
		this.data = data;
	}

	@Override
	public short getLength(Charset charset) {
		// TODO Auto-generated method stub
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		// TODO Auto-generated method stub
		return (short) 0x000a;
	}

	@Override
	public String toString() {
		return "LedFrm [data=" + data + "]";
	}


}
