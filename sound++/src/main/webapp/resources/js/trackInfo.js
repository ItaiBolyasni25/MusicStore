"use strict";
$(document).ready(function () {
    $("tr").each(function () {
        $(this).click(function () {
            $("#info").css('display', 'block');
            $("#trackTable").css('display', 'none');
            var id = $(this).attr('id');
            var request = $.ajax({
                url: "getTrackInfo",
                data: {id: id},
                success: function (data) {
                    console.log(data);
                    displayData(data);
                    createTabContent(data);
                },
                error: function(ts) { alert(ts.responseText); }
            });
        });


    });
});
function displayData(data) {
    var artistNames = "";
    $("#albumCover").attr('src', data.info[0].image);
    $("#title").text(data.info[0].title);
    $("#titleTrack").text(data.info[0].title);
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
    $("#addeddate").append(document.createTextNode(data.info[0].added_date));
    $("#genre").append(document.createTextNode(data.info[0].genre));
    $("#songwriter").append(document.createTextNode(data.info[0].songwriter));
    $("#playlength").append(document.createTextNode(data.info[0].playlength));
}
