"use strict";
function assignButtons() {
    //$(".albumButton").click(function() {})
}

function setPattern(obj) {
    $(".patternTextBox").val(obj.innerText);
    hideSearchList();
}

function hideSearchList() {
    $(".searchList").css('display', 'none');
}

function showSearchList() {
    $(".searchList").css('display', 'flex');
}


$(document).ready(function () {
    $(window).click(hideSearchList)
    $(".searchForm").click(function(event) {
        event.stopPropagation();
    })
});
