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
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
<%--                    ${fn:replace(fn:replace(requestScope.culture.getDtlcont(), "\\r\\n", " "), "\\t", " ")}--%>
                </div>
            </div>
        </section>
    </div>
<%--    <form action="<c:url value="/detail"/>" method="post">--%>
<%--        <input type='date' name='res_dt'/>    &lt;%&ndash;res_dt=2022-10-29&ndash;%&gt;--%>
<%--        <input type="hidden" name="cno" value="${requestScope.culture.getCno()}"/>--%>
<%--        <input type="hidden" name="id" value="${sessionScope.user}"/>--%>
<%--        <button type="submit" onclick="return confirm('예약 하시겠습니까?')">전송</button>--%>
<%--    </form>--%>


    <!--리뷰 작성-->
    <!--댓글작성/수정-->
    <div>
        리뷰 작성/수정 : <textarea cols="50" rows="10" name="content" id="content" placeholder="리뷰내용"></textarea>
        <label for="grade">평점 : </label><input type="number" name="grade" id="grade" placeholder="1-5">
        <button id="writeBtn" type="button">리뷰작성</button>
        <button id="modBtn" type="button" style="display: none;">리뷰수정 후 전송</button>
    </div>

    <!--리뷰 리스트-->
    <div id="reviewList" data-cno="${requestScope.culture.getCno()}" data-id="${sessionScope.user}">

    </div>
    <script>
        $(document).ready(function(){
            let data_cno = $("#reviewList").attr("data-cno");
            let data_id = $("#reviewList").attr("data-id");
            //u d
            // 시작하면서 해당 cno의 모든 리뷰 리스트를 가져옴
            getReviews(data_cno);

            //리뷰쓰기버튼 클릭이벤트
            $("#writeBtn").click(function(){
                let reviewVO = {id : data_id ,
                    cno : data_cno,
                    content : $("textarea[name=content]").val(),
                    grade : $("input[name=grade]").val()
                }
                writeReview(reviewVO);
            });

            //리뷰수정버튼 클릭이벤트
            $("#reviewList").on("click",".modBtn",function(){ //아래의 클래스 modBtn클릭
                //1. writeBtn숨기고 숨겨진 #modBtn 버튼 다시 보이게
                $("#writeBtn").css("display","none");
                $("#modBtn").css("display","block");
                //2. 수정에 필요한 content,grade
                let data_cno = $("#reviewList").attr("data-cno");
                let data_content = $(this).siblings(':eq(1)').text();
                let data_grade = $(this).parent().attr("data-grade");
                //3. 검증에 필요한 re_no
                let data_re_no = $(this).parent().attr("data-re_no");
                //4. 현재 리뷰 내용 textarea에 표시 + 평점도 표시
                $("textarea[name=content]").val(data_content);
                $("input[name=grade]").val(data_grade);

                $("#modBtn").click(function(){
                    let reviewVO = {
                        re_no : data_re_no,
                        cno : data_cno,
                        content : $("textarea[name=content]").val(),
                        grade : $("input[name=grade]").val()
                    }
                    updateReview(reviewVO);
                })
            })//리뷰수정버튼 클릭이벤트

            //리뷰삭제버튼 클릭이벤트
            $("#reviewList").on("click",".delBtn",function(){ //아래의 클래스 modBtn클릭
                //1. 삭제,검증에 필요한 re_no
                let re_no = $(this).parent().attr("data-re_no");
                //2. 목록 불러오기에 필요한 cno
                let cno = $("#reviewList").attr("data-cno");
                deleteReview(re_no,cno);
            })//리뷰삭제버튼 클릭이벤트

            $("#reviewList").on("click",".reviewPage",function(){
                //페이지를 cno와 같이 넘겨준다
                let page = $(this).text();
                getReviews2(data_cno,page);
            })//리뷰 페이징


        }); //document.ready

        //////////////////////////////////////////////

        let getReviews = function(cno) {
            $.ajax({
                url: '/project/review?cno='+cno,
                type: 'GET',
                headers: {"content-type":"application/json"},

                success : function(result){
                    //result = pageresponse
                    $("#reviewList").html(toHtml(result));
                },
                error: function() {
                    alert("error");
                }
            });//ajax
        }//getReviews

        let getReviews2 = function(cno,page) {
            $.ajax({
                url: '/project/review?cno='+cno+'&page='+page,
                type: 'GET',
                headers: {"content-type":"application/json"},

                success : function(result){
                    //result = pageresponse
                    $("#reviewList").html(toHtml(result));
                },
                error: function() {
                    alert("error");
                }
            });//ajax
        }//getReviews

        let writeReview = function(reviewVO) {
            $.ajax({
                url: '/project/review',
                type: 'POST',
                headers: {"content-type":"application/json"},
                data: JSON.stringify(reviewVO),

                success : function(result) {
                    alert("리뷰 등록 성공");
                    getReviews(reviewVO.cno);
                },
                error: function() {
                    alert("error");
                }
            })//ajax
        }//writeReivew

        let updateReview = function(reviewVO) {
            $.ajax({
                url: '/project/review/'+reviewVO.re_no,
                type: 'PUT',
                headers: {"content-type":"application/json"},
                data: JSON.stringify(reviewVO),

                success : function(result) {
                    alert("리뷰 수정 성공");
                    $("textarea[name=content]").val('');
                    $("input[name=grade]").val('');
                    $("#writeBtn").css("display","block");
                    $("#modBtn").css("display","none");
                    getReviews(reviewVO.cno);
                },
                error: function() {
                    alert("error");
                }
            })//ajax
        }//updateReview

        let deleteReview = function(re_no,cno) {
            $.ajax({
                url: '/project/review/'+re_no,
                type: 'DELETE',
                headers: {"content-type":"application/json"},

                success : function(result) {
                    alert("삭제 성공");
                    getReviews(cno);
                },
                error: function() {
                    alert("error");
                }
            })//ajax
        }//deleteReview

        //배열로 들어온 (js 객체를 html 문자로) 바꿔주는 함수
        let toHtml = function(pageResponse) {
            let reviews = pageResponse.pageList;
            let tmp = "<ul>";
            reviews.forEach(function(review) {
                tmp += '<li data-cno='+review.cno +' data-grade='+review.grade+' data-re_no='+review.re_no+'>'
                tmp += ' 아이디 : <span class="id">'+review.id + '</span>'
                tmp += ' 리뷰 : <span class="content">' + review.content + '</span>'
                tmp += ' 평점 : <span class="grade">' + review.grade + '</span>'
                tmp += ' 등록일 : '+review.regDate
                tmp += '<button class = "delBtn">삭제</button>'
                tmp += '<button class = "modBtn">수정</button>'
                tmp += '</li>'
            })//foreach
            tmp += '</ul>';
            //page nav
            if(pageResponse.showPrev){
                tmp += '[PREV]';
            }
            for(var i = pageResponse.start; i<=pageResponse.end ; i++){
                tmp += '<div class="reviewPage" style="display:inline-block; cursor: pointer;">'+i+'</div>';
            }
            if(pageResponse.showNext){
                tmp += '[NEXT]';
            }
            return tmp;
        }

        // if($("#comment").val().trim()==''){ // 입력이 없을때 검증용
        //   alert('댓글을 입력해주세요');
        //   $("#comment").focus();
        //   return;
        // }

    </script>
</main>
</body>
</html>
