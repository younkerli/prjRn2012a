package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataUser;

public class MessageMenuFrm extends GeneralFrame {

	private DataUser data;

	public DataUser getData() {
		return data;
	}

	public void setData(DataUser data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MessageMenuFrm [data=" + data + "]";
	}

	@Override
	public short getLength(Charset charset) {
		return (short) (MINLEN + data.getLen());
	}

	@Override
	public short getCommandId() {
		return (short) 0x0008;
	}

}
