<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>축제</title>
    <jsp:include page="common/commonFiles.jsp" flush="true"/>
    <link rel="stylesheet" href="css/main.css">
</head>
<script>
    //로그인유지체크 + 로그인성공시 msg=remember_login 이 전달됨
    let query = window.location.search;
    let param = new URLSearchParams(query);
    let msg = param.get('msg');
    //체크된 경우
    if(msg!=null&&msg.length>0){
        alert(msg);
        //logined_cookie를 생성
        setCookie("logined_cookie","${sessionScope.user}",7);
    }
    function setCookie(cookieName, value, exdays) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
        document.cookie = cookieName + "=" + cookieValue;
    }
</script>
<body>
    <jsp:include page="common/header.jsp" flush="true"/>
    <main>
        <div id="main">
            <section id="visual">
               <div class="main_bg">
                   <p class="bg00"></p>
                   <p class="bg01"></p>
                   <p class="bg02"></p>
                   <p class="bg03"></p>
               </div>
               <p class="main_txt">SEOUL FESTIVAL

                </p>
            </section>
        </div>
    </main>
</body>
</html>