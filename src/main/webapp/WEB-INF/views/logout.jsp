<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	</head>	
	<body>
		<form action= "${pageContext.request.contextPath}/logout"  method="post">
			<button type="submit">Logout</button>
		</form>
	</body>
</html>

<!-- ${pageContext.request.contextPath} 현재 컨텍스트 경로를 동적으로 삽입. 
거기에 /logout 을 붙이다 보니 SecurityConfig에 설정한것 처럼 모든 쿠키를 삭제하고 기본페이지로 돌아가는 것. = 로그아웃-->