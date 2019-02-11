$(document).ready(function () {
    $("tr").each(function () {
        $(this).click(function () {
            $("#info").css('display', 'block');
            $("#albumTable").css('display', 'none');
            var id = $(this).attr('id');
            $.get("getInfo", {
                id: id
            }, function (responseText) {
                console.log(responseText);
            });
        });
    });
});