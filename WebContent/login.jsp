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
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>RN2012A——用户登陆</title>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/login.js"></script>
</head>
<body>

	<div id="container">

                        <s:a id="userMng" name="userMng">用户注册</s:a>
		<div id="loginfrm">
			<s:form action="loginAction" theme="simple">
				<label for="username">用户名:</label>
				<div class="div_txtbox">
					<s:textfield id="username" name="username"></s:textfield>
				</div>
				<label for="password"> 密码: </label>
				<div class="div_txtbox">
					<s:password id="password" name="password"></s:password>
				</div>
<!-- 				<label for="region"> 地区: </label> -->
<!-- 				<div class="div_txtbox"> -->
<%-- 					<s:textfield id="region" name="region" value="芮城"></s:textfield> --%>
<!-- 				</div> -->
				<div class="div_submit">
					<s:submit value="登陆"></s:submit>
				</div>
				<div class="div_error">
					<s:fielderror />
				</div>
			</s:form>

		</div>

	</div>

</body>
</html>