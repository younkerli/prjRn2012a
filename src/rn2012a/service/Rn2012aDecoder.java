package rn2012a.service;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import rn2012a.dataPack.DataEvent;
import rn2012a.dataPack.DataLed;
import rn2012a.dataPack.DataMeasure;
import rn2012a.dataPack.DataTop;
import rn2012a.dataPack.DataUser;
import rn2012a.dataPack.DataValue;
import rn2012a.dataPack.TimeInfo;
import rn2012a.frm.AllDataFrm;
import rn2012a.frm.EventFrm;
import rn2012a.frm.GeneralFrame;
import rn2012a.frm.LedFrm;
import rn2012a.frm.MeasureFrm;
import rn2012a.frm.MessageMenuFrm;
import rn2012a.frm.TimeFrm;
import rn2012a.frm.TopMenuFrm;
import rn2012a.frm.ValueFrm;

public class Rn2012aDecoder implements MessageDecoder {

	private Logger logger = Logger.getLogger(Rn2012aDecoder.class);
	private Charset charset = Charset.forName("UTF-8");

	// public Rn2012aDecoder(Charset charset) {
	// this.charset = charset;
	// }

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {

		int pos = 0;
		while (in.getUnsignedShort() != (short) 0x55aa) {
			pos = in.position();
		}
		in.position(pos);

//		IoBuffer tmp = IoBuffer.allocate(10).setAutoExpand(true);
//		tmp.put(in);
//		tmp.flip();
//		// in.flip();
//		in.clear();
//		in.put(tmp);
//		in.flip();
//		
//		byte[] dt = in.array();
//		for (int i = 0; i < in.limit(); i++) {
//			logger.info("data[" + i +"]: " + Integer.toHexString((dt[i]) & 0xff) );
//		}
//		
//		
//		tmp.clear();
//		tmp.put(in);
//		in.flip();
//		tmp.flip();

		byte[] dt2 = in.array();
		for (int i = 0; i < in.limit(); i++) {
			logger.info("data[" + i +"]: " + Integer.toHexString((dt2[i]) & 0xff) );
		}
		
		logger.info("===Rn2012aDecoder.decodable():data:" + in.toString());

		if (in.remaining() < 19) {
			logger.info(":::数据长度小于最小帧长");
			return MessageDecoderResult.NEED_DATA;
		}
		if (in.getUnsignedShort() != (short) 0x55aa) {
			logger.error("帧头出错……");
		}
		int length = in.getUnsignedShort();
		if (in.remaining() < length - 4) {
			logger.info(":::数据长度小于帧中Length域数据");
			return MessageDecoderResult.NEED_DATA;
		}
		int cmd = in.getUnsignedShort();
		if (cmd < 1 || cmd > 11) {
			logger.error(":::未知的命令ID……");
			return MessageDecoderResult.NOT_OK;
		} else {
			logger.info(":::命令ID：0x" + Integer.toHexString(cmd));
		}
		
		// 检验校验和
//		int sum = 0;
//		while (tmp.position() < length - 3) {
//			sum += tmp.get();
//		}
//		if ((sum & 0xff) != tmp.get()) {
//			logger.error(":::校验和出错！");
//			in.clear();
//			return MessageDecoderResult.NOT_OK;
//		}

		return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		logger.info("===Rn2012aDecoder.decode():decode:" + in.toString());
		
		byte[] dt = in.array();
		for (int i = 0; i < in.limit(); i++) {
			logger.info("data[" + i +"]: " + Integer.toHexString((dt[i]) & 0xff) );
		}
		
		CharsetDecoder decoder = charset.newDecoder();// 指定字符集的解码器
		GeneralFrame frame = null;
		int head = in.getUnsignedShort();
		int length = in.getUnsignedShort();
		byte[] data = new byte[length - 19];
		int cmd = in.getUnsignedShort();
		int reverse = in.getUnsignedShort();
		int devId = in.getInt();
		session.setAttribute("devId", devId);
		in.get(data);
		int timeStamp =	in.getInt();
		short checksum = in.getUnsigned();
		short tail = in.getShort();
		/**
		 * 准备解析数据
		 */
		IoBuffer dataBuf = IoBuffer.allocate(10).setAutoExpand(true);
		dataBuf.put(data);
		dataBuf.flip();

		IoBuffer frmBuf = IoBuffer.allocate(10).setAutoExpand(true);
		frmBuf.putUnsignedShort(head);
		frmBuf.putUnsignedShort(length);
		frmBuf.putUnsignedShort(cmd);
		frmBuf.putUnsignedShort(reverse);
		frmBuf.putInt(devId);
		frmBuf.put(data);
		frmBuf.putInt(timeStamp);
		frmBuf.flip();
		// 检验校验和
		int sum = 0;
		while (frmBuf.hasRemaining()) {
			sum += frmBuf.get();
		}
		if ((sum & 0xff) != checksum) {
			logger.error(":::校验和出错！");
			return MessageDecoderResult.NOT_OK;
		}

		/**
		 * 开始解码
		 */
		switch (cmd) {
		case (short) 0x0002:
			frame = getTopMenuFrm(dataBuf);
			break;
		case (short) 0x0003:
			frame = getValueFrm(dataBuf);
			break;
		case (short) 0x0006:
			frame = getTimeFrm(dataBuf);
			break;
		case (short) 0x0007:
			frame = getMeasureFrm(dataBuf);
			break;
		case (short) 0x0008:
			frame = getMessageMenuFrm(dataBuf);
			break;
		case (short) 0x0009:
			frame = getEventFrm(dataBuf);
			break;
		case (short) 0x000a:
			frame = getLedFrm(dataBuf);
			break;
		case (short)0x000b:
			frame = getAllDataFrm(dataBuf);
			break;
		default:
			logger.error(":::未找到解码器……");
			break;
		}
		frame.setDevId(devId);

		/**
		 * 解码成功
		 */
		out.write(frame);
		return MessageDecoderResult.OK;
	}

