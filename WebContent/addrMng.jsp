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
<link rel="shortcut icon" href="<%=basePath%>rn_icon_256.ico"
	type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/addrMng.css">
<title>设备地址管理</title>
<script type="text/javascript"
        src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/myJs2.js"></script>
<base target=_self>
</head>
<body>
<%--         <s:debug></s:debug> --%>
        <div id="container">
	        <div id="title">设备地址管理</div>
		<s:form action="saveAddr">
			<table>
				<tbody>
					<tr>
						<td><s:select id="ids" name="ids" list="#{-1:'选择设备编号...'}" label="设备编号"></s:select></td>
						<s:hidden id="id" name="id"></s:hidden>
					</tr>
					<tr>
						<td><s:textfield id="addr" name="addr" label="设备地址"
								placeholder="设备地址..."></s:textfield></td>
					</tr>
					<tr>
						<td><s:submit id="submit" name="submit" value="保存修改"></s:submit></td>
<!--                                                 <td></td> -->
<!--                                                 <td><button id="submit" name="submit">保存修改</button></td> -->
					</tr>
				</tbody>
			</table>
	
		</s:form>
        </div>

</body>
</html>