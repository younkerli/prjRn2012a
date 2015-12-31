package rn2012a.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class SessionMap {

	private static Logger logger = Logger.getLogger(SessionMap.class);

	private Map<Integer, IoSession> map = new HashMap<Integer, IoSession>();

	public void addSession(Integer devId, IoSession session) {

		logger.info("===SessionMap.addSession():保存会话到SessionMap--- devId=" + devId);
		this.map.put(devId, session);

	}

	public IoSession getSession(Integer devId) {

		logger.info("===SessionMap.getSession():从SessionMap获取会话--- devId=" + devId);
		return this.map.get(devId);

	}

	public void sendMessage(Integer devId, Object message) {

		IoSession session = getSession(devId);
		if (session == null) {
			logger.error("===SessionMap.sendMessage():反向发送消息到客户端---异常：session为空");
			return;
		}
		logger.info("===SessionMap.sendMessage():反向发送消息到客户端--- devId=" + devId + "------- 消息：" + message);
		session.write(message);

	}

	public Integer[] getDevIds() {

		Set<Integer> devIds = map.keySet();
		Integer[] devs = new Integer[devIds.size()];
		devIds.toArray(devs);

		return devs;
	}

	public void remove(int devId) {
		// TODO Auto-generated method stub
		logger.info("===SessionMap.remove():删除设备" + devId + "对应的session。");
		map.remove(devId);
	}

}
