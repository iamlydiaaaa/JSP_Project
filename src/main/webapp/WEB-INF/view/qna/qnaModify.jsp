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
</head>
<body>
<form action="<c:url value="/qnaModify"/>" method="post">
    <!--제목-->
    <input type="text" name="title" id="title" value="${qna.getTitle()}">

    <!--내용-->
    content :
    <textarea name="content" id="content" cols="30" rows="10"><c:out value="${qna.getContent()}"/></textarea>

    <!--작성자-->
    <input type="text" name="id" id="writer" value="${qna.getId()}" readonly>

    <input type="hidden" name="qqno" value="${qna.getQqno()}">
    <input type="hidden" name="page" value="${page}">
    <button type="submit">수정</button>
    <button id="delBtn">삭제</button>
    <button id="listBtn">목록</button>
</form>
<script>
    $(document).ready(function(){
       $("#delBtn").click(function(e){
           e.stopPropagation();
           self.location="/project/qnaDelete?qqno="${qna.getQqno()}
       })
        $("#listBtn").click(function(e){
            e.stopPropagation();
            self.location="/project/qnaList"
        })
    });
</script>
<a href="<c:url value="/qnaModify?qqno="/>${qna.getQqno()}">수정</a>
<a href="">삭제</a>
<a href="">목록</a>
</body>
</html>
