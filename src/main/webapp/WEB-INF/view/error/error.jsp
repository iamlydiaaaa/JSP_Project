<%@ page import="java.util.Date" %><%--
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
<!DOCTYPE html>

<head>
    <title>Title</title>
    <script src="<c:url value="/resources/javascript/jquery-3.6.0.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
   <style>
        .error_wrap{text-align: center; 
            padding: 100px 0; margin: 0 auto; max-width: 1200px;
        }
        .error_wrap h1{
            font-size: 4em;
            margin-bottom: 50px;
            color: #793dea;
        }
        
        .error_wrap h2{font-size: 1.7em; margin-bottom: 50px;}
        .error_wrap strong{margin-bottom: 50px; display: block; color: #999;}
        .error_wrap>p{background-color: #ece8ef; padding: 20px 0; margin-bottom: 5px;}
        .error_wrap a{display: block;  font-weight: bold; cursor: pointer; color: #793dea;}
        .error_wrap a:hover{text-decoration: underline;}
        #error_txt{display: none; margin-top: 30px; color: #999;}
    </style>
    <script>
        //에러 내용 표시
        function showTxt(){
            document.getElementById("error_txt").style.display="block";
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/header.jsp" flush="true"/>
<!--<script src="/resources/javascript/header_sub.js"></script>-->
<main>
    <div class="sub_tit_line">
        <ul>
            <li class="sub_tit_home"><a href="/project">H</a></li>
            <li><a href="#">ERROR</a></li>
        </ul>
    </div>
    <div class="error_wrap">
       <div>
          <h1>400 ERROR</h1>
           <h2> 페이지를 찾을 수 없습니다.</h2>
           <strong>페이지가 존재하지 않거나, 사용할 수 없는 페이지입니다. <br> 주소가 올바른지 다시 한 번 확인해 주세요.</strong>
       </div>
       <p>
           <a onclick="showTxt()">에러 오류 확인:</a>
           <span id="error_txt">에러 내용 삽입</span>
       </p>
       
       
    </div>

</main>
</body>
</html>

