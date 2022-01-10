<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 소켓 - 에코</title>
</head>
<body>
	<input type="text" id="message" />
	<input type="button" id="sendbtn" value="전송" />
</body>

<script>
	window.addEventListener("load", function(e){
		var message = document.getElementById("message");
		var sendbtn = document.getElementById("sendbtn");
		
		
		sendbtn.addEventListener("click", function(e){
			//웹 소켓 생성
			var websocket = new WebSocket(
					"ws://localhost/echo-ws");
			//웹 소켓이 연결되면 
			websocket.addEventListener("open", function(e){
				//메시지 전송
				websocket.send(message.value)
				
				websocket.addEventListener("message", function(e){
					//전송된 메시지
					var msg = e.data;
					alert(msg);
					websocket.close();
				})
			});
		})
		
	});

</script>
</html>