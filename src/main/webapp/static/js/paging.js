/*分页器*/
document.write('<style>');
document.write('.paging{font-size: 0;text-align: center;margin-top: 50px;}');
document.write('.paging div,.paging button,.paging input{ display: inline-block; font-size: 16px; width: 60px; height: 36px; line-height: 36px;');
document.write('text-align: center; border: 1px solid #ccc; margin: 5px; cursor: pointer; outline: 0;border-radius: 3px;}');
document.write('input::-webkit-outer-spin-button, input::-webkit-inner-spin-button{ -webkit-appearance: none !important; margin: 0;}');
document.write('</style>');
document.write('<div class="paging clearfix"><button class="firstPage">首页</button><button class="paging_prev">上一页</button><div class="page_num"><span>1</span>/<span>2</span></div>');
document.write('<button class="paging_next">下一页</button><button class="lastPage">尾页</button><input type="number"><button class="linTo">跳转</button></div>');

function paging(dataNum){
    pageList = Math.ceil(data_number/dataNum);
    if(pageList>1){
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
    }else{
        $(".paging").hide();
    }
}