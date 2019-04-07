"use strict";

document.addEventListener("DOMContentLoaded", function () {   
    var box = document.getElementsByClassName("unique")[0];
    box.addEventListener("click", function () {
        var stars = document.getElementsByClassName("toRate");
        var starsToChange = box.value;

        for (var j = 0; j < starsToChange; j++) {
            var star = stars[j];
            star.style.color = "gold";
        }

        for (j = starsToChange; j < stars.length; j++) {
            var star = stars[j];
            star.style.color = "grey";
        }
    });
});

