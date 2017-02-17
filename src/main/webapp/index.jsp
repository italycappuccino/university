<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Home</title>
</head>
<body>
<h2>Hello World!</h2>
<div>
城市编码（区号）:<input type="text" id="cityCode" name="cityCode"/>
<span id="msg" style="color: red;"></span>
</div>

<script src="${root.contextPath }/static/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript">

/**原始处理方法*/
/**
document.getElementById("cityCode").onkeydown=function(event){
	if(event.keyCode==13){
		var request = new XMLHttpRequest();
		request.open("GET","${root.contextPath}/queryCity?cityCode="+document.getElementById("cityCode").value);
		request.send();
		request.onreadystatechange=function(){
			if(request.readyState==4){
			    if(request.status==200){
			    	var data = JSON.parse(request.responseText);
			    	if(data.success){
			    		document.getElementById("msg").innerHTML="1响应成功，城市名称："+data.city.name;
			    	}else{
			    		document.getElementById("msg").innerHTML="处理失败"+data.msg;
			    	}
			    }else{
			    	document.getElementById("msg").innerHTML="error,status code="+request.status;
			    }
			}else{
				document.getElementById("msg").innerHTML="error,readyState="+request.readyState;
			}
		}
	}
}
*/



/**JQuery 处理方式*/
/**
$(document).ready(function(){
	$("#cityCode").keydown(function(event){
	    if(event.keyCode==13){
	        $.ajax({
	        	type:"GET",
	        	url:"${root.contextPath}/queryCity?cityCode="+$("#cityCode").val(),
	        	dataType:"json",
	        	success:function(data){
	        		if(data.success){
	        			$("#msg").html("2响应成功，城市名称："+data.city.name);
	        		}else{
	        			$("#msg").html("处理失败"+data.msg);
	        		}
	        	},
	        	error:function(jqXHR){
	        		alert("发生错误："+jqXHR.status);
	        	}
	        });
	    }
	});
});
*/

/*处理跨域请求
一、后台代理
二、jsonp
三、XMLHttpRequest Level2,,,XHR2,,,HTML5,,,,IE10以下不支持。。。
服务器端加以下两行代码，这是php代码。。。。
header("Access-Control-Allow-Origin:*");
header("Access-Control-Allow-Methods:POST,GET");


*/
$(document).ready(function(){
	$("#cityCode").keydown(function(event){
	    if(event.keyCode==13){
	        $.ajax({
	        	type:"GET",
	        	url:"${root.contextPath}/queryCityWithJsonp?cityCode="+$("#cityCode").val(),
	        	dataType:"jsonp",
	        	jsonp:"callback",
	        	success:function(data){
	        		if(data.success){
	        			$("#msg").html("2响应成功，城市名称："+data.city.name);
	        		}else{
	        			$("#msg").html("处理失败"+data.msg);
	        		}
	        	},
	        	error:function(jqXHR){
	        		alert("发生错误："+jqXHR.status);
	        	}
	        });
	    }
	});
});

</script>
</body>
</html>