	private AllDataFrm getAllDataFrm(IoBuffer dataBuf) {
		
		DataTop top = new DataTop();
		DataValue val = new DataValue();
		DataMeasure msr = new DataMeasure();
		DataUser usr = new DataUser();
		
		TimeInfo tm = new TimeInfo();
		tm.setWeek(dataBuf.getUnsigned());
		tm.setYear(dataBuf.getUnsigned());
		tm.setMonth(dataBuf.getUnsigned());
		tm.setDate(dataBuf.getUnsigned());
		tm.setHour(dataBuf.getUnsigned());
		tm.setMinute(dataBuf.getUnsigned());
		tm.setSecond(dataBuf.getUnsigned());
		tm.setAmpm(dataBuf.getUnsigned());
		top.setTm(tm);
		top.setSwitchState(dataBuf.getUnsignedShort());
		top.setPtValue(dataBuf.getUnsignedShort());
		

		val.setOvercurrent1(dataBuf.getShort());
		val.setOver1delay(dataBuf.getShort());
		val.setOvercurrent2(dataBuf.getShort());
		val.setOver2delay(dataBuf.getShort());
		val.setOvercurrent3(dataBuf.getShort());
		val.setOver3delay(dataBuf.getShort());
		val.setAccelerate(dataBuf.getShort());
		val.setAccelerateDelay(dataBuf.getShort());
		val.setDoublication1(dataBuf.getShort());
		val.setDoublication2(dataBuf.getShort());
		val.setDoublication3(dataBuf.getShort());
		val.setReverseTime(dataBuf.getShort());
		val.setReverseStart(dataBuf.getShort());
		val.setReverseRatio(dataBuf.getShort());
		val.setZero(dataBuf.getShort());
		val.setZeroDelay(dataBuf.getShort());
		val.setPt_volt(dataBuf.getShort());
		val.setPt_overvolt(dataBuf.getShort());
		val.setPt_none(dataBuf.getShort());
		val.setBackDelay(dataBuf.getShort());
		dataBuf.getShort();
		val.setBoardState(dataBuf.getShort());
		
		msr.setUa(dataBuf.getInt());
		msr.setUb(dataBuf.getInt());
		msr.setUc(dataBuf.getInt());
		msr.setUbat(dataBuf.getInt());
		msr.setIa(dataBuf.getInt());
		msr.setIb(dataBuf.getInt());
		msr.setIc(dataBuf.getInt());
		msr.setIn(dataBuf.getInt());
		msr.setFrequency(dataBuf.getShort());
		msr.setMeetCount(dataBuf.getShort());
		
		String[] phones = new String[5];
		for (int i = 0; i < phones.length; i++) {
			String phone = new String();
			for (int j = 0; j < 11; j++) {
				phone += dataBuf.get();
			}
			phones[i] = phone;
		}
		usr.setUserphone(phones);
		usr.setCount(dataBuf.get());
		
		AllDataFrm frm = new AllDataFrm();
		frm.setDataTop(top);
		frm.setDataValue(val);
		frm.setDataMeausre(msr);
		frm.setDataUser(usr);
		return frm;
	}

	private LedFrm getLedFrm(IoBuffer dataBuf) {
		DataLed data = new DataLed();
		data.setLeds(dataBuf.getUnsignedShort());
		
		LedFrm frm = new LedFrm();
		frm.setData(data);
		return frm;
	}

	private MessageMenuFrm getMessageMenuFrm(IoBuffer dataBuf) throws CharacterCodingException {
		String[] phones = new String[5];
		for (int i = 0; i < phones.length; i++) {
			phones[i] = dataBuf.getString(11, charset.newDecoder());
		}
		DataUser data = new DataUser();
		data.setUserphone(phones);
		data.setCount(dataBuf.get());

		MessageMenuFrm frm = new MessageMenuFrm();
		frm.setData(data);
		return frm;
	}

