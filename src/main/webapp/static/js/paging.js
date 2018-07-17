
function paging(dataNum){
    pageList = Math.ceil(data_number/dataNum);
    var span_01 = $(".page_num span:nth-child(1)");
    var span_02 = $(".page_num span:nth-child(2)");
    $(".page_num span:nth-child(2)").text(pageList);
    $(".paging_prev").click(function () {
        if(span_01.text()>1){
            span_01.text(parseInt(span_01.text())-1);
        }
    })
    $(".paging_next").click(function () {
        if(span_01.text()<span_02.text()){
            span_01.text(parseInt(span_01.text())+1);
        }
    })
    $(".firstPage").click(function () {
        if(span_01.text()!=1){
            span_01.text(1);
        }
    })
    $(".lastPage").click(function () {
        if(span_01.text()!=span_02.text()){
            span_01.text(span_02.text());
        }
    })

    $(".firstPage,.paging_prev,.lastPage,.paging_next").click(function(){
        $(".every_search").hide();
        for(var i=dataNum*(parseInt(span_01.text())-1);i<dataNum*parseInt(span_01.text());i++){
            $($(".every_search")[i]).show();
        }
    })

    $(".linTo").click(function () {
        var pageNum = parseInt($(".paging input").val());
        if(pageNum>=1&&pageNum<=parseInt(span_02.text())){
            span_01.text(pageNum);
            $(".every_search").hide();
            for(var i=dataNum*(pageNum-1);i<dataNum*pageNum;i++){
                $($(".every_search")[i]).show();
            }
        }
    })
}