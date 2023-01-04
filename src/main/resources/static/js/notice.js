function getSearchList(){
        $.ajax({
                type: 'GET',
                url: "/getSearchList",
                data : $("form[name=search-form").serialize(),
                success : function (result){
                        // 테이블 초기화
                        $('#boardtable > tbody').empty();
                        if(result.length>=1){
                                result.forEach(function (item){
                                    str='<tr>'
                                    str+="<td>"+item.idx+"</td>";
                                    str+="<td>"
                                })
                        }
                }
        })
}