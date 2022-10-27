<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/27
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <ul>
        <li>
            no : <c:out value="${qna.getQqno()}"/>
        </li>
        <li>
            <a href="<c:url value='/qnaDetail?qqno=${qna.getQqno()}'/>">
                title : <c:out value="${qna.getTitle()}"/>
            </a>
        </li>
        <li>
            writer : <c:out value="${qna.getId()}"/>
        </li>
        <li>
            content :
            <textarea name="content" id="content" cols="30" rows="10"><c:out value="${qna.getContent()}"/></textarea>
        </li>
    </ul>
    <div>
        <a href="<c:url value="/qnaModify?qqno="/>${qna.getQqno()}&page=${page}">수정</a>
        <a href="<c:url value="/qnaDelete?qqno="/>${qna.getQqno()}&page=${page}" onclick="return confirm('정말 삭제 하시겠습니까?')">삭제</a>
        <a href="<c:url value="/qnaList?page="/>${page}">목록</a>
    </div>
    <div>댓글..</div>
</body>
</html>
