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

	$("#username").blur(
			function() {
				$.get("usernameAction", $("#registerAction").serializeArray(), function(data, statusText) {
					if (data == '1') {
						$("#username").val("");
						$("#show").height(30).width(200)
						.css("color", "#ff0000")
						.css("font-size", "0.8em").css("text-align", "left")
						.css("margin", "0 auto")
						.empty();
						$("#show").append("用户名已存在！<br/>");
						$("#show").show();
					}
				}, "html");
			});
})