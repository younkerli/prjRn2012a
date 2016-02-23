<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=basePath%>rn_icon_256.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>rn_icon_256.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/content.css">
<title>RN2012A控制器实时监控</title>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/json2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myJs.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>js/myJs2.js"></script> --%>
</head>

<body>
	<div id="container">

		<div id="header">
			<div id="logo">
				<a class="logo_a" href="http://www.zzrndq.com/"><img
					src="image/rn_logo.jpg"></a>
			</div>
			<div id="title">
				<img src="image/title_black.png">
			</div>
			<div id="topright">
				<div id="show"></div>

			</div>
		</div>

		<div class="t"></div>
		<div id="content">
			<div id="row_1">
				<span style="margin-left: 10px"> <s:select id="devAddr"
						label="设备地址" list="#{-1:'选择设备地址...'}" onfocus="gotDevAddr();"></s:select>
				</span>
				<s:hidden id="devId"></s:hidden>
				<span style="margin-left: 10px"> <s:a id="addrMng">设备地址管理</s:a></span>
<%-- 				<span> --%>
<%-- 					<s:textfield id="devAdd" name="devAdd" label="设备地址" value="芮城县供电公司"></s:textfield> --%>
<%-- 				</span> --%>
				<%-- <span> <s:textfield id="cmdId" name="cmdId" label="CmdId"></s:textfield> --%>
				<%-- </span>  --%>
