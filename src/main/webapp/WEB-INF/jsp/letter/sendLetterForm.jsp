<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./sendLetter" method="post">
	<table border="1" style="width:600px">
            <caption>편지 보내기</caption>
            <colgroup>
                <col width='15%' />
                <col width='*%' />
            </colgroup>
            <tbody>
                <tr>
                    <td>제목</td> 
                    <td><input type="text" name="title" size="70" maxlength="250" value="${param.title}"></td> 
                </tr>
                <tr>
                    <td>내용</td> 
                    <td><textarea name="content" rows="20" cols="60"></textarea></td> 
                </tr>
            </tbody>
        </table>    
        <button type="submit">전송</button>
        <input type="hidden" name="receiverId" value="${param.receiverId }" />
        <input type="hidden" name="receiverName" value="${param.receiverName }" />
        </form>
</body>
</html>