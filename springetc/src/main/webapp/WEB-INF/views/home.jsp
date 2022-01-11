<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring 기타</title>
</head>
<body>
	<a href="/echo">에코</a><br/>
	<a href="/chat">채팅</a><br/>
	<a href="#" id="push">웹 푸시</a><br/>
	메시지:<span id="disp"></span><br/>
	<a href="#" id="proxy">기상청 데이터 proxy 요청</a><br/>
	<a href="/mailsend" >메일보내기</a><br/>
</body>

<script>
	window.addEventListener("load", function(e){
		document.getElementById("push").addEventListener(
				'click', function(e){
			var eventSource = new EventSource('push');
			eventSource.addEventListener('message', function(event){
				document.getElementById("disp").innerHTML = 
					event.data + "<br/>";
			});
		});
		
		document.getElementById("proxy").addEventListener(
				'click', function(e){
				//ajax 요청할 URL 과 객체를 생성
			var param="http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109"
			var request=new XMLHttpRequest();
					
			//요청 생성
			request.open("get", 'proxy?addr=' + param, true);
			//요청 전송
			request.send('');
					
			//ajax 요청 응답이 오면
			request.addEventListener('load', function show(e){
					alert(request.responseXML);
			});
		});
	});
</script>
</html>