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
    var textInput;
    $(window).click(hideSearchList);
    $(".searchForm").click(function(event) {
        event.stopPropagation();
    });
    $(".filter").change(function(){
        if($("[id='searchform:filter:2']").is(':checked')){
            $("[id='searchform:pattern']").daterangepicker();
        }
        else{
        if($("[id='searchform:pattern']").data('daterangepicker')){
        $("[id='searchform:pattern']").data('daterangepicker').remove();
        }
        $(".patternTextBox").val("");
        $('.patternTextBox').keyup();
        hideSearchList();
    }
    });
    $("[id='searchform:pattern']").change(function(){
        if($("[id='searchform:filter:2']").is(':checked')){
         $('.patternTextBox').keyup();
     }
    })
});
