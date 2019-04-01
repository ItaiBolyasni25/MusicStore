$(document).ready(function () {
      $(".daterange").daterangepicker();
        $(window).click(hideSearchList);
    $(".searchForm").click(function (event) {
        event.stopPropagation();
    });
    
});
function setPattern(obj) {
    $(".patternTextBox").val(obj.innerText);
    hideSearchList();
}
function hideSearchList() {
    $(".resultSearch").css('display', 'none');
}
function showSearchList() {
    $(".resultSearch").css('display', 'flex');
}
