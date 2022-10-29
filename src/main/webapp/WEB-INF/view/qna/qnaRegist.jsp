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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/detail.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
</head>

<body>
<main id="board_register">

    <div class="sub_tit_line">
        <ul>
            <li class="sub_tit_home"><a href="#">H</a></li>
            <li><a href="#">고객센터</a></li>
            <li><a href="#">QnA 작성</a></li>
        </ul>
    </div>

    <div class="board_wrap">
        <div id="sub_banner">
            <p><strong>문의하기</strong>QnA센터 답변 가능 시간: 월~금 9:00 - 18:00 </p>
        </div>
        <form action="/project/qnaRegist" method="post">
            <table class="board_write_table">
                <tr>
                    <td>제목</td>
                    <td align="left">
                        <input type="text" name="title" id="title" placeholder="제목">
                    </td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td align="left">
                        <input type="text" name="id" id="writer" value="<c:out value="${sessionScope.get('user')}"/>" readonly>
                    </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td align="left">
                        <textarea name="content" id="content" class="txt_area" placeholder="내용을 입력하세요."></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="hidden" name="page" value="${page}">
                        <button type="submit" class="board_edit">문의 등록하기</button>
                    </td>
                </tr>
            </table>
        </form>
        <p class="board_btns">
            <a href="<c:url value="/qnaList?page="/>${page}">목록으로 돌아가기</a>
        </p>
    </div>
</main>
</body>
</html>