// 메인 프로젝트 더보기 기능
$(function () {
    $('#moreproject').click(function () {
        $('#parentCard .col:hidden').slice(0, 3).show();
        if ($('#datalist li').length == $('#datalist li:visible').length) {
            $('#moreproject').hide();
        }
    });
});

$(function () {
    $('#morepre').click(function () {
        $('#parentCard2 .col:hidden').slice(0, 3).show();
        if ($('#datalist li').length == $('#datalist li:visible').length) {
            $('#morepre').hide();
        }
    });
});

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
$(function(){
    $('#certifyEmail').click(function(){
        $('#emailHelp').hide();
        $(this).attr('disabled', true);
    })
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