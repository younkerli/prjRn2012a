<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="icon" href="<%=basePath%>rn_icon_256.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>rn_icon_256.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript"
        src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/register.js"></script>
</head>
<body>

        <div id="container">
                <div id="title">用户注册</div>
                <s:form action="registerAction">
                        <table>
                                <tbody>
                                        <tr>
                                                <s:textfield id="username" name="username" label="用户名" placeholder="请输入用户名..."></s:textfield>
                                        </tr>
                                        <tr>
                                                <s:password id="password" name="password" label="密码" placeholder="请输入密码..."></s:password>
                                        </tr>
                                        <s:submit value="提交"></s:submit>
                                </tbody>
                        </table>
                </s:form>
        </div>

</body>
</html>