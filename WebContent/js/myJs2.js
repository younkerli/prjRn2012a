$(function () {
	$("#ids").focus(function () {
		$.ajax({
			url : "devIdAction",
			type : "post",
			dataType : "json",
		}).done(function(data) {
			var devIds = data.devIds;
			if (devIds == null) {
				alert("设备编号列表为空！");
				return;
			}
//			var devAddrs = data.devAddrs;
			$("#ids").empty();
			$("#ids").append($("<option>").text("选择设备编号...").val("-1"));
			$.each(devIds, function(n, value) {
				$("#ids").append($("<option/>", {
					value : n,
					text : value
				}));
			});
		});
	}).change(function() {
		var index = $("#ids option:selected").val();
		if (index == "-1") {
			$("#addr").val("");
			return;
		}
		
		$.ajax({
			url : "showAddr",
			type : "post",
			dataType : "json",
			data : {
				devId : $("#ids option:selected").text()
			}
		}).done(function(data) {
			$("#addr").val(data.devAddr);
		})
	});
	$("#submit").click(function() {
		
		$("#id").val($("#ids option:selected").text());
		var index = $("#ids option:selected").val();
		var addr = $("#addr").val().trim();
		if ( index == "-1" ) {
			alert("未选择设备编号！");
			return false;
		} else if ( addr == "" ) {
			alert("设备地址不能为空！");
			return false;
		}
//		$.ajax({
//			url : "saveAddr",
//			type : "post",
//			dataType : "json", 
//			data : {
//				id : id,
//				addr : addr
//			}
//		});
	});
})