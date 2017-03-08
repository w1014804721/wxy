	var btns = document.getElementsByClassName("tab-toggle");
	var wrappers = document.getElementsByClassName("tab-wrapper");
    
//  为btns绑定事件
	for(var i = 0;i<5;i++){
		(function(i){//闭包传参数
		    btns[i].onclick = function(){
		    	for(var j = 0;j<5;j++){					
					wrappers[j].style.display = "none";
					if(j==i){
						wrappers[i].style.display = "block";
					}
				}				
			};
		})(i);
	}
	
	
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
        
        
        

	
	
