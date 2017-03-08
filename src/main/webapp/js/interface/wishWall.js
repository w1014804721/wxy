var pubTotal;//总条数
getCount();
//获取微心愿的总条数
function getCount(){
	$.ajax({
	 	type:"post",
	 	url:"wishCount",
        dataType:"json",
        success:function(data){
			pubTotal=data;
        	$('#page-ctrl').extendPagination({
	            totalCount: pubTotal,
	            showCount: 10,
	            limit: 9,
    	  	});
			createWishes(1,pubTotal,9);
        }          
	});
}
				 
//根据点击的页数显示相应范围的微心愿		 
function createWishes(id,all,limit){
	$.ajax({
		type:"get",
		url:"wishShow",
		data:{	
			"id":id,
			"all":all,
			"limit":limit
		},
		dataType:"json",
		success:function(data){
			createWish(data);
		}
	});
}
        
function createWish(data){
    var $wishWall=$(".wishWall");
    $wishWall.empty();
	var sexpic;
	var len = data.length;
	var state;
	for(var i=0;i<len;i++){
		if(data[i].sex=='女'){
    		sexpic = "img/女2.png";
    	}else if(data[i].sex=='男'){
    		sexpic = "img/男2.png";
    	}
    	if(data[i].state == 0){
    		state = '未完成';
    	}
		var newWish = '<div class="wishes"><div class="wish-up"><div class="image-wrap"><img src="'+data[i].wishpic+'" class="showwish"/></div><div class="wish-status"><span class="ribbon3">'
				+state+'</span></div></div><div class="wish-down"><div class="publisher-show"><div class="publisher-img publisher"><img src="'
				+data[i].userimg+'"></div><div class="publisher pub-sex"><img src="'+sexpic+'"></div><div class="publisher pub-age"><p>'
				+data[i].age+'</p></div><div class="line publisher"></div><div class="publisher pub-name"><p>'
				+data[i].publisher+'</p></div><div class="clearfix"></div></div><div class="wish-topic"><p>'
				+data[i].simpleDes+'</p><div class="read-btn" onclick="showMoreWish('+data[i].wishId+')" type="button" class="btn btn-primary btn-lg" data-toggle="modal" id="'
				+data[i].wishId+'" data-target="#wishModal">心愿详情</div></div></div></div>';
        $wishWall.append(newWish);
    }
}		       
//心愿的查看详情  
 function showMoreWish(id) {
        var wishid = id;
        var _data = {"wishId": wishid};
    $.ajax({
        url: "wishMore",
        type: "get",
        data: _data,
        dataType: "json",
        success: function (data) {
            data = data[0];
            $("#pubName").text(data.publisher);
            var picsrc;
            if (data.sex == '女') {
                picsrc = "img/女2.png";
            } else if (data.sex == '男') {
                picsrc = "img/男2.png";
            }
            var date = new Date(data.date);
            var pubtime = ''+date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDay()+'';
            $("#pubage").text(data.age);
            $("#pubtime").text(pubtime);
            $("#deadtime").text(data.expectTime);
            $("#expectper").text(data.expectNum);
            $("#deadline").text(data.deadLine);
            $("#pubsex").attr('src',picsrc);
            $("#wish-pic").attr("src",data.wishpic);
            $("#smallTitle").text(data.simpleDes);
            $("#complexDes").text(data.complexDes);
            $(".lingqu-btn").attr('id', data.wishId);  
//          $(".lingqu-btn").on('click',connect());
        }
    });
}

//领取心愿后弹出心愿发布者的联系方式      
function connect() {
	alert('11111111111');
	var baomingrenshu = $("#baomingrenshu").val();
	if(baomingrenshu == ''){
		alert('请输入完成心愿的人数~');
	}
	var id = {"wishId":$(this).attr("id"),"baomingrenshu":baomingrenshu};
	$.ajax({
		type:"post",
		url:"wishDraw",
		data:id,
		dataType:"json",
		success:function(data){
            if(data.length==0){
                alert("这个心愿已经被别人领取了，请选择其他的心愿")
            }else
            if(data == -2){
                alert("请不要领取自己的心愿");
            }else
            if (data == -1) {
                alert("请不要重复领取。")
            } else if(data == 1) {
                data = data[0];
                alert("请及时与发布心愿者联系~\n手机号：" + data.tel + "\n邮箱：" + data.email + "\n");
            }
        }
	});
}

