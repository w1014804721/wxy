
        // 修改用户名
        $("#username-change").click(function(){
        	$("#username-form").show();
        	$("#username-change").hide();
        	$("#username-show").hide();
        });

        $("#username-btn").click(function(){
        	
        	if($("#form-field-username").val()==""){
        		alert("用户名不能为空");
        	}
        	$("#username-form").hide();
        	$("#username-show").text($("#form-field-username").val());
        	$("#username-show").show();
        	$("#username-change").show();
        	$("#username-form").hide();
        });

            //修改真实姓名
        $("#userRealname-change").click(function(){
        	$("#userRealname-form").show();
        	$("#userRealname-change").hide();
        	$("#userRealname-show").hide();
        });

        $("#userRealname-btn").click(function(){
        	$("#userRealname-form").hide();
        	if($("#form-field-userRealname").val()==""){
        		alert("姓名不能为空");
        	}
        	$("#userRealname-show").text($("#form-field-userRealname").val());
        	$("#userRealname-show").show();
        	$("#userRealname-change").show();  	       	       	        	
        });
           

        //修改联系方式
        $("#usertel-change").click(function(){
        	$("#usertel-form").show();
        	$("#usertel-change").hide();
        	$("#usertel-show").hide();
        });
         
        $("#usertel-btn").click(function(){
        	$("#usertel-form").hide();
       	
        	if($("#form-field-usertel").val()==""){
        		alert("电话不能为空");
        	}
        	
        	// for(int i = 0;i<$("#form-field-usertel").val().length;i++){
        	// 	if($("#form-field-usertel").val().charCodeAt(i)<48||$("#form-field-usertel").val().charCodeAt(i)>57){
        	// 		alert("请输入正确格式的手机号码");
        	// 	}
        	// }
        	$("#usertel-show").text($("#form-field-usertel").val());
        	$("#usertel-show").show();
        	$("#usertel-change").show();  	       	       	        	
        });
      	