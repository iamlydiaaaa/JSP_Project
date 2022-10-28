<%--
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
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <script src="<c:url value="/resources/javascript/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
   <jsp:include page="common/header.jsp" flush="true" />
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
                <p><strong>문의 목록</strong></p>
            </div>

            <table class="board_write_table">
                <tr>
                    <td>번호</td>
                    <td align="left">
                        <c:out value="${qna.getQqno()}" />
                    </td>
                </tr>
                <tr>
                    <td>제목</td>
                    <td align="left">
                        <a href="<c:url value='/qnaDetail?qqno=${qna.getQqno()}'/>">
                            <c:out value="${qna.getTitle()}" /></a>
                    </td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td align="left">
                        <c:out value="${qna.getId()}" />
                    </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td align="left">
                        <textarea name="content" id="content" class="txt_area"><c:out value="${qna.getContent()}"/></textarea>
                    </td>
                </tr>
            </table>

            <div class="qna_btn_wrap">
                <a href="<c:url value=" /qnaModify?qqno="/>${qna.getQqno()}&page=${page}" class="btn_modify btn_active">수정</a>
                <a href="<c:url value=" /qnaDelete?qqno="/>${qna.getQqno()}&page=${page}" onclick="return confirm('정말 삭제 하시겠습니까?')" class="btn_modify btn_delete">삭제</a>
                <a href="<c:url value=" /qnaList?page="/>${page}" class="btn_modify btn_default">목록</a>
            </div>

            <!--리뷰-->
            <div class="detail_review">
               <div class="review_write">
                <p class="review_txt">
                    <textarea cols="50" rows="5" name="reviewContent" id="reviewContent" placeholder="답글내용"></textarea>
                    <button id="writeBtn" type="button">답글 등록</button>
                    <button id="modBtn" type="button" style="display: none;">답글 수정</button>
                </p>
                <div id="reviewList" data-qqno="${requestScope.qna.getQqno()}" data-id="${sessionScope.user}">
                    <p class="btn_wrap">
                        <button class="modBtn">수정</button>
                        <button class="delBtn">삭제</button>
                    </p>
                </div>
                </div>
            </div>

        </div>

    </main>
    
</body>

<script>
        $(document).ready(function(){
            let data_qqno = $("#reviewList").attr("data-qqno");
            let data_id = $("#reviewList").attr("data-id");
            // 시작하면서 해당 qqno의 모든 리뷰 리스트를 가져옴
            getReviews(data_qqno);

            //리뷰쓰기버튼 클릭이벤트
            $("#writeBtn").click(function(){
                let reviewVO = {
                    qqno : data_qqno,
                    content : $("textarea[name=reviewContent]").val(),
                }
                writeReview(reviewVO);
            });

            //리뷰수정버튼 클릭이벤트
            $("#reviewList").on("click",".modBtn",function(){ //아래의 클래스 modBtn클릭
                //1. writeBtn숨기고 숨겨진 #modBtn 버튼 다시 보이게
                $("#writeBtn").css("display","none");
                $("#modBtn").css("display","block");
                //2. 수정에 필요한 content,grade
                let data_qqno = $("#reviewList").attr("data-qqno");
                let data_content = $(this).parent().prev().prev().prev().text();
                //3. 검증에 필요한 qano
                let data_qano = $(this).parent().parent().attr("data-qano");
                //4. 현재 리뷰 내용 textarea에 표시 + 평점도 표시
                $("textarea[name=reviewContent]").val(data_content);

                $("#modBtn").click(function(){
                    let reviewVO = {
                        qano : data_qano,
                        qqno : data_qqno,
                        content : $("textarea[name=reviewContent]").val(),
                    }
                    updateReview(reviewVO);
                })
            })//리뷰수정버튼 클릭이벤트

            //리뷰삭제버튼 클릭이벤트
            $("#reviewList").on("click",".delBtn",function(){ //아래의 클래스 modBtn클릭
                //1. 삭제,검증에 필요한 qano
                let qano = $(this).parent().parent().attr("data-qano");
                //2. 목록 불러오기에 필요한 qqno
                let qqno = $("#reviewList").attr("data-qqno");
                deleteReview(qano,qqno);
            })//리뷰삭제버튼 클릭이벤트

            $("#reviewList").on("click",".reviewPage",function(){
                //페이지를 qqno와 같이 넘겨준다
                let page = $(this).text();
                let qqno = $("#reviewList").attr("data-qqno");
                getReviews2(qqno,page);
            })//리뷰 페이징

            //prev
            $("#reviewList").on("click",".prev",function(){
                //getreviews2 이용
            })
            //next
            $("#reviewList").on("click",".next",function(){

            })


        }); //document.ready

        //////////////////////////////////////////////

        let getReviews = function(data_qqno) {
            $.ajax({
                url: '/project/qnaReview?qqno='+data_qqno,
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
                url: '/project/qnaReview?qqno='+qqno+'&page='+page,
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
                url: '/project/qnaReview',
                type: 'POST',
                headers: {"content-type":"application/json"},
                data: JSON.stringify(reviewVO),

                success : function(result) {
                    alert("리뷰 등록 성공");
                    getReviews(reviewVO.qqno);
                },
                error: function() {
                    alert("error");
                }
            })//ajax
        }//writeReivew

        let updateReview = function(reviewVO) {
            $.ajax({
                url: '/project/qnaReview/'+reviewVO.qano,
                type: 'PUT',
                headers: {"content-type":"application/json"},
                data: JSON.stringify(reviewVO),

                success : function(result) {
                    alert("리뷰 수정 성공");
                    $("textarea[name=reviewContent]").val('');
                    $("#writeBtn").css("display","block");
                    $("#modBtn").css("display","none");
                    getReviews(reviewVO.qqno);
                },
                error: function() {
                    alert("error");
                }
            })//ajax
        }//updateReview

        let deleteReview = function(qano,qqno) {
            $.ajax({
                url: '/project/qnaReview/'+qano,
                type: 'DELETE',
                headers: {"content-type":"application/json"},

                success : function(result) {
                    alert("삭제 성공");
                    getReviews(qqno);
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
                tmp += '<li data-qqno='+review.qqno +' data-qano='+review.qano+'>'
                tmp += '<span class="title">'+review.title+'</span>'
                tmp += '<span class="content">'+review.content+'</span>'
                tmp += '<span class="regDate">'+review.regDate+'</span>'
                tmp += '<span class="updateDate">'+review.updateDate+'</span>'
                tmp += '<p class="btn_wrap"><button class = "delBtn">삭제</button>'
                tmp += '<button class = "modBtn">수정</button>'
                tmp += '</p></li>'
            })//foreach
            tmp += '</ul>';
            //page nav
            if(pageResponse.showPrev){
                tmp += '<span class="prev" data-page='+pageResponse.page+' >'+[PREV]+'</span>';
            }
            for(var i = pageResponse.start; i<=pageResponse.end ; i++){
                tmp += '<div class="reviewPage" style="display:inline-block; cursor: pointer;">'+i+'</div>';
            }
            if(pageResponse.showNext){
                tmp += '<span class="next" data-page='+pageResponse.page+' >'+[NEXT]+'</span>';
            }
            return tmp;
        }
    </script>
</html>
