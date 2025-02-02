<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/join.css">
    <script src="javascript/agreetxt.js"></script>
    <script>
        $(document).ready(function() {
            //아이디/비밀번호에 포커스하면 텍스트 효과
            $(".u_txt input").focus(function() {
                $(this).siblings("label").addClass("focus");
            }).blur(function() {
                if ($(this).val() == "") {
                    $(this).siblings("label").removeClass("focus");
                } else {
                    $(this).siblings("label").addClass("focus");
                }
            })

            //이용약관,개인정보 취급방침 체크버튼
            $("#chk1").change(function() {
                var chk1 = $("#chk1").prop("checked");
                if (chk1 == true) {
                    $("#chk2, #chk3").prop("checked", true);
                    $(".type01").addClass("checked");
                } else {
                    $("#chk2, #chk3").prop("checked", false);
                    $(".type01").removeClass("checked");
                }
            })
            $("#chk2, #chk3").change(function() {
                var chk2 = $("#chk2").prop("checked");
                var chk3 = $("#chk3").prop("checked");
                if (chk2 == true) {
                    $(".type03").addClass("checked");
                } else {
                    $(".type03").removeClass("checked");
                }

                if (chk3 == true) {
                    $(".type04").addClass("checked");
                } else {
                    $(".type04").removeClass("checked");
                }
                if (chk2 == true && chk3 == true) {
                    $("#chk1").prop("checked", true);
                    $(".type02").addClass("checked");
                } else {
                    $("#chk1").prop("checked", false);
                    $(".type02").removeClass("checked");
                }
            })
        })
    </script>
</head>
<body>
    <jsp:include page="common/header.jsp" flush="true"/>
   <script>
       let query = window.location.search;
       let param = new URLSearchParams(query);
       let msg = param.get('msg');
       if(msg!=null&&msg.length>0){
           alert(msg)
       }
   </script>
    <div id="wrap">
        <script src="common/header_sub.js"></script>

        <main id="main">
            <div id="join_wrap">
                <div id="sub_banner">
                    <p><strong>회원 가입</strong></p>
                </div>
                <form action="<c:url value="/join"/>" method="post" id="join_form">
                    <div class="personal_info">
                        <strong class="join_tit">1) <br>개인정보 <br>입력</strong>
                        <p>
                            <label for="u_id">아이디<span> (영문소문자/숫자, 4~16자)</span></label>
                            <input type="text" id="u_id" name="id">
                        </p>
                        <p>
                            <label for="u_pw1">비밀번호<span>(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10~16자)</span></label>

                            <input type="password" id="u_pw1" name="pwd">
                        </p>
                        <p>
                            <label for="u_pw2">비밀번호 확인</label>
                            <input type="password" id="u_pw2">
                        </p>
                        <p>
                            <label for="u_name">이름</label>
                            <input type="text" id="u_name" name="name">
                        </p>
                        <p class="tel_wrap">
                            <label for="u_tel1">전화번호</label>
                            <input type="tel" id="u_tel1" name="phone1"> -
                            <input type="tel" id="u_tel2" name="phone2"> -
                            <input type="tel" id="u_tel3" name="phone3">
                        </p>
                        <p>
                            <label for="email_l">이메일</label>
                            <input type="email" id="email_l" name="email">
                        </p>
                        <p>
                            <label for="u_gender">성별</label>
                            <input type="checkbox" id="u_gender1" name="gender" value="1">남
                            <input type="checkbox" id="u_gender2" name="gender" value="2">여
                        </p>
                        <p>
                            <label for="u_birth">나이</label>
                            <input type="number" id="u_birth" name="age">
                        </p>

                    </div>
                    <div id="agree">
                        <strong class="join_tit">2) <br>이용약관 <br>동의</strong>
                        <div class="agreebox1">
                            <p class="agreebox_tit">
                                <input type="checkbox" name="chk" id="chk2">
                                <label for="chk2" class="type01 type03">[필수] 이용약관 동의</label>
                            </p>
                            <p class="agreebox_txt">
                                <textarea class="agree_txt1" cols="100" rows="10" readonly></textarea>
                            </p>
                        </div>
                        <div class="agreebox2">
                            <p class="agreebox_tit">
                                <input type="checkbox" name="chk" id="chk3">
                                <label for="chk3" class="type01 type04">[필수] 개인정보 수집 및 이용 동의</label>
                            </p>
                            <p class="agreebox_txt">
                                <textarea class="agree_txt2" cols="100" rows="10" readonly></textarea>
                            </p>
                        </div>
                        <p class="chk_all">
                            <input type="checkbox" id="chk1">
                            <label for="chk1" class="type01 type02">이용약관 및 개인정보수집 및 이용에 모두 동의합니다.</label>
                        </p>
                    </div>
                    <div id="login_btn">
                        <button type="submit" class="login_submit">회원가입</button>
                        <button type="reset" class="cancel">취소</button>
                    </div>
                </form>
            </div>
        </main>
        <!--        FOOTER 불러오기 -->
        <script src="common/footer.js"></script>
        <!--        FOOTER 불러오기 끝-->
    </div>

</body>

</html>
