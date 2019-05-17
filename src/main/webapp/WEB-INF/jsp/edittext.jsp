<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 입력</title>
</head>
<body>
	<h2>게시글 입력</h2>
	<form action="./app/finish" method="post">
		<p>
			제목:<br> <input type="text" name="title" value="${param.title }">
		</p>
		<p>
			내용:<br><textarea name="content"></textarea>
		</p>
		<button type="submit">저장</button>
		<p>
		<a href="./app/articles">[취소]</a>
	</p>
	</form>
</body>
</html>