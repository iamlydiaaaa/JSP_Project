<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>행사 상세</title>
    <link rel="stylesheet" href="css/reserveResult.css">
</head>

<body>
<jsp:include page="common/header.jsp" flush="true"/>
    <main id="reservation">
        <div class="sub_tit_line">
            <ul>
                <li class="sub_tit_home"><a href="#">H</a></li>
                <li><a href="#">행사예약</a></li>
                <li><a href="#"></a></li>
            </ul>
        </div>
        <!--     상세페이지 영역       -->
        <div class="container_wrap">
            <section class="reservation_nav">
                <p>
                    <span>1</span>
                    <strong>나의 예약 정보</strong>
                </p>
                <p>
                    <span>2</span>
                    <strong>예약 완료</strong>
                </p>
            </section>
            <section class="reserve_ok">
                <h1>예약이 완료되었습니다!</h1>
                <p>자세한 내용은 마이페이지에서 확인하실 수 있습니다.</p>
                <p class="btn_reservation"><input type="button" value="마이페이지"><input type="button" value="목록으로"></p>
            </section>

        </div>
    </main>

</body></html>