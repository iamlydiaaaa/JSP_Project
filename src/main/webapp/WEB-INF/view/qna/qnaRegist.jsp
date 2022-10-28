<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/27
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <script>
        window.onpageshow = function(event) {
            //back 이벤트 일 경우
            if (event.persisted) {
                location.reload(true);
            }
        }
    </script>
</head>
<body>
<form action="<c:url value="/qnaRegist"/>" method="post">
    <!--제목-->
    <input type="text" name="title" id="title" placeholder="제목">

    <!--내용-->
    content :
    <textarea name="content" id="content" cols="30" rows="10" placeholder="내용"></textarea>

    <!--작성자-->
    <input type="text" name="id" id="writer" value="${sessionScope.get("user")}" readonly>

    <input type="hidden" name="page" value="${page}">
    <button type="submit">전송</button>
</form>
<a href="<c:url value="/qnaList?page="/>${page}">목록</a>
</body>
</html>
