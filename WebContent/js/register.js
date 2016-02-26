$(function() {
	$("input[type='submit']").click(function() {
		var username = $("#username").val().trim();
		var password = $("#password").val().trim();
		if (username == "") {
			alert("用户名不能为空！");
			return;
		}
		if (password == "") {
			alert("请输入密码！");
			return;
		}
	});	
})