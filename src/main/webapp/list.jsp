<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>행사 목록</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/sub.css">
</head>

<body>
    <script src="javascript/header_sub.js"></script>
    <main id="festival_list">
        <div class="sub_tit_wrap">
            <div class="sub_tit_inner">
                <h2>2022 행사 예약</h2>
            </div>
        </div>
        <!--     게시판 검색 영역       -->
        <div class="container_wrap">
            <div id="search_wrap">
                <input type="text" name="list_search" value="검색어 입력" class="list_search">
                <button type="submit" id="list_search_btn">검색</button>
            </div>
            <ul id="list_wrap">
<%--                <c:out value="${requestScope.pageResponse}"></c:out>--%>
                <c:forEach items="${requestScope.pageResponse.getPageList()}" var="list">
                    <li>
                            ${fn:replace(fn:replace(list, "\\r\\n", " "), "\\t", " ")}
<%--                                <c:out value="${list.getImg_url()}"></c:out>--%>
<%--                            <c:out value="${list.getSvc_nm()}"></c:out>--%>
<%--                            ${fn:replace(fn:replace(list.getDtlcont(), "\\r\\n", " "), "\\t", " ")}--%>
                    </li>
                </c:forEach>
<%--
<%--                <li>--%>
<%--                    <a href="festivalDetail.html">--%>
<%--                        <img id="list_img" src="images/bg04.jpg" alt="">--%>
<%--                        <div class="list_box">--%>
<%--                            <p id="list_pro_name">서울물재생체험관 단체관람 예약</p>--%>
<%--                            <ul id="list_pro_info">--%>
<%--                                <li><strong>장소명</strong><span>서울물재생체험관</span></li>--%>
<%--                                <li><strong>이용대상</strong><span>성인,어린이</span></li>--%>
<%--                                <li><strong>접수기간</strong><span>2022-07-01~2022.12.25</span></li>--%>
<%--                                <li><strong>이용기간</strong><span>2022.08.02-2022.12.31</span></li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>
<%--                    </a>--%>
<%--                </li>--%>
            </ul>
        </div>

    </main>
</body></html>