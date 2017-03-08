//	document.getElementById("search-btn").onclick = function(){
//		toggleSearch();
//	};

//*******************************样式相关的JS********************************
	var count = 1;
	function toggleSearch(){
		var areaInput = document.getElementsByClassName("search-input")[0];
    	var sub_btn = document.getElementsByClassName("search-submit")[0];
		if(count%2==0){
		  areaInput.style.left = 150+"px";
//		  areaInput.style.display = "none";
          areaInput.style.display = "none";                 
		  sub_btn.style.backgroundColor = "#e2dee0";
		  count++;
		}else if(count%2==1){
		  areaInput.style.left = 0+"px";
		  areaInput.style.display = "block";
//		  sub_btn.style.backgroundColor = "#000";
		  count++;
		}
	}
	
	function personPanelLog(){
		document.getElementById("personlog-content").style.display = "block";
		document.getElementById("teamlog-content").style.display = "none";
		document.getElementsByClassName("personlog-btn")[0].style.color = "#e94e38";
		document.getElementsByClassName("teamlog-btn")[0].style.color = "#787d82";
	}
	function teamPanelLog(){
		document.getElementById("personlog-content").style.display = "none";
		document.getElementById("teamlog-content").style.display = "block";
		document.getElementsByClassName("personlog-btn")[0].style.color = "#787d82";
		document.getElementsByClassName("teamlog-btn")[0].style.color = "#e94e38";
	}
	
	function personPanel(){
		document.getElementById("person-content").style.display = "block";
		document.getElementById("team-content").style.display = "none";
		document.getElementsByClassName("personreg-btn")[0].style.color = "#e94e38";
		document.getElementsByClassName("teamreg-btn")[0].style.color = "#787d82";
	}
	function teamPanel(){
		document.getElementById("person-content").style.display = "none";
		document.getElementById("team-content").style.display = "block";
		document.getElementsByClassName("personreg-btn")[0].style.color = "#787d82";
		document.getElementsByClassName("teamreg-btn")[0].style.color = "#e94e38";
	}
	
//********************************Ajax请求*************************************************	
	
//	个人用户登录的ajax
function personLog() {
	if($("#tel").val()==''){
			alert("账号不能为空~~");
			return false;
	}
	else if($("#password").val()==''){
		alert("密码不能为空~~");
		return false;
	}
	else{
		var loginData = {
			"tel":$("#tel").val(),
			"password":$("#password").val()
		};
		$.ajax({
			type:"post",
			url:"personLogin",
			data:loginData,
			dataType:"json",
			success:function(data){
				if(data.length!=1){
					$("#login-div").hide();
					$("#reg-div").hide();
					$(".welcome").show();
					$("#user-mes").html(data);//这个地方要显示返回的用户昵称
				}else
				{
					alert("登录失败");
				}
			}
		});
	}
}

function teamLog() {
	if($("#team-tel").val()==''){
			alert("账号不能为空~~");
			return false;
		}
		else if($("#team-password").val()==''){
			alert("密码不能为空~~");
			return false;
		}
		else{
			var loginData = {
				"tel":$("#team-tel").val(),
				"password":$("#team-password").val()
			};
			$.ajax({
				type:"post",
				url:"teamLogin",
				data:loginData,
				dataType:"json",
				success:function(data){
					if(data.length!=1){
						$("#login-div").hide();
						$("#reg-div").hide();
						$(".welcome").show();
						$("#user-mes").html(data);//这个地方要显示返回的用户昵称
					}else
					{
						alert("登录失败");
					}
				}
			});
		}
}
//个人用户的注册	
function personReg() {
	var tel = $("#reg-tel").val();
    	var name = $("#reg-name").val();
    	var email = $("#reg-email").val();
    	var birthday = $("#datepicker").val();
    	var password1 = $("#reg-password").val();
    	var password2 = $("#passwordNext").val();
    	var sex =$("input[name='Sex']:checked").val();
    	var re= /^1\d{10}$/;
    	var re2 = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    	var birth = birthday.split("-");
    	var date2 = new Date();
    	var a = date2.getFullYear()- parseInt(birth[0]);
    	var b = date2.getMonth()+1 - parseInt(birth[1]);
    	var c = date2.getDate() - parseInt(birth[2]);

    	if(tel==''){
    		alert("账号不能为空，请输入您的手机号作为账号~");
    	}else if(!re.test(tel)){
    		alert("请输入正确格式的手机号~");
    	}else if(!re2.test(email)){
    		alert("请输入正确格式的邮箱~");
    	}else if(name==''){
    		alert("昵称不能为空~");
    	}else if(birthday==''){
    		alert("请选择出生日期~");
    	}else if(a<0){
    		alert("请输入正确生日哟~");
    	}else if(a=0&&b<0){
    		alert("请输入正确生日哟~");
    	}else if((a=0)&&(b=0)&&(c<0)){
    		alert("请输入正确生日哟~");
    	}else if(password1==''){
    		alert("请输入您设置的密码~");
    	}
    	else if(password2==''){
    		alert("请再次输入您设置的密码~");
    	}
    	else if(password1!==password2){
    		alert("两次输入的密码不一致，请确认后再输入~");
    	}
    	else {
    		var data = {
    			"tel":tel,
    			"email":email,
    			"name":name,
    			"sex":sex,
    			"birthday":birthday,
    			"password":password1
    		}
    		$.ajax({
    			type:"post",
    			url:"personRegister",
    			data:data,
    			dataType:"json",
    			success:function(data){
    				if(data==1){
    					alert("注册成功请登录~");
    				}
    				if(data==2){
    					alert("注册失败");
    				}
    			}
    		});
    	}
}
    
//  团队用户的注册
function teamReg() {
	var tel = $("#regt-tel").val();
    	var name = $("#regt-name").val();
    	var city = $("#regt-area").val();
    	var email = $("#regt-email").val();
    	var password1 = $("#regt-pass").val();
    	var password2 = $("#regt-passN").val();
    	var re1 = /^1\d{10}$/;
    	var re2 = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;

    	
    	if(tel==''){
    		alert("账号不能为空，请输入您的手机号作为账号~");
    	}else if(!re1.test(tel)){
    		alert("请输入正确格式的手机号~");
    	}else if(name==''){
    		alert("队名不能为空~");
    	}else if(city==''){
    		alert("所属城市不能为空~")
    	}else if(email==''){
    		alert("邮箱不能为空~");
    	}else if(!re2.test(email)){
    		alert("请输入正确格式的邮箱~");
    	}else if(password1==''){
    		alert("请输入您设置的密码~");
    	}
    	else if(password2==''){
    		alert("请再次输入您设置的密码~");
    	}
    	else if(password1!==password2){
    		alert("两次输入的密码不一致，请确认后再输入~");
    	}else {
    		var data = {
    			"tel":tel,
    			"teamName":name,
    			"city":city,
    			"email":email,
    			"password":password1  			
    		}
    		$.ajax({
    			type:"post",
    			url:"teamRegister",
    			data:data,
    			dataType:'json',
    			success:function(data){
    				if(data==1){
    					alert("注册成功请登录~");
    				}
    				if(data==2){
    					alert("注册失败");
    				}
    			}
    		});
    	}
}
    

   