	private EventFrm getEventFrm(IoBuffer dataBuf) {
		TimeInfo tm = new TimeInfo();
		tm.setWeek(dataBuf.getUnsigned());
		tm.setYear(dataBuf.getUnsigned());
		tm.setMonth(dataBuf.getUnsigned());
		tm.setDate(dataBuf.getUnsigned());
		tm.setHour(dataBuf.getUnsigned());
		tm.setMinute(dataBuf.getUnsigned());
		tm.setSecond(dataBuf.getUnsigned());
		tm.setAmpm(dataBuf.getUnsigned());
		DataEvent data = new DataEvent();
		data.setTm(tm);
		data.setEvent(dataBuf.getUnsignedShort());
		data.setVoltage(dataBuf.getShort());
		char[] tmp = {(char) dataBuf.get()};
		data.setPharse(new String(tmp));
		data.setReserved(dataBuf.get());
		
		EventFrm frm = new EventFrm();
		frm.setData(data);
		return frm;
	}

	private MeasureFrm getMeasureFrm(IoBuffer dataBuf) {
		DataMeasure data = new DataMeasure();
		data.setUa(dataBuf.getInt());
		data.setUb(dataBuf.getInt());
		data.setUc(dataBuf.getInt());
		data.setUbat(dataBuf.getInt());
		data.setIa(dataBuf.getInt());
		data.setIb(dataBuf.getInt());
		data.setIc(dataBuf.getInt());
		data.setIn(dataBuf.getInt());
		data.setFrequency(dataBuf.getShort());
		data.setMeetCount(dataBuf.getShort());

		MeasureFrm frm = new MeasureFrm();
		frm.setData(data);
		return frm;
	}

	private TimeFrm getTimeFrm(IoBuffer dataBuf) {
		TimeInfo tm = new TimeInfo();
		tm.setWeek(dataBuf.getUnsigned());
		tm.setYear(dataBuf.getUnsigned());
		tm.setMonth(dataBuf.getUnsigned());
		tm.setDate(dataBuf.getUnsigned());
		tm.setHour(dataBuf.getUnsigned());
		tm.setMinute(dataBuf.getUnsigned());
		tm.setSecond(dataBuf.getUnsigned());
		tm.setAmpm(dataBuf.getUnsigned());

		TimeFrm frm = new TimeFrm();
		frm.setData(tm);
		return frm;
	}

	private ValueFrm getValueFrm(IoBuffer dataBuf) {
		DataValue data = new DataValue();
		data.setOvercurrent1(dataBuf.getShort());
		data.setOver1delay(dataBuf.getShort());
		data.setOvercurrent2(dataBuf.getShort());
		data.setOver2delay(dataBuf.getShort());
		data.setOvercurrent3(dataBuf.getShort());
		data.setOver3delay(dataBuf.getShort());
		data.setAccelerate(dataBuf.getShort());
		data.setAccelerateDelay(dataBuf.getShort());
		data.setDoublication1(dataBuf.getShort());
		data.setDoublication2(dataBuf.getShort());
		data.setDoublication3(dataBuf.getShort());
		data.setReverseTime(dataBuf.getShort());
		data.setReverseStart(dataBuf.getShort());
		data.setReverseRatio(dataBuf.getShort());
		data.setZero(dataBuf.getShort());
		data.setZeroDelay(dataBuf.getShort());
		data.setPt_volt(dataBuf.getShort());
		data.setPt_overvolt(dataBuf.getShort());
		data.setPt_none(dataBuf.getShort());
		data.setBackDelay(dataBuf.getShort());
		dataBuf.getShort();
		data.setBoardState(dataBuf.getShort());

		ValueFrm frm = new ValueFrm();
		frm.setData(data);
		return frm;
	}

	private TopMenuFrm getTopMenuFrm(IoBuffer dataBuf) {
		TimeInfo tm = new TimeInfo();
		tm.setWeek(dataBuf.getUnsigned());
		tm.setYear(dataBuf.getUnsigned());
		tm.setMonth(dataBuf.getUnsigned());
		tm.setDate(dataBuf.getUnsigned());
		tm.setHour(dataBuf.getUnsigned());
		tm.setMinute(dataBuf.getUnsigned());
		tm.setSecond(dataBuf.getUnsigned());
		tm.setAmpm(dataBuf.getUnsigned());
		DataTop data = new DataTop();
		data.setTm(tm);
		data.setSwitchState(dataBuf.getUnsignedShort());
		data.setPtValue(dataBuf.getUnsignedShort());

		TopMenuFrm frm = new TopMenuFrm();
		frm.setData(data);
		return frm;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
	}

}
