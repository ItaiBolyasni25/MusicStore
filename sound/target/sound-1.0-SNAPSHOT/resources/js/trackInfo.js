"use strict";

var currentlyShowingTrack = false;

function setTrackVisible() {
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



