//<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
  jQuery(function($){
         // bind event handlers to modal triggers
         $('body').on('click', '.trigger', function(e){
            e.preventDefault();
            $('#test-modal').modal().open();
         });

         // attach modal close handler
         $('.modal .close').on('click', function(e){
            e.preventDefault();
            $.modal().close();
         });

         // below isn't important (demo-specific things)
         $('.modal .more-toggle').on('click', function(e){
            e.stopPropagation();
            $('.modal .more').toggle();
         });


          $('body').on('click', '.vmore1', function(e){
            e.preventDefault();
            $('#vmore1-modal').modal().open();
         });

         $('body').on('click', '.chou1', function(e){
            e.preventDefault();
            $('#chou1-modal').modal().open();
         });
			
});
checkUser();
function checkUser(){
	$.ajax({
		type:"post",
		url:"isLogIn",
		async:true,
		dataType:'json',
		success:function(data){
			if(data !== ''){
				handleData(data);				
			}
		}
	});
	function handleData(data){
		$(".login").hide();
		$(".register").hide();
		$("#user-mes").text = data;
		$(".welcome").show();
	}
}

initInfo();
function initInfo() {
	initWish();
	initLab();
	//initVteer();
}
function initWish() {
    $.ajax({
        type: "GET",
        url: "indexWish",
        dataType : 'json',
        success: function (data) {
            handleData(data);
        }
    });
    function handleData(data) {
//      data = JSON.parse(data);
        for (var i = 0; i < data.length; i++) {
            $("#list-last-wish").append("<div class='col-md-3 index-wish'><div class='grid1'><div class='view view-first'><div class='index_img'><img src='"
            							+ data[i].wishpic + "' class='img-responsive' alt=''/></div><div class='mask'><div class='info trigger'>也想领取心愿</div><ul class='mask_img'><li class='star'><img src='img/star.png'/></li><li class='set'><img src='img/set.png'/></li></ul></div></div><i class='home'></i><div class='inner_wrap'><h3>"
            							+ data[i].simpleDes + "</h3><ul class='star1'><h4 class='green nick'>" 
            							+ data[i].publisher + "</h4><li><a class='btna trigger' href='wishWall.html'>心愿详情</a></li></ul></div></div></div>");
        }
    }
}
function initLab() {
	var reporters = {};
	$.ajax({
		type: "GET",
        url: "indexActivity",
        dataType:'json',
        success: function (data) {
        	handleData(data);
        }
	});
	function handleData(data) {
//		data = JSON.parse(data);
		for(var i=0;i<data.length;i++) {
			$("#list-last-labs").append("<div class='col-md-6'><div class='grid1'><div class='index_img'><img src='"
										+data[i].activitypic+"' class='img-responsive' alt=''/></div><i class='m_home'> </i><ul class='vision'><li>发布者："
										+data[i].publisher+"</li><li class='desc'><a> <img src='img/star1.png' alt=''>期望人数("
										+data[i].expectNum+")</a></li></ul><div class='inner_wrap1'><ul class='item_module'><li class='module_left'><img src='img/m1.jpg' class='img-responsive' alt=''/></li><li class='module_right'><img src='img/m_star.png' class='img-responsive' alt=''/><h5>"
										+data[i].simpleDes+"</h5><p>"
										+data[i].complexDes+"</p><a href='activity.html' class='content_btn'>....read more</a></li><div class='clearfix'> </div></ul></div></div></div>");
		}
	}
}
      //个人登陆
	  function personLog(){
	  	console.log("1");
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
						  $("#user-mes").html(data);
//						  $("#myModal1").hide();
						  $('#myModal1').modal('hide');//这个地方要显示返回的用户昵称
					  }else
					  {
						  alert("登录失败");
					  }
				  }
			  });
		  }
	  }
	  //团队登陆
	  function teamLog(){
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
	  };


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
	  //个人用户注册
	  function personReg(){
	  	  console.log("1");
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
		  }else if((a=0)&&(b<0)){
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
			  };
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
	  };

	  //  团队用户的注册
	  function teamReg(){
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
			  };
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
	  };



