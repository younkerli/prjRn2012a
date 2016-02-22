
window.onload = function() {
	setInterval("refrsh();", 5000);
	setRdOnly();
}

function setRdOnly(){
	$("#ff_top input").attr("readOnly",true);
	$("#ff_usr input").attr("readOnly",true);
	$("#ff_val input").attr("readOnly",true);
	$("#ff_val_ input").attr("readOnly",true);
	$("#ff_msr input").attr("readOnly",true);
	$("#ff_evt input").attr("readOnly",true);
} 

function refrsh(){
	var aaaa = $("#devId").val() ;
	if ( aaaa == "-1") {
		return;
	} else {
		gotClick();
	}
}

function gotClick() {

	
	jQuery.ajax({

		url : "testAction.action",
		type : "POST",
		dataType : "json",
		data : {
			// action中属性名:值
			devId : $("#devAddr option:selected").val(),
		}

	}).done(
			function(data) {
				
				var topData = data.dataPackage.topData;
				if(topData != null){
					$("#t_tm2").val((topData.tm.year + 2000) + "-" + padLeft(topData.tm.month,2) + "-" + padLeft(topData.tm.date,2));
					$("#t_tm").val(
							topData.tm.hour + ":" + padLeft(topData.tm.minute,2) + ":"
							+ padLeft(topData.tm.second,2));
					$("#t_switch").val(topData.switchState?"关":"开");
					$("#t_ptValue").val(topData.ptValue +"V");
				}
				
				var userData = data.dataPackage.userData;
				if (userData != null) {
					$("#u_num1").val(userData.userphone[0]);
					$("#u_num2").val(userData.userphone[1]);
					$("#u_num3").val(userData.userphone[2]);
					$("#u_num4").val(userData.userphone[3]);
					$("#u_num5").val(userData.userphone[4]);
				}
				
				var valueData = data.dataPackage.valueData;
				if (valueData != null) {
					$("#v_over1").val(valueData.overcurrent1 + "A");
					$("#v_over1delay").val(valueData.over1delay + "S");
					$("#v_over2").val(valueData.overcurrent2 + "A");
					$("#v_over2delay").val(valueData.over2delay + "S");
					$("#v_over3").val(valueData.overcurrent3 + "A");
					$("#v_over3delay").val(valueData.over3delay + "S");
					$("#v_accelerate").val(valueData.accelerate + "A");
					$("#v_accelerateDelay").val(valueData.accelerateDelay + "S");
					$("#v_zero").val(valueData.zero + "A");
					$("#v_zeroDelay").val(valueData.zeroDelay + "S");
					$("#v_doublication1").val(valueData.doublication1 + "S");
					$("#v_doublication2").val(valueData.doublication2 + "S");
					$("#v_doublication3").val(valueData.doublication3 + "S");
					$("#v_reverseTime").val(valueData.reverseTime + "S");
					$("#v_reverseStart").val(valueData.reverseStart + "S");
					$("#v_reverseRatio").val(valueData.reverseRatio);
					$("#v_pt_volt").val(valueData.pt_volt + "V");
					$("#v_pt_overvolt").val(valueData.pt_overvolt + "V");
					$("#v_pt_none").val(valueData.pt_none + "V");
					$("#v_backDelay").val(valueData.backDelay + "S");
					
					var boardState = valueData.boardState;
					if (boardState != null) {
						var tou = "image/tou.png";
						var qie = "image/qie.png";
						if (boardState & 1) {
							$("#b_switch").attr("src", tou);
						} else {
							$("#b_switch").attr("src", qie);
						}
						if ((boardState >> 1) & 1) {
							$("#b_gprs").attr("src", tou);
						} else {
							$("#b_gprs").attr("src", qie);
						}
						if ((boardState >> 2) & 1) {
							$("#b_ctrl").attr("src", tou);
						} else {
							$("#b_ctrl").attr("src", qie);
						}
						switch ((boardState >> 3) & 3) {
						case 0:
							$("#b_recls1").attr("src", qie);
							$("#b_recls2").attr("src", qie);
							$("#b_recls3").attr("src", qie);
							break;
						case 1:
							$("#b_recls1").attr("src", tou);
							$("#b_recls2").attr("src", qie);
							$("#b_recls3").attr("src", qie);
							break;
						case 2:
							$("#b_recls1").attr("src", tou);
							$("#b_recls2").attr("src", tou);
							$("#b_recls3").attr("src", qie);
							break;
						case 3:
							$("#b_recls1").attr("src", tou);
							$("#b_recls2").attr("src", tou);
							$("#b_recls3").attr("src", tou);
							break;
						default:
							break;
						}
						if ((boardState >> 5) & 1) {
							$("#b_bkacc").attr("src", tou);
						} else {
							$("#b_bkacc").attr("src", qie);
						}
						if ((boardState >> 6) & 1) {
							$("#b_zero").attr("src", tou);
						} else {
							$("#b_zero").attr("src", qie);
						}
						if ((boardState >> 7) & 1) {
							$("#b_Reverse").attr("src", tou);
						} else {
							$("#b_Reverse").attr("src", qie);
						}
						if ((boardState >> 8) & 1) {
							$("#b_over3").attr("src", tou);
						} else {
							$("#b_over3").attr("src", qie);
						}
						if ((boardState >> 9) & 1) {
							$("#b_over2").attr("src", tou);
						} else {
							$("#b_over2").attr("src", qie);
						}
						if ((boardState >> 10) & 1) {
							$("#b_over1").attr("src", tou);
						} else {
							$("#b_over1").attr("src", qie);
						}
					}
				}
				
				var measureData = data.dataPackage.measureData;
				if (measureData != null) {
					$("#m_ua").val(measureData.ua + "V");
					$("#m_ub").val(measureData.ub + "V");
					$("#m_uc").val(measureData.uc + "V");
					$("#m_ubat").val(measureData.ubat + "V");
					$("#m_ia").val(measureData.ia + "A");
					$("#m_ib").val(measureData.ib + "A");
					$("#m_ic").val(measureData.ic + "A");
					$("#m_in").val(measureData.in + "A");
					$("#m_f").val(measureData.frequency + "Hz");
					$("#m_count").val(measureData.meetCount?"关":"开");
				}
				
				//var evtId = $("#evtId option:selected").text();
				
				var i = ( $("#evtId").val() == "-1") ? 0 : ($("#evtId option:selected").text() - 1);

				var eventData = data.dataPackage.eventData[i];
				if (eventData != null) {
					$("#e_tm2").val(
							(eventData.tm.year + 2000) + "-"
									+ padLeft(eventData.tm.month,2) + "-"
									+ padLeft(eventData.tm.date,2));
					$("#e_tm").val(
							eventData.tm.hour + ":" + padLeft(eventData.tm.minute,2)
									+ ":" + padLeft(eventData.tm.second,2));
					switch (eventData.event) {
					case 1:
						$("#e_event").val("手动打开断路器");
						break;
					case 2:
						$("#e_event").val("手动关闭断路器");
						break;
					case 3:
						$("#e_event").val("GPRS打开断路器");
						break;
					case 4:
						$("#e_event").val("GPRS关闭断路器");
						break;
					case 5:
						$("#e_event").val("合闸回路欠压");
						break;
					case 6:
						$("#e_event").val("PT1有压");
						break;
					case 7:
						$("#e_event").val("PT1过压");
						break;
					case 8:
						$("#e_event").val("PT2有压");
						break;
					case 9:
						$("#e_event").val("PT2过压");
						break;
					case 10:
						$("#e_event").val("后加速");
						break;
					case 11:
						$("#e_event").val("一次重合闸");
						break;
					case 12:
						$("#e_event").val("二次重合闸");
						break;
					case 13:
						$("#e_event").val("三次重合闸");
						break;
					case 14:
						$("#e_event").val("零序过流");
						break;
					case 15:
						$("#e_event").val("过流一段");
						break;
					case 16:
						$("#e_event").val("过流二段");
						break;
					case 17:
						$("#e_event").val("过流三段");
						break;
					case 18:
						$("#e_event").val("A相零序过流");
						break;
					case 19:
						$("#e_event").val("B相零序过流");
						break;
					case 20:
						$("#e_event").val("C相零序过流");
						break;
					default:
						alert("事件编号：" + eventData.event);
						break;
					}
					if ((eventData.event > 14) && (eventData.event < 18)) {
						$("#e_curE").val(eventData.pharse + ":"+ eventData.voltage + "A");
					} else if(eventData.event == 14 ) {
						$("#e_curE").val(eventData.voltage + "A");
					} else {
						$("#e_vtE").val(eventData.voltage + "V");
					}
				}
				
				var ledData = data.dataPackage.ledData.leds;
				if (ledData != null) {
					var ledOn = "image/led_red.png";
					var ledOff = "image/led_gray.png";
					if (ledData & 1) {
						$("#led_fen").attr("src", ledOn);
					} else {
						$("#led_fen").attr("src", ledOff);
					}
					if ((ledData >> 1) & 1) {
						$("#led_he").attr("src", ledOn);
					} else {
						$("#led_he").attr("src", ledOff);
					}
					if ((ledData >> 2) & 1) {
						$("#led_chongh").attr("src", ledOn);
					} else {
						$("#led_chongh").attr("src", ledOff);
					}
					if ((ledData >> 3) & 1) {
						$("#led_houjs").attr("src", ledOn);
					} else {
						$("#led_houjs").attr("src", ledOff);
					}
					if ((ledData >> 4) & 1) {
						$("#led_batq").attr("src", ledOn);
					} else {
						$("#led_batq").attr("src", ledOff);
					}
					if ((ledData >> 5) & 1) {
						$("#led_capq").attr("src", ledOn);
					} else {
						$("#led_capq").attr("src", ledOff);
					}
					if ((ledData >> 6) & 1) {
						$("#led_PT").attr("src", ledOn);
					} else {
						$("#led_PT").attr("src", ledOff);
					}
					if ((ledData >> 7) & 1) {
						$("#led_baohu").attr("src", ledOn);
					} else {
						$("#led_baohu").attr("src", ledOff);
					}
					if ((ledData >> 8) & 1) {
						$("#led_tongx").attr("src", ledOn);
					} else {
						$("#led_tongx").attr("src", ledOff);
					}
					if ((ledData >> 9) & 1) {
						$("#led_work").attr("src", ledOn);
					} else {
						$("#led_work").attr("src", ledOff);
					}
					if ((ledData >> 10) & 1) {
						$("#led_unlk").attr("src", ledOn);
					} else {
						$("#led_unlk").attr("src", ledOff);
					}
					if ((ledData >> 11) & 1) {
						$("#led_fugui").attr("src", ledOn);
					} else {
						$("#led_fugui").attr("src", ledOff);
					}
				}
				
				
				
			}).fail(function() {
				alert("连接出错！");
				blanking();
				$("#devId").empty();
				$("#devId").append($("<option>").text("设备编号...").val("-1"));
				$("#evtId").empty();
				$("#evtId").append($("<option>").text("事件编号...").val("-1"));
			});

}

