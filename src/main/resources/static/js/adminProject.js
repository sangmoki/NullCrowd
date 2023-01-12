// $(function (){
//     $(".approveBtn").onclick(approvalDecision(1));
//
//     $(".deniedBtn").onclick(approvalDecision(2));
//
//
// })




function approvalDecision(no){

    let projectNo = $(this).parent().children[0].html();
    const decision = no;

    $.ajax({
        type: 'post',
        url: '/admin/confirmProject',
        data: {"projectNo" : projectNo,
                "decision" : decision
                },
        success : function(data){

            window.alert("업데이트에 성공했습니다.")
        },
        error : window.alert("업데이트 실패했습니다.")
    });
}