package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataMeasure;

public class MeasureFrm extends GeneralFrame {

	private DataMeasure data;

	public DataMeasure getData() {
		return data;
	}

	@Override
	public String toString() {
		return "MeasureFrm [data=" + data + "]";
	}

	public void setData(DataMeasure data) {
		this.data = data;
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0007;
	}

}
