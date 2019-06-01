<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 조</title>
<script type="text/javascript">
	function confirmDelete() {
		if (confirm("삭제하시겠습니까?"))
			return true;
		else
			return false;
	}
</script>
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
</style>
<body>
	<c:set var="mode" value="${param.mode }"/>
	<c:set var="SENT" value="SENT"/>
	<table>
	<tbody>
	<tr>
		<td>번호</td>
		<td>${letter.letterId }</td>
		<td>글 쓴 시간</td>
		<td>${letter.cdate }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3">${letter.title }</td>
	</tr>
	<tr>
		<c:choose>
		<c:when test="${mode eq SENT}">
		<td>수신자</td>
		<td colspan=3>${letter.receiverName }</td>
		</c:when>
		<c:otherwise>
		<td>작성자</td>
		<td colspan=3>${letter.senderName }</td>
		</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${letter.contentHtml }</td>
	</tr>
	</tbody>
	</table>
	<a href="./app/letter/deleteLetter?letterId=${letter.letterId}&mode=${param.mode}"onclick="return confirmDelete();">편지 삭제</a>
</body>
</html>