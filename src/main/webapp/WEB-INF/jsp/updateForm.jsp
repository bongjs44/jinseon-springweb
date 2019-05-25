<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글수정</title>
</head>
<body>
	<c:set var="memberId" value="${sessionScope.MEMBER.memberId }"/>
	<c:set var="userId" value="${article.userId }"/>
	<c:choose>
	<c:when test="${memberId eq userId}">
	<form action="./app/update?articleId=${article.articleId}" method="post">
	<table border="1" style="width:600px">
            <caption>게시판</caption>
            <colgroup>
                <col width='15%' />
                <col width='*%' />
            </colgroup>
            <tbody>
                <tr>
                    <td>제목</td> 
                    <td><input type="text" name="title" size="70" maxlength="250" value="${article.title}"></td> 
                </tr>
                <tr>
                    <td>내용</td> 
                    <td><textarea name="content" rows="20" cols="60" required>${article.contentHtml}</textarea></td> 
                </tr>
            </tbody>
        </table>    
        <button type="submit">수정</button>
        </form>
        </c:when>
        <c:otherwise>
        <a>다른 유저의 게시물은 수정할 수 없습니다</a><br/>
        <a href="./app/articles">돌아가기</a>
        </c:otherwise>
        </c:choose>
</body>
</html>