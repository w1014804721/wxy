//<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>

$(document).ready(function(){
    alert("123")
	//checkUser();
	initInfo();
});
function initInfo() {
	initWish();
	initLab();
	//initVteer();
}
function initWish() {
    $.ajax({
        type: "GET",
        url: "indexWish",
        success: function (data) {
            handleData(data);
        }
    });
    function handleData(data) {
        data = JSON.parse(data);
        for (var i = 1; i <= 4; i++) {
            $("#list-last-wish").append("<div class='col-md-3'><div class='grid1'><div class='view view-first'><div class='index_img'><img src='"
            							+ data[i].wishPic + "' class='img-responsive' alt=''/></div><div class='mask'><div class='info trigger'>也想领取心愿</div><ul class='mask_img'><li class='star'><img src='img/star.png'/></li><li class='set'><img src='img/set.png'/></li></ul></div></div><i class='home'></i><div class='inner_wrap'><h3>"
            							+ data[i].wishDes + "</h3><ul class='star1'><h4 class='green nick'>" 
            							+ data[i].wishPub + "</h4><li><a class='btna trigger' href='wishWall.html'>心愿详情</a></li></ul></div></div></div>");
        }
    }
}
function initLab() {
	var reporters = {};
	$.ajax({
		type: "GET",
        url: "indexActivity",
        success: function (data) {
        	handleData(data);
        }
	});
	function handleData(data) {
		data = JSON.parse(data);
		for(var i=1;i<=4;i++) {
			$("#list-last-labs").append("<div class='col-md-6'><div class='grid1'><div class='index_img'><img src='"
										+data[i].activityPic+"' class='img-responsive' alt=''/></div><i class='m_home'> </i><ul class='vision'><li>发布者："
										+data[i].publisher+"</li><li class='desc'><a> <img src='img/star1.png' alt=''>期望人数("
										+data[i].expectNum+")</a></li></ul><div class='inner_wrap1'><ul class='item_module'><li class='module_left'><img src='img/m1.jpg' class='img-responsive' alt=''/></li><li class='module_right'><img src='img/m_star.png' class='img-responsive' alt=''/><h5>"
										+data[i].actComDes+"</h5><a href='activity.html' class='content_btn'>....read more</a></li><div class='clearfix'> </div></ul></div></div></div>");		
		}
	}
}
//function checkUser() {
//	var name = getCookie("uname");
//	var password = getCookie("upswd");
//	var data_login = {"mname":name,"mpswd":password};
//	infoCheck(data_login);
//}
//function infoCheck(data_info) {
//	$.ajax({
//		type: "POST",
//		url: "../wy_login.php",
//		data: data_info,
//		success: function (data) {
//			handleData(data);
//		}
//	});
//	function handleData(data) {
//		data = JSON.parse(data);
//		var statueInfo = data.rcode;
//		if (statueInfo != 0) {
//			clearCookie("uname");
//			clearCookie("upswd");
//			window.location.href = "index.html";
//		}
//		return true;
//	}
//}