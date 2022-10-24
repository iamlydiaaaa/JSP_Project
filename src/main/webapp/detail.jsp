<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/10/19
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>${culture.getSvc_nm()}</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/sub.css">
    <link rel="stylesheet" href="css/detail.css">
    <link rel="icon" href="images/favicon.ico">
    <script src="javascript/calendar.js"></script>
</head>
<body>
<jsp:include page="header_sub.jsp" flush="true"/>
<main id="festival_detail">
    <div class="sub_tit_line">
        <ul>
            <li class="sub_tit_home"><a href="/project">H</a></li>
            <li><a href="/project/list">행사 예약</a></li>
            <li><a href="#">행사 상세</a></li>
        </ul>
    </div>
    <!--     상세페이지 영역       -->
    <div class="container_wrap">
        <section class="detail_overview">
            <div>
                <h2>${culture.getSvc_nm()} <br>
                    <span class="detail_date">행사 기간 : ${culture.getSvc_opn_bgn_dt()} ~ ${culture.getSvc_opn_end_dt()}</span>
                </h2>
                <table id="detail_table">
                    <tr>
                        <th>대상</th>
                        <td>${culture.getUse_tgt_info()}</td>
                    </tr>
                    <tr>
                        <th>장소</th>
                        <td>${culture.getArea_nm()} ${culture.getPlace_nm()}</td>
                    </tr>
                    <tr>
                        <th>모집정원</th>
                        <td>${culture.getCapacity()}명</td>
                    </tr>
                    <tr>
                        <th>이용기간</th>
                        <td>${culture.getSvc_opn_bgn_dt()} ~ ${culture.getSvc_opn_end_dt()}</td>
                    </tr>
                    <tr>
                        <th>취소기간</th>
                        <td>${culture.getRevstd_day_nm()} ~ ${culture.getRevstd_day()}</td>
                    </tr>
                    <tr>
                        <th>이용요금 유무</th>
                        <td>${culture.getPay_ay_nm()}</td>
                    </tr>
                    <tr>
                        <th>문의전화</th>
                        <td>${culture.getTel_no()}</td>
                    </tr>
                    <tr>
                        <th>기타</th>
                        <td> </td>
                    </tr>
                </table>
            </div>
            <p class="bg"></p>
        </section>
        <section class="detail_select_date">
            <div>
                <h2>날짜 확인</h2>
                <table class="calendar">
                    <thead>
                    <tr class="calendar_tit">
                        <td onClick="prevCalendar();" style="cursor:pointer;">&#60;&#60;</td>
                        <td colspan="5">
                            <span id="calYear">YYYY</span>년
                            <span id="calMonth">MM</span>월
                        </td>
                        <td onClick="nextCalendar();" style="cursor:pointer;">&#62;&#62;</td>
                    </tr>
                    <tr class="calendar_days">
                        <td>일</td>
                        <td>월</td>
                        <td>화</td>
                        <td>수</td>
                        <td>목</td>
                        <td>금</td>
                        <td>토</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <div class="calendar_desc">
                    <p>선택한 날짜: <span id="cal_getDate"></span></p>
                </div>
            </div>
        </section>
        <section class="detail_desc">
            <div>
                <h2>이용 안내</h2>
                <div id="description">
                    ${fn:replace(fn:replace(requestScope.culture.getDtlcont(), "\\r\\n", " "), "\\t", " ")}
                </div>
            </div>
        </section>
    </div>

</main>
</body>
</html>