function gotDevAddr() {

	blanking();
	
	jQuery.ajax({

		url : "devAddrAction.action",
		type : "post",
		dataType : "json",

	}).done(function(data) {

		var devIds = data.devIds;
		var devAddrs = data.devAddrs;
		
		$("#devAddr").empty();
		$("#devAddr").append($("<option>").text("选择设备地址...").val("-1"));

		$.each(devAddrs, function(n, value) {
			$("#devAddr").append($("<option/>", {
				value : n,
				text : value
			}));

		});
// setInterval("gotClick();", 100);
	});
}
function gotEvtId() {
	
	$("#ff_evt input").val("");
	
	jQuery.ajax({
		
		url : "evtIdAction.action",
		type : "post",
		dataType : "json",
		data : {
			// action中属性名:值
			devId : $("#devId option:selected").text()
		}
	}).done(function(data) {
		
		var evtIds = data.evtIds;
		
		$("#evtId").empty();
		$("#evtId").append($("<option>").text("事件编号...").val("-1"));
		
		$.each(evtIds, function(n, value) {
			$("#evtId").append($("<option/>", {
				value : n,
				text : value
			}));
			
		});
// setInterval("gotClick();", 100);
	});
}

function padLeft(str,lenght){
	return (str <10) ? ("0"+str) : str;
} 

function blanking(){

	$("#ff_top input").val("");
	$("#ff_usr input").val("");
	$("#ff_val input").val("");
	$("#ff_val_ input").val("");
	$("#ff_msr input").val("");
	$("#ff_evt input").val("");
	
	var qie = "image/qie.png";
	$("#ff_borad img").attr("src", qie);
	
	var ledOff = "image/led_gray.png";
	$("#led_fen").attr("src", ledOff);
	$("#led_he").attr("src", ledOff);
	$("#led_chongh").attr("src", ledOff);
	$("#led_houjs").attr("src", ledOff);
	$("#led_batq").attr("src", ledOff);
	$("#led_capq").attr("src", ledOff);
	$("#led_PT").attr("src", ledOff);
	$("#led_baohu").attr("src", ledOff);
	$("#led_tongx").attr("src", ledOff);
	$("#led_work").attr("src", ledOff);
	$("#led_unlk").attr("src", ledOff);
	$("#led_fugui").attr("src", ledOff);
	
} 
