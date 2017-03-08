//		<!--个人领取的微心愿展示-->
//		<!--获取当前用户领取的微心愿数目以便生成选择按钮-->
	var pubTotal1;
	$("#lingqu-wrapper").empty();
	$("#lingqu").click(function(){
		$("#lingqu-wrapper").empty();
		var newnode1 = "<div class='lingqucontent'></div>";
		$("#lingqu-wrapper").append(newnode1);
		var newnode2 = "<div class='pagepub-ctrl'></div>";
		$("#lingqu-wrapper").append(newnode2);
		$.ajax({
			type:"post",
			url:"myDrawWishCount",
			success:function(data){
				pubTotal1=data;
				$('.pagepub-ctrl').extendPagination({
					totalCount: data,
					showCount: 10,
					limit: 1,
				});
//					$(".fabucontent").empty();
//					$('#fabu-wrapper').appendChild()
				createDrawWishes(1,pubTotal1,1);
			}
		});
	});
//		<!--根据点击的按钮显示相应区段的微心愿-->			
//     此处修改了插件的源代码 
    function createDrawWishes(id,all,limit){
		$.ajax({
			type:"get",
			url:"myDrawWish",
			data:{	
				"id":id,
				"all":all,
				"limit":limit,
			},
			dataType:"json",
			success:function(data){
				$(".lingqucontent").empty();
				createDrawWish(data);
			}
		});
	}
    function createWish(data){
    	$("#lingqu-wrapper").empty();
    	var sexpic;        	
    	var len = data.length;
    	for(var i=0;i<len;i++){
    		if(data[i].sex=='女'){
        		sexpic = "img/女2.png";
        	}else if(data[i].sex=='男'){
        		sexpic = "img/男2.png";
        	}
    		var newWish = '<div class="wishes"><div class="wish-up"><div class="image-wrap"><img src="'+data[i].wishpic+'" class="showwish"/></div><div class="wish-status"><span class="ribbon3">'
    				  +data[i].status+'</span></div></div><div class="wish-down"><div class="publisher-show"><div class="publisher-img publisher"><img src="'
    				  +data[i].userimg+'"></div><div class="publisher pub-sex"><img src="'+sexpic+'"></div><div class="publisher pub-age"><p>'
    	  			  +data[i].age+'</p></div><div class="line publisher"></div><div class="publisher pub-name"><p>'
    	  			  +data[i].publisher+'</p></div><div class="clearfix"></div></div><div class="wish-topic"><p>'
    	  			  +data[i].simpleDes+'</p></div></div></div>'
    	    $("#lingqu-wrapper").append(newWish);
    	}  	  			  
    }				
/*******************************************************************************************/		
//		<!--发布的心愿展示，涉及到修改心愿的状态-->
	var pubTotal2;
	$("#fabu").click(function(){
	$("#fabu-wrapper").empty();
	var newnode1 = "<div class='fabucontent'></div>";
	$("#fabu-wrapper").append(newnode1);
	var newnode2 = "<div class='pagepub-ctrl'></div>";
	$("#fabu-wrapper").append(newnode2);
		$.ajax({
			type:"post",
			url:"myWishCount",
			success:function(data){
				pubTotal2=data;
				$('.pagepub-ctrl').extendPagination({
		            totalCount: data,
		            showCount: 10,
		            limit: 1,
				});
	//					$(".fabucontent").empty();
	//					$('#fabu-wrapper').appendChild()
				createWishes(1,pubTotal2,1);
			}
		});
	});
//		<!--根据点击的按钮显示相应区段的微心愿-->
//     此处修改了插件的源代码 
        function createWishes(id,all,limit){
        	$.ajax({
        		type:"get",
        		url:"myPubWish",
        		data:{	
        			"id":id,
					"all":all,
					"limit":limit,
					},
        		dataType:"json",
        		success:function(data){
					$(".fabucontent").empty();
        			createWish(data);
        		}
        	});
        }       
        function createWish(data){

        	var sexpic;
        	var len = data.length;
//			var content = ""
//			$("#fabu-wrapper").append("")
        	for(var i=0;i<len;i++){
        		if(data[i].sex=='女'){
	        		sexpic = "img/女2.png";
	        	}else if(data[i].sex=='男'){
	        		sexpic = "img/男2.png";
	        	}
        		var newWish = '<div class="wishes"><div class="wish-up"><div class="image-wrap"><img src="'+data[i].wishpic+'" class="showwish"/></div><div class="wish-status"><span class="ribbon3">'
        				  +data[i].showit+'</span></div></div><div class="wish-down"><div class="publisher-show"><div class="publisher-img publisher"><img src="'
        				  +data[i].userimg+'"></div><div class="publisher pub-sex"><img src="'+sexpic+'"></div><div class="publisher pub-age"><p>'
        	  			  +data[i].age+'</p></div><div class="line publisher"></div><div class="publisher pub-name"><p>'
        	  			  +data[i].publisher+'</p></div><div class="clearfix"></div></div><div class="wish-topic"><p>'
        	  			  +data[i].simpleDes+'</p><div class="read-btn" onclick="showMoreWish('+data[i].wishId+')" type="button" class="btn btn-primary btn-lg" data-toggle="modal" id="'
        	  			  +data[i].wishId+'" data-target="#wishpubModal">心愿详情</div></div></div></div>'
        	    $(".fabucontent").append(newWish);
        	}  	  			  
        }				

