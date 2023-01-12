// 메인 프로젝트 더보기 기능

// 회원정보보기
$(function(){
    $('#supporter_mode').click(function(){
        $('#personal_makerMode').hide();
        $('#personal_supporterMode').show();
        $(this).parent().removeClass('NullUserTypeSwitch_isMaker');
    });
});
$(function(){
    $('#maker_mode').click(function(){
        $('#personal_supporterMode').hide();
        $('#personal_makerMode').show();
        $(this).parent().addClass('NullUserTypeSwitch_isMaker');
    });
});
$(function() {
    $('.goPJ1').click(function () {
        $("#PJform").attr("action", "/project/makeProject?page=1").submit();
    })
})
$(function() {
    $('.goPJ2').click(function(){
        $("#PJform").attr("action", "/project/makeProject?page=2")
        $("#PJform").submit();
    });
})
$(function(){$('.goPJ3').click(function() {
    $("#PJform").attr("action", "/project/makeProject?page=3").submit();
    })
});