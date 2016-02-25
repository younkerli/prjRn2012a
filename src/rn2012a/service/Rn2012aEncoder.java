package rn2012a.service;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import rn2012a.frm.AddrFrm;
import rn2012a.frm.CallDataFrm;
import rn2012a.frm.EventFrm;
import rn2012a.frm.GeneralFrame;
import rn2012a.frm.MeasureFrm;
import rn2012a.frm.MessageMenuFrm;
import rn2012a.frm.TimeFrm;
import rn2012a.frm.TopMenuFrm;
import rn2012a.frm.ValueFrm;

public class Rn2012aEncoder implements MessageEncoder<GeneralFrame> {

	private Logger logger = Logger.getLogger(Rn2012aEncoder.class);
	private Charset charset = Charset.forName("UTF-8");

	// public Rn2012aEncoder(Charset charset) {
	// this.charset = charset;
	// }

	private byte getCheckSum(IoBuffer buf) {
		buf.flip();
		byte sum = 0;
		while (buf.hasRemaining()) {
			sum += buf.get();
		}
		logger.info("===Rn2012aEncoder.getCheckSum():校验和：0x" + Integer.toHexString(Byte.toUnsignedInt(sum)));
		return sum;
	}

	@Override
	public void encode(IoSession session, GeneralFrame frame, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		/**
		 * 开始编码
		 */
		buf.putUnsignedShort(frame.HEAD);
		/**
		 * 编码数据区
		 */
		if (frame instanceof TopMenuFrm) {
			buf.put(dataTopMenuFrm(frame));
		} else if (frame instanceof ValueFrm) {
			buf.put(dataValueFrm(frame));
		} else if (frame instanceof TimeFrm) {
			buf.put(dataTimeFrm(frame));
		} else if (frame instanceof MeasureFrm) {
			buf.put(dataMeasureFrm(frame));
		} else if (frame instanceof EventFrm) {
			buf.put(dataEventFrm(frame));
		} else if (frame instanceof MessageMenuFrm) {
			buf.put(dataMessageMenuFrm(frame));
		} else if (frame instanceof CallDataFrm) {
			buf.put(dataCallDataFrm(frame));
		} else if (frame instanceof AddrFrm) {
		    buf.put(dataAddrFrm(frame));
        }

		buf.putUnsignedInt(frame.getTimeStamp());
		buf.putUnsigned(getCheckSum(buf));
		buf.putUnsignedShort(frame.TAIL);
		/**
		 * 编码成功
		 */
		buf.flip();
		logger.info("===Rn2012aEncoder.encode():编码：" + buf.toString());
		out.write(buf);
	}

	private IoBuffer dataAddrFrm(GeneralFrame frame) throws CharacterCodingException
    {
	    IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
	    AddrFrm frm = (AddrFrm) frame;
	    buf.putUnsignedShort(frm.getLength(charset));
	    buf.putUnsignedShort(frm.getCmdId());
	    buf.putUnsignedShort(frm.RESRV);
	    buf.putInt(frm.getDevId());
	    if (frm.getDataAddr().getAddress() != null)
        {
	        buf.putString(frm.getDataAddr().getAddress(), charset.newEncoder());
        }
	    return buf.flip();
    }

    private IoBuffer dataCallDataFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		CallDataFrm frm = (CallDataFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCmdId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());

		return buf.flip();
	}

	private IoBuffer dataMessageMenuFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		MessageMenuFrm frm = (MessageMenuFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		String[] userPhone = frm.getData().getUserphone();
		if (userPhone != null) {
			for (int i = 0; i < userPhone.length; i++) {
				buf.put(userPhone[i].getBytes());
			}
		}
		buf.putUnsigned(frm.getData().getCount());
		return buf.flip();
	}

	private IoBuffer dataEventFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		EventFrm frm = (EventFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		buf.putUnsigned(frm.getData().getTm().getWeek());
		buf.putUnsigned(frm.getData().getTm().getYear());
		buf.putUnsigned(frm.getData().getTm().getMonth());
		buf.putUnsigned(frm.getData().getTm().getDate());
		buf.putUnsigned(frm.getData().getTm().getHour());
		buf.putUnsigned(frm.getData().getTm().getMinute());
		buf.putUnsigned(frm.getData().getTm().getSecond());
		buf.putUnsigned(frm.getData().getTm().getAmpm());
		buf.putUnsignedShort(frm.getData().getEvent());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getVoltage()*100).intValue());
		try {
			buf.putString(frm.getData().getPharse(),charset.newEncoder());
		} catch (CharacterCodingException e) {
			logger.error("===Rn2012aEncoder.dataEventFrm(): String2byte error!");
			e.printStackTrace();
		}
		buf.putUnsigned(frm.getData().getReserved());
		return buf.flip();
	}

	private IoBuffer dataMeasureFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		MeasureFrm frm = (MeasureFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		buf.putInt(Float.valueOf(frm.getData().getUa()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getUb()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getUc()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getUbat()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getIa()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getIb()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getIc()*100).intValue());
		buf.putInt(Float.valueOf(frm.getData().getIn()*1000).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getFrequency()*100).intValue());
		buf.putUnsignedShort(frm.getData().getMeetCount());
		return buf.flip();
	}

	private IoBuffer dataTimeFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		TimeFrm frm = (TimeFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		buf.putUnsigned(frm.getData().getWeek());
		buf.putUnsigned(frm.getData().getYear());
		buf.putUnsigned(frm.getData().getMonth());
		buf.putUnsigned(frm.getData().getDate());
		buf.putUnsigned(frm.getData().getHour());
		buf.putUnsigned(frm.getData().getMinute());
		buf.putUnsigned(frm.getData().getSecond());
		buf.putUnsigned(frm.getData().getAmpm());
		return buf.flip();
	}

	private IoBuffer dataValueFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		ValueFrm frm = (ValueFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOvercurrent1()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOver1delay()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOvercurrent2()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOver2delay()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOvercurrent3()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getOver3delay()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getAccelerate()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getAccelerateDelay()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getDoublication1()*10).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getDoublication2()*10).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getDoublication3()*10).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getReverseTime()).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getReverseStart()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getReverseRatio()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getZero()*1000).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getZeroDelay()*10).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getPt_volt()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getPt_overvolt()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getPt_none()*100).intValue());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getBackDelay()*10).intValue());
		buf.putUnsignedShort(frm.getData().getBoardState());
		return buf.flip();
	}

	private IoBuffer dataTopMenuFrm(GeneralFrame frame) {
		IoBuffer buf = IoBuffer.allocate(10).setAutoExpand(true);
		TopMenuFrm frm = (TopMenuFrm) frame;
		buf.putUnsignedShort(frm.getLength(charset));
		buf.putUnsignedShort(frm.getCommandId());
		buf.putUnsignedShort(frm.RESRV);
		buf.putInt(frm.getDevId());
		buf.putUnsigned(frm.getData().getTm().getWeek());
		buf.putUnsigned(frm.getData().getTm().getYear());
		buf.putUnsigned(frm.getData().getTm().getMonth());
		buf.putUnsigned(frm.getData().getTm().getDate());
		buf.putUnsigned(frm.getData().getTm().getHour());
		buf.putUnsigned(frm.getData().getTm().getMinute());
		buf.putUnsigned(frm.getData().getTm().getSecond());
		buf.putUnsigned(frm.getData().getTm().getAmpm());
		buf.putUnsignedShort(frm.getData().getSwitchState());
		buf.putUnsignedShort(Float.valueOf(frm.getData().getPtValue()*100).intValue());
		return buf.flip();
	}

}