//		<!--心愿详情的信息展示-->
//			$(".read-btn").click(function () {
			function showMoreWish(id){
				var wishid = id;
				var _data = {"wishId": wishid};
				console.log(wishid);
				$.ajax({
					url: "wishMore",
					type: "get",
					data: _data,
					dataType: "json",
					success: function (data) {
						data=data[0];
						alert(data.publisher);
						$("#pubname").text(data.publisher);
						var picsrc;
						if (data.sex == '女') {
							picsrc = "img/女2.png";
						} else if (data.sex == '男') {
							picsrc = "img/男2.png";
						}
						$("#pubage").text(data.age);
						$("#pubtime").text(data.date);
						$("#deadtime").text(data.expectTime);
						$("#expectper").text(data.expectNum);
						$("#deadline").text(data.deadLine);
						$("#wish-pic").attr("src",data.wishpic);
						$("#smallTitle").text(data.simpleDes);
						$("#complexDes").text(data.complexDes);
						$(".savestatus-btn").attr('id',data.wishId)  ;
					}
				});
			}

//			});

			$(".savestatus-btn").click(function () {
				var status = $("#wishStatus").val();
				var wishid = $(".savestatus-btn").attr("id");
				_data = {
					"status": status,
					"wishid": wishid
				};
				alert(status);
				$.ajax({
					type: "post",
					url: "changeWishStatus",
					data: _data,
					dataType: "json",
					success: function (data) {
						if (data == 1) {
							alert("修改成功~");
						} else if (data == 2) {
							alert("修改失败~");
						}
					}
				});
			});
			//领取的招募的信息
			$("#zhiyuan").click(function(){
			var drawCount;
			$.ajax({
				type:"post",
				url:"personDrawActCount",
				dataType:'json',
				success:function(data) {
					drawCount=data;
					$('.pagepub-ctrl').extendPagination({
						totalCount: data,
						showCount: 10,
						limit: 1,
					})
					createActivities(1,drawCount,1);
				}
			});
			})
			function createActivities(id,all,limit){
			var	data={
					"id":id,
					"all":all,
					"limit":limit}
        	$.ajax({
        		type:"post",
        		url:"personDrawAct",
        		data:data,
        		dataType:"json",
        		success:function(data){
        			createActivity(data);
        		}
        	});
        }
        
        function createActivity(data){
        	$(".activityShow").empty();      	
        	var len = data.length;
        	for(var i=0;i<len;i++){       		
        		var newactivity = '<div class="activities"><div class="up-act"><img src="'
        						  +data[i].activitypic+'" /></div><h5>'
        						  +data[i].simpleDes+'</h5><p>'
        						  +data[i].complexDes+'</p><p class="act-publisher">发布者：<span>'
        						  +data[i].publisher+'</span></p><p class="act-deadline">截止时间：<span>'
        						  +data[i].deadLine+'</span></p></div></div>'
        	    $(".activityShow").append(newactivity);       	    
        	}  	  			  
        }	
/**************************************************************************************************/			
//		<!--发布心愿-->
//		<!--上传的图片后台要返回一个文件路径，分两步上传-->
			function pubWish(){
//				alert('1111111111111');
				var simpleDes = $("#simpleDes").val();
				var expectNum = $("#expectNum").val();
				var expectTime = $("#expectTime").val();
				var complexDes = $("#wishText").val();
				var time = expectTime.split("-");
		    	var date2 = new Date();
				var picSrc=path;
				alert(path);
		    	var a = date2.getFullYear()- parseInt(time[0]);
		    	var b = date2.getMonth()+1 - parseInt(time[1]);
		    	var c = date2.getDate() - parseInt(time[2]);
				var _data = {
					"simpleDes":simpleDes,
					"expectNum":expectNum,
					"expectTime":expectTime,
					"complexDes":complexDes,
					"picSrc":picSrc,
				};
				var re = /^[-+]?\d+$/;
				if(simpleDes==''){
					alert("请输入微心愿简述~");
				}else if(expectNum==''){
					alert("请输入期待完成心愿的人数~");
				}else if(!re.test(expectNum)){
					alert("请输入正确格式的数字~");
				}else if(expectNum<0){
					alert("请输入正确格式的数字~");
				}
				else if(expectTime==''){
					alert("请输入期待心愿完成的时间~");
				}else if(a>0){
		    		alert("请输入正确时间哟~");
		    	}else if(a=0&&b>0){
		    		alert("请输入正确时间哟~");
		    	}else if((a=0)&&(b=0)&&(c>0)){
		    		alert("请输入正确时间哟~");
		    	}else if(complexDes==''){
					alert("请输入微心愿的具体描述，以便大家更好的帮您完成~");
				}else if(picSrc==''){
					alert("请上传微心愿相关配图~");
				}
				$.ajax({
					type:"post",
					url:"wishPub",
					dataType:"json",
					data:_data,
					success:function(data){
						if(data==1){
							alert("发布成功~");
						}else if(data=2){
							alert("发布失败~");
						}
					}
				});
			}
/*******************************************************************************************/
//      <!--显示个人信息-->
$.ajax({
	type:"post",
	url:"personMess",
	dataType:"json",
		success:function(data){
		data=data[0];
		$("#showName").text(data.name);
		$("#userId").text(data.tel);
		$("#userage-show").text(data.age);
		$("#userbirth-show").text(data.birthday);
		$("#username-show").text(data.name);
		$("#usersex-show").text(data.sex);
	}
});	
//      <!--修改个人信息-->
        	$("#save-btn").click(function(){
        		$.ajax({
        			type:"post",
        			url:"personMessChange",
        			data:{"userName":$("#username-show").text()},
        			success:function(data){
        				if(data==1){
        					alert("修改成功~");
        				}else if(data==2){
        					alert("修改失败~");
        				}
        			}
        		});
        	});
