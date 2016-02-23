package rn2012a.service;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import rn2012a.dataPack.DataTop;
import rn2012a.frm.AddrFrm;
import rn2012a.frm.AllDataFrm;
import rn2012a.frm.CallDataFrm;
import rn2012a.frm.EventFrm;
import rn2012a.frm.GeneralFrame;
import rn2012a.frm.LedFrm;
import rn2012a.frm.MeasureFrm;
import rn2012a.frm.MessageMenuFrm;
import rn2012a.frm.TimeFrm;
import rn2012a.frm.TopMenuFrm;
import rn2012a.frm.ValueFrm;

public class ServerHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(ServerHandler.class);

	private SessionMap sessionMap;

	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

	private DataPackageMap dataPackageMap;

	public void setDataPackageMap(DataPackageMap dataPackageMap) {
		this.dataPackageMap = dataPackageMap;
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// super.messageReceived(session, message);
		// 业务逻辑
		int devId = (int) session.getAttribute("devId");
		if (devId > 200) {
			return ;
		}
		sessionMap.addSession(devId, session);
		DataPackage dataPack = dataPackageMap.getDataPackage(devId);
		if (dataPack == null) {
			dataPack = new DataPackage(); 
			dataPackageMap.addDataPackage(devId, dataPack);
		}
		session.setAttribute("msgRcvd", true);
		String msg = message.toString();
		logger.info("===ServerHandler.messageReceived():服务器接收到的数据为：" + msg);

		if (message instanceof EventFrm) {
			EventFrm frame = (EventFrm) message;
			if (frame.isDisplay()) {
				dataPack.setEventData(frame.getData());
				session.write(frame);
			}
		} else if (message instanceof MeasureFrm) {
			MeasureFrm frame = (MeasureFrm) message;
			dataPack.setMeasureData(frame.getData());
		} else if (message instanceof MessageMenuFrm) {
			MessageMenuFrm frame = (MessageMenuFrm) message;
			dataPack.setUserData(frame.getData());
		} else if (message instanceof TopMenuFrm) {
			TopMenuFrm frame = (TopMenuFrm) message;
			dataPack.setTopData(frame.getData());
		} else if (message instanceof ValueFrm) {
			ValueFrm frame = (ValueFrm) message;
			dataPack.setValueData(frame.getData());
		} else if (message instanceof TimeFrm) {
			TimeFrm frame = (TimeFrm) message;
			DataTop tp =  dataPack.getTopData();
			if (tp == null) {
				tp = new DataTop();
			}
			tp.setTm(frame.getData());
			dataPack.setTopData(tp);
		} else if (message instanceof LedFrm) {
			LedFrm frame = (LedFrm) message;
			dataPack.setLedData(frame.getData());
		} else if (message instanceof AllDataFrm) {
			AllDataFrm frame = (AllDataFrm) message;
			dataPack.setTopData(frame.getDataTop());
			dataPack.setValueData(frame.getDataValue());
			dataPack.setMeasureData(frame.getDataMeausre());
			dataPack.setUserData(frame.getDataUser());
		} else if (message instanceof AddrFrm) {
            AddrFrm frame = (AddrFrm) message;
            dataPack.setAddrData(frame.getDataAddr());
        } else {
			logger.error(":::非法数据！");
		}
		
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// super.sessionCreated(session);
		logger.info("===ServerHandler.sessionCreated():服务器与客户端创建连接……");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// super.sessionOpened(session);
		logger.info("===ServerHandler.sessionOpened():服务器与客户端打开连接……");
		CallDataFrm frm = new CallDataFrm();
		frm.setDevId(0);
		frm.setCmdId(6);
		session.setAttribute("msgRcvd", false);
		session.write(frm);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// super.messageSent(session, message);
		// session.close(true);
		logger.info("===ServerHandler.messageSent():服务器发送信息成功……");
		
		boolean msgRcvd = (boolean) session.getAttribute("msgRcvd");
		
		if (!msgRcvd) {
			CallDataFrm frm = new CallDataFrm();
			frm.setDevId(0);
			frm.setCmdId(6);
			
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					logger.info(":::延迟3秒……");
					session.write(frm);
				}
			}, 3000);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		session.setAttribute("msgRcvd", false);
		logger.info("===ServerHandler.sessionClosed():session断开！！");
		super.sessionClosed(session);
		int devId = (int) session.getAttribute("devId");
		sessionMap.remove(devId);
		dataPackageMap.removeDataPackage(devId);
		session.close(true);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// super.sessionIdle(session, status);
		logger.info("===ServerHandler.sessionIdle():服务器进入空闲状态……");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// super.exceptionCaught(session, cause);
		logger.error("===ServerHandler.exceptionCaught():服务器异常……", cause);
	}

	public boolean sendMessage(int devId, int cmdId) {

		// 1. 根据设备编号，获得对应的session
		IoSession session = sessionMap.getSession(devId);

		if (session == null) {
			logger.error("===ServerHandler.sendMessage(): session为空！！！");
		}

		// 2. 根据命令编号，准备数据帧
		GeneralFrame frame = null;

		if (cmdId < 1 || cmdId > 13) {
			logger.error("===ServerHandler.sendMessage(): 未知的命令ID！");
			return false;
		} else {
		    switch (cmdId) {
	        case 11:
	            frame = new CallDataFrm();
	            break;
	        case 13:
	            AddrFrm addrFrm = new AddrFrm();
	            String address = dataPackageMap.getDataPackage(devId).getAddrData().getAddress();
	            addrFrm.getDataAddr().setAddress(address);
	            frame = addrFrm;
	            break;

	        default:
	            break;
	        }

	        frame.setCmdId(cmdId);
			frame.setDevId(devId);
		}

		session.write(frame);

		return true;
	}
//
//	private GeneralFrame getFrame(int cmdId) {
//
//	    GeneralFrame frame = null;
//	    
//	    switch (cmdId) {
//        case 11:
//            frame = new CallDataFrm();
//            break;
//        case 13:
//            frame = new AddrFrm();
//            break;
//
//        default:
//            break;
//        }
//
//		frame.setCmdId(cmdId);
//
//		return frame;
//	}

}
