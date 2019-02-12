"use strict";
$(document).ready(function () {
    $("tr").each(function () {
        $(this).click(function () {
            $("#info").css('display', 'block');
            $("#albumTable").css('display', 'none');
            var id = $(this).attr('id');
            /*var request = $.ajax({
             url: "getInfo",
             method: "GET",
             data: {Name: "id"}//,
             //dataType: "json"
             });*/
            var request = $.ajax({
                url: "getInfo",
                data: {id:id},
                success: function (data) {
                    console.log(data.info[0].image);
                    $("#albumCover").attr('src', data.info[0].image);
                    $("#title").text(data.info[0].title);
                    //$("#artist")
                    $("#released_date").text(data.info[0].released_date);
                    $("#cost").text(data.info[0].cost);
                }
            });
        });


    });
});

