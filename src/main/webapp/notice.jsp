<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <jsp:include page="common/commonFiles.jsp" flush="true"/>
    <script src="javascript/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="css/sub.css">
</head>

<body>
    <jsp:include page="common/header.jsp" flush="true"/>
    <main id="notice">
        <!--    서브메뉴 타이틀 영역    -->
        <div class="sub_tit_line">
            <ul>
                <li class="sub_tit_home"><a href="/project">H</a></li>
                <li><a href="/project/notice">공지 목록</a></li>
                <li><a href="#">고객센터</a></li>
            </ul>
        </div>
        <h2 class="sub_h2_tit">고객센터</h2>
        <!--        게시판 시작   -->
        <div class="container_wrap">
            <!--     공지 카테고리 영역       -->
            <div class="container_inner">
                <ul class="notice_category">
                    <li class="on"><a href="${pageContext.request.contextPath}/notice.jsp">공지사항</a></li>
                    <li><a href="${pageContext.request.contextPath}/qnaList">1:1 상담</a></li>
                </ul>
            </div>
             <!--     게시판 검색 영역       -->
            <div id="search_wrap">
                검색 영역
            </div>
             <!--     게시판 목록 영역       -->
            <div id="board_wrap">
                <table>
                    <tr>
                        <td class="bo_num">1</td>
                        <td class="bo_tit"><a href="#">서울 대표식당 100곳 뽑았다…'테이스트오브서울' 공개</a></td>
                        <td class="bo_date">2022-10-12</td>
                    </tr>
                    <tr>
                        <td class="bo_num">2</td>
                        <td class="bo_tit"><a href="#">서울 대표식당 100곳 뽑았다…'테이스트오브서울' 공개</a></td>
                        <td class="bo_date">2022-10-12</td>
                    </tr>
                </table>

            </div>
        </div>
    </main>
</body>
</html>