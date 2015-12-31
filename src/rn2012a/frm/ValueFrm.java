package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataValue;

public class ValueFrm extends GeneralFrame {

	private DataValue data;

	public DataValue getData() {
		return data;
	}

	public void setData(DataValue data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ValueFrm [data=" + data + "]";
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0003;
	}

}
