<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 조</title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<style type="text/css">
table {
 border:2px solid #000;
 border-collapse:collapse;
}
td {
 border:1px solid #000;
 height:20px;
}
<c:set var="memberId" value="${sessionScope.MEMBER.memberId }"/>
<c:set var="userId" value="${article.userId }"/>
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
	<c:choose>
	<c:when test="${memberId ne userId}">
	<a>다른 유저의 게시물은 수정/삭제할 수 없습니다</a>
	</c:when>
	<c:otherwise>
		<a href="./app/updateForm?articleId=${article.articleId}">[글 수정]</a>
		<a href="./app/deletearticle?articleId=${article.articleId}">[글 삭제]</a>
	</c:otherwise>
	</c:choose>
	</p>
</body>
</html>