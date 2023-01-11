var nickCheck = false;
var idCheck = false;
var code;
var isCertification;
var pwCheck;
var phoneNumber = /^[0-9]{10,11}$/


// 아이디 중복 체크
$(function(){
    $('#certifyEmail').on('click', function () {


        let memEmail = $('#memEmail').val();

        $.ajax({
            url: "/member/idDupCheck",
            type: 'post',
            data: {memEmail: memEmail},
            success: function (resultMap) {
                if (resultMap.result == 0) {
                    idCheck = true;
                    $('#emailHelp').hide();
                    $('#certifyEmail').attr('disabled', true);
                    $('#emailId').attr('disabled', true);
                    $(this).html('발송되었습니다');
                    $('#checkEmail').show();
                    code = resultMap.randomCode;
                } else {
                    alert('사용중인 아이디입니다')
                    idCheck = false;
                }
            }
        })

    })
})

//이메일 인증번호 대조
$(function(){
    $("#certifyEmail2").on('click', function(){

        if($("#confirmCode").val() == code){
            // $(".successEmailChk").text("인증번호가 일치합니다.");
            // $(".successEmailChk").css("color","green");
            alert('인증번호가 일치합니다.')
            $("#emailDoubleChk").val("true");
            // $("#confirmCode").attr("disabled",true);
            isCertification = true;
        }else{
            // $(".successEmailChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
            // $(".successEmailChk").css("color","red");
            alert('인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.')
            $("#emailDoubleChk").val("false");
            // $("#confirmCode").attr("autofocus",true);
            isCertification = false;
        }

    });
})

// 닉네임 중복 체크
$(function () {
    $('#nickCheck').on('click', function () {

        let nickName = $('#nickName').val();


        $.ajax({
            url: '/member/nickDupCheck',
            type: 'post',
            data: {nickName: nickName},
            success: function (nickResult) {
                if (nickResult == 0) {
                    alert('사용 가능합니다!')
                    nickCheck = true;
                } else {
                    alert('사용중인 닉네임입니다')
                    nickCheck = false;
                }
            }
        });
    })
})

// 비밀번호 확인
$(function (){
    $("#inputPassword6").blur(function(){
        if($("#inputPassword6").val() == $("#inputPassword4").val()){
            $(".successPwChk").text("비밀번호가 일치합니다.");
            $(".successPwChk").css("color", "green");
            pwCheck = true;
        }else{
            $(".successPwChk").text("비밀번호가 일치하지 않습니다.");
            $(".successPwChk").css("color", "red");
            pwCheck = false;
        }
    });
})

$(function (){

})

// 회원가입 유효성 검사
$(function (){
    $("#joinBtn").click(function (){
        if (
            idCheck == true && isCertification == true
            && $("#inputPassword4").val() != ''
            && pwCheck == true
            && $("#memName").val() != ''
            && nickCheck == true
            && $("#bYear").val() != ''
            && $("#gender").val() != ''
            && $("#phoneNumber").val() != ''){
            $("#signup").attr("action", "/member/signup");
            $("#signup").submit();
            alert("환영합니다");
        }else{
            alert("회원가입에 실패하였습니다 다시한번 확인해 주시기 바랍니다.")
        }
        return false
    })
})