package rn2012a.frm;

import java.nio.charset.Charset;

public class CallDataFrm extends GeneralFrame {

	@Override
	public short getLength(Charset charset) {
		return MINLEN;
	}

	@Override
	public short getCommandId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "CallDataFrm []";
	}

}
