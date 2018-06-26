$(function () {
	// 导航栏下拉菜单
	$(".title_tab").mouseover(function () {
		$(this).find(".second_title").show();

        $(".title_tab .second_title a").mouseover(function () {
            $(this).addClass("title_over");
        })
        $(".title_tab .second_title a").mouseout(function () {
            $(this).removeClass("title_over");
        })
    });
    $(".title_tab").mouseout(function () {
        $(this).find(".second_title").hide();
    });

})