<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/27
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>qna</title>
</head>
<script>
    let query = window.location.search;
    let param = new URLSearchParams(query);
    let msg = param.get('msg');
    if(msg!=null&&msg.length>0){
        alert(msg);
    }
</script>
<body>
    <ul id="list_wrap">
        <c:forEach items="${requestScope.pageResponse.getPageList()}" var="qna">
            <li>
                no : <c:out value="${qna.getQqno()}"/>
            </li>
            <li>
                <a href="<c:url value='/qnaDetail?qqno=${qna.getQqno()}&page=${requestScope.pageResponse.getPage()}'/>">
                    title : <c:out value="${qna.getTitle()}"/>
                </a>
            </li>
            <li>
                writer : <c:out value="${qna.getId()}"/>
            </li>
            <li>
                commentCnt : <c:out value="${qna.getCommentCnt()}"/>
            </li>
            <li>
                cnt : <c:out value="${qna.getCnt()}"/>
            </li>
            <li>
                <fmt:formatDate value="${qna.getRegDate()}" pattern="yyyy/MM/dd" var="regDate"/>
                ${regDate}
            </li>
            <li>
                <fmt:formatDate value="${qna.getUpdateDate()}" pattern="yyyy/MM/dd" var="updateDate"/>
                ${updateDate}
            </li>
        </c:forEach>
        <div class="nav">
            <ul>
                <c:if test="${requestScope.pageResponse.isShowPrev()}">
                    <li class="nav_prev">
                        <a href="<c:url value="/qnaList?page=${requestScope.pageResponse.page-1}&size=${requestScope.pageResponse.size}"/>">
                            [PREV]
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="${requestScope.pageResponse.start}" end="${requestScope.pageResponse.end}" var="num">
                    <li>
                        <a href="<c:url value="/qnaList?page=${num}&size=${requestScope.pageResponse.size}"/>"
                           class="${num==requestScope.pageResponse.page?'sel':''}">${num} </a>
                    </li>
                </c:forEach>
                <c:if test="${requestScope.pageResponse.isShowNext()}">
                    <li class="nav_next">
                        <a href="<c:url value="/qnaList?page=${requestScope.pageResponse.page+1}&size=${requestScope.pageResponse.size}"/>">
                            [NEXT]
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </ul>
</body>
</html>
