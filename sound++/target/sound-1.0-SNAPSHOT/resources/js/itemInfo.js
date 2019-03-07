"use strict";

var currentlyShowingAlbum = false;

function setAlbumVisible() {
    if(!currentlyShowingAlbum)
        return;
    $(window).scrollTop(0);
    $("#info").css('display', 'block');
    $("#albumTable").css('display', 'none');
}

function assignEventsRows() {
    $(".customLink").mouseover(function () {
        $(".customLink").css('text-decoration', 'none');
    });
    $(".customLink").click(function () {
        currentlyShowingAlbum = true;
    });
    $("tr").each(function () {
        $(this).click(function (event) {
            if($(event.target).attr("class")==="customLink")
                return;
            $(this).find("td").get(1).getElementsByTagName("form")[0].children[1].click();
            
        });
    });
}

function setAlbumList() {
    var albumList = $("#albumList");
    albumList.attr("href","#");
    albumList.click(function (event) {
        event.preventDefault();
        $("#info").css('display', 'none');
        $("#albumTable").css('display', 'block');
        currentlyShowingAlbum = false;
        return false;
    });

}