<%-- 				<span> <input type="button" value="提交" onclick="gotClick();" /> --%>
<%-- 				</span> --%>
			</div>
			<div id="lside">
				<div id="topMenu" class="ml">
					<div class="tit">
						<h3>TOP数据</h3>
					</div>
					<div class="con">
						<s:form id="ff_top">
							<s:textfield id="t_tm2" name="t_tm2" label="日期"></s:textfield>
							<s:textfield id="t_tm" name="t_tm" label="时间"></s:textfield>
							<s:textfield id="t_switch" name="t_switch" label="开关状态"></s:textfield>
							<s:textfield id="t_ptValue" name="t_ptValue" label="PT值"></s:textfield>
						</s:form>
					</div>
				</div>
				<div id="userInfo" class="ml">
					<div class="tit">
						<h3>用户数据</h3>
					</div>
					<div class="con">
						<s:form id="ff_usr">
							<s:textfield id="u_num1" name="u_num1" label="VIP号码"></s:textfield>
							<s:textfield id="u_num2" name="u_num2" label="普通号码1"></s:textfield>
							<s:textfield id="u_num3" name="u_num3" label="普通号码2"></s:textfield>
							<s:textfield id="u_num4" name="u_num4" label="普通号码3"></s:textfield>
							<s:textfield id="u_num5" name="u_num5" label="普通号码4"></s:textfield>
						</s:form>
					</div>
				</div>
			</div>
			<div id="main">
				<div id="valueMenu">
					<div class="tit">
						<h3>定值数据</h3>
					</div>
					<div>
						<div class="value_ val_l">
							<s:form id="ff_val">
								<s:textfield id="v_over1" name="v_over1" label="过流一段门槛"></s:textfield>
								<s:textfield id="v_over1delay" name="v_over1delay"
									label="过流一段延时"></s:textfield>
								<s:textfield id="v_over2" name="v_over2" label="过流二段门槛"></s:textfield>
								<s:textfield id="v_over2delay" name="v_over2delay"
									label="过流二段延时"></s:textfield>
								<s:textfield id="v_over3" name="v_over3" label="过流三段门槛"></s:textfield>
								<s:textfield id="v_over3delay" name="v_over3delay"
									label="过流三段延时"></s:textfield>
								<s:textfield id="v_accelerate" name="v_accelerate" label="后加速门槛"></s:textfield>
								<s:textfield id="v_accelerateDelay" name="v_accelerateDelay"
									label="后加速延时"></s:textfield>
								<s:textfield id="v_zero" name="v_zero" label="零序过流门槛"></s:textfield>
								<s:textfield id="v_zeroDelay" name="v_zeroDelay" label="零序过流延时"></s:textfield>
							</s:form>
						</div>
						<div class="value_ val_r">
							<s:form id="ff_val_">
								<s:textfield id="v_doublication1" name="v_doublication1"
									label="一次重合延时"></s:textfield>
								<s:textfield id="v_doublication2" name="v_doublication2"
									label="二次重合延时"></s:textfield>
								<s:textfield id="v_doublication3" name="v_doublication3"
									label="三次重合延时"></s:textfield>
								<s:textfield id="v_reverseTime" name="v_reverseTime" label="反时限"></s:textfield>
								<s:textfield id="v_reverseStart" name="v_reverseStart"
									label="反时限启动值"></s:textfield>
								<s:textfield id="v_reverseRatio" name="v_reverseRatio"
									label="反时限倍数"></s:textfield>
								<s:textfield id="v_pt_volt" name="v_pt_volt" label="PT有压门限"></s:textfield>
								<s:textfield id="v_pt_overvolt" name="v_pt_overvolt"
									label="PT过压门限"></s:textfield>
								<s:textfield id="v_pt_none" name="v_pt_none" label="PT无压门限"></s:textfield>
								<s:textfield id="v_backDelay" name="v_backDelay" label="重合闸复归延时"></s:textfield>
							</s:form>
						</div>
					</div>
				</div>
				<div id="measureMenu">
					<div class="tit">
						<h3>测量数据</h3>
					</div>
					<div class="con">
						<s:form id="ff_msr">
							<s:textfield id="m_ua" name="m_ua" label="A相测量电压"></s:textfield>
							<s:textfield id="m_ub" name="m_ub" label="B相测量电压"></s:textfield>
							<s:textfield id="m_uc" name="m_uc" label="C相测量电压"></s:textfield>
							<s:textfield id="m_ubat" name="m_ubat" label="电池电压"></s:textfield>
							<s:textfield id="m_ia" name="m_ia" label="A相测量电流"></s:textfield>
							<s:textfield id="m_ib" name="m_ib" label="B相测量电流"></s:textfield>
							<s:textfield id="m_ic" name="m_ic" label="C相测量电流"></s:textfield>
							<s:textfield id="m_in" name="m_in" label="零序测量电流"></s:textfield>
							<s:textfield id="m_f" name="m_f" label="频率"></s:textfield>
							<s:textfield id="m_count" name="m_count" label="开关状态"></s:textfield>
						</s:form>
					</div>
				</div>
				<div id="event">
					<div class="tit">
						<h3>事件数据</h3>
					</div>
					<div class="con">
						<s:form id="ff_evt">
							<s:select id="evtId" label="事件编号" list="#{-1:'事件编号...'}" onfocus="gotEvtId();"></s:select>
							<s:textfield id="e_tm2" name="e_tm2" label="事件发生日期"></s:textfield>
							<s:textfield id="e_tm" name="e_tm" label="事件发生时间"></s:textfield>
							<s:textfield id="e_event" name="e_event" label="事件描述"></s:textfield>
							<s:textfield id="e_volt" name="e_volt" label="PT电压记录"></s:textfield>
							<s:textfield id="e_curE" name="e_curE" label="事件电流"></s:textfield>
							<s:textfield id="e_vtE" name="e_vtE" label="事件电压"></s:textfield>
						</s:form>
					</div>
				</div>
			</div>
			<div id="borad">
				<div class="con">
					<table id="ff_borad">
						<tr>
							<td colspan="12"><h3>压板状态</h3></td>
						</tr>
						<tr>
							<td><h4>手动开关</h4></td>
							<td><h4>GPRS控制</h4></td>
							<td><h4>遥控器控制</h4></td>
							<td><h4>重合闸一</h4></td>
							<td><h4>重合闸二</h4></td>
							<td><h4>重合闸三</h4></td>
							<td><h4>后加速</h4></td>
							<td><h4>零序过流</h4></td>
							<td><h4>过流反时限</h4></td>
							<td><h4>过流一段</h4></td>
							<td><h4>过流二段</h4></td>
							<td><h4>过流三段</h4></td>
						</tr>
						<tr>
							<td><img src="<%=basePath%>image/qie.png" id="b_switch"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_gprs"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_ctrl"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_recls1"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_recls2"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_recls3"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_bkacc"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_zero"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_Reverse"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_over1"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_over2"></td>
							<td><img src="<%=basePath%>image/qie.png" id="b_over3"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="t"></div>
		<div id="footer">
			<div class="led">
				<img id="led_fen" src="image/led_gray.png"><br /> <br /> <font>分闸</font>
			</div>
			<div class="led">
				<img id="led_he" src="image/led_gray.png"><br /> <br /> <font>合闸</font>
			</div>
			<div class="led">
				<img id="led_chongh" src="image/led_gray.png"><br /> <br />
				<font>重合闸</font>
			</div>
			<div class="led">
				<img id="led_houjs" src="image/led_gray.png"><br /> <br /> <font>后加速</font>
			</div>
			<div class="led">
				<img id="led_batq" src="image/led_gray.png"><br /> <br /> <font>电池欠压</font>
			</div>
			<div class="led">
				<img id="led_capq" src="image/led_gray.png"><br /> <br /> <font>开关未储能</font>
			</div>
			<div class="led">
				<img id="led_PT" src="image/led_gray.png"><br /> <br /> <font>PT有压</font>
			</div>
			<div class="led">
				<img id="led_baohu" src="image/led_gray.png"><br /> <br /> <font>保护</font>
			</div>
			<div class="led">
				<img id="led_tongx" src="image/led_gray.png"><br /> <br /> <font>通讯</font>
			</div>
			<div class="led">
				<img id="led_work" src="image/led_gray.png"><br /> <br /> <font>工作</font>
			</div>
			<div class="led">
				<img id="led_unlk" src="image/led_gray.png"><br /> <br /> <font>解锁</font>
			</div>
			<div class="led">
				<img id="led_fugui" src="image/led_gray.png"><br /> <br /> <font>复归</font>
			</div>
		</div>
	</div>
</body>
</html>