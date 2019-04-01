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
    var rangeDate = false;
    $(window).click(hideSearchList);
    $(".searchForm").click(function (event) {
        event.stopPropagation();
    });
    $("[id='searchform:filterLink']").click(function () {
       
        if (!rangeDate) {
            $(".dateSymbol").css( "opacity", 1.0 );
            $("[id='searchform:pattern']").daterangepicker();
            rangeDate = true;
        } else {
            if ($("[id='searchform:pattern']").data('daterangepicker')) {
                $("[id='searchform:pattern']").data('daterangepicker').remove();
                 $(".dateSymbol").css( "opacity", 0.4 );
                rangeDate = false;
            }
            $(".patternTextBox").val("");
            $('.patternTextBox').keyup();
            hideSearchList();
        }
        $("[id='searchform:pattern']").change(function () {
            if (rangeDate) {
                $('.patternTextBox').keyup();
            }
        })
    });
});
