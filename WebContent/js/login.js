//window.onload = function() {
//	document.getElementById("region").setAttribute("readOnly", true);
//}

$(function() {
	$("#userMng").click(function () {
		window.showModalDialog("userRegister.jsp", "用户注册", 'help:no;dialogWidth:400px;dialogHeight:300px' );
	});
	
})
