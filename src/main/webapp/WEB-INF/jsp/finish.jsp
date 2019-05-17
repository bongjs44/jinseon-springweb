<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글 쓰기</title>
</head>
<body>
	<p>${param.title }</p>
	<p>${param.content }</p>
	<p>글이 성공적으로 등록되었습니다</p>
	<p>
		<a href="./app/articles">[돌아가기]</a>
	</p>
</body>
</html>