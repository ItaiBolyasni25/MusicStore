"use strict";

var currentlyShowingAlbum = false;

function setAlbumVisible() {
    if(!currentlyShowingAlbum)
        return;
    $("#info").css('display', 'block');
    $("#albumTable").css('display', 'none');
}

function assignEventsRows() {
    $(".customLink").mouseover(function () {
        $(".customLink").css('text-decoration', 'none');
        console.log($(this).children().get(1));
      $(this).children(".albumInfo").css("display",'block');
       $(this).children(".albumCover").css('display', 'none');
    });
    $(".customLink").click(function () {
        currentlyShowingAlbum = true;
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



