<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../common/commonFiles.jsp" flush="true"/>
<header>
    <div id="header">
        <h1 id="logo"><a href="/project">SEOUL<br>FESTIVAL</a></h1>
        <nav id="gnb">
            <ul>
                <li><a href="#">축제안내</a></li>
                <li><a href="/project/list">예약하기</a></li>
                <li><a href="/project/notice">공지사항</a></li>
                <li><a href="../myPage.html">마이페이지</a></li>
            </ul>
        </nav>
        <div id="header_login">
            <script>
                if(getCookie("logined_cookie")!=null){
                    $(".logined").css("display","block");
                    $(".logouted").css("display","none");
                } else{
                    $(".logined").css("display","none");
                    $(".logouted").css("display","block");
                }
                function getCookie(cookieName) {
                    cookieName = cookieName + '=';
                    var cookieData = document.cookie;
                    var start = cookieData.indexOf(cookieName);
                    var cookieValue = '';
                    if (start != -1) {
                        start += cookieName.length;
                        var end = cookieData.indexOf(';', start);
                        if (end == -1) end = cookieData.length;
                        cookieValue = cookieData.substring(start, end);
                    }
                    return unescape(cookieValue);
                }
            </script>

            <a href="/project/logout" class="logined">로그아웃</a>
            <a href="/project/logout" class="logined">마이페이지</a>
            <a href="/project/login" class="logouted">로그인</a>
            <a href="/project/join" class="logouted">회원가입</a>
        </div>
    </div>
</header>

