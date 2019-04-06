"use strict";

document.addEventListener("DOMContentLoaded", function () {   
    console.log("Script running");
    var box = document.getElementsByTagName("SELECT")[0];
    box.addEventListener("click", function () {
        console.log("Should change colors");
        var stars = document.getElementsByClassName("fa-star");
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

