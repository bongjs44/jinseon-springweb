<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 조</title>
</head>
<style type="text/css">
table {
 border:2px solid #000;
 border-collapse:collapse;
}
td {
 border:1px solid #000;
 height:20px;
}
</style>
<body>
	<table>
	<tr>
		<td>글 번호</td>
		<td>${article.articleId }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${article.name }</td>
	</tr>
	<tr>
		<td>글 쓴 시간</td>
		<td>${article.cdate }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${article.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${article.contentHtml }</td>
	</tr>
	</table>
	<p>
		<a href="./app/edittext">[글 쓰기]</a>
	</p>
	<p>
		<a href="./app/articles">[돌아가기]</a>
	</p>
	<p>
		<a href="./app/updatearticle">[글 수정]</a>
		<a href="./app/deletearticle">[글 삭제]</a>
	</p>
</body>
</html>