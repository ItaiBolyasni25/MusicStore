"use strict";
$(document).ready(function () {
    var id;

    $("tr").each(function () {
        if ($(this).attr('id') !== "header") {
            $(this).click(function () {
                $("#info").css('display', 'block');
                $("#albumTable").css('display', 'none');
                id = $(this).attr('id');
                sendRequest(id);
            });
        }
    });
    $("#albumsList").click(function () {
        $("#info").css('display', 'none');
        $("#albumTable").css('display', 'block');
    });
}
);
function displayData(data) {
    var artistNames = "";
    $("#albumCover").attr('src', data.info[0].image);
    $("#title").text(data.info[0].title);
    $("#titleAlbum").text(data.info[0].title);
    $("#cost").text("CA$ " + data.info[0].cost);
    for (var i = 0; i < data.info[0].artists.length; i++) {
        if (data.info[0].artists.length === 1) {
            artistNames += data.info[0].artists[i];
            break;
        }
        artistNames += data.info[0].artists[i] + ", ";
    }
    $("#artist").text(artistNames);
}
function createTabContent(data) {
    $("#releasedate").append(document.createTextNode(data.info[0].released_date));
    $("#addeddate").append(document.createTextNode(data.info[0].added_date));
    $("#label").append(document.createTextNode(data.info[0].label));
}
function sendRequest(id) {
    var request = $.ajax({
        url: "getInfo",
        data: {id: id},
        success: function (data) {
            displayData(data);
            createTabContent(data);
        }
    });
}



