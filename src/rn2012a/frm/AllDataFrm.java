package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataMeasure;
import rn2012a.dataPack.DataTop;
import rn2012a.dataPack.DataUser;
import rn2012a.dataPack.DataValue;

public class AllDataFrm extends GeneralFrame {

	private DataTop dataTop;
	private DataValue dataValue;
	private DataMeasure dataMeausre;
	private DataUser dataUser;
	
	public DataTop getDataTop() {
		return dataTop;
	}

	public void setDataTop(DataTop dataTop) {
		this.dataTop = dataTop;
	}

	public DataValue getDataValue() {
		return dataValue;
	}

	public void setDataValue(DataValue dataValue) {
		this.dataValue = dataValue;
	}

	public DataMeasure getDataMeausre() {
		return dataMeausre;
	}

	public void setDataMeausre(DataMeasure dataMeausre) {
		this.dataMeausre = dataMeausre;
	}

	public DataUser getDataUser() {
		return dataUser;
	}

	public void setDataUser(DataUser dataUser) {
		this.dataUser = dataUser;
	}

	@Override
	public short getLength(Charset charset) {
		int len = dataTop.getLen() + dataMeausre.getLen() + dataValue.getLen() + dataUser.getLen() + MINLEN;
		return (short) len;
	}

	@Override
	public short getCommandId() {
		return 11;
	}

	@Override
	public String toString() {
		return "AllDataFrm [dataTop=" + dataTop + ", dataValue=" + dataValue + ", dataMeausre=" + dataMeausre
				+ ", dataUser=" + dataUser + "]";
	}

}
