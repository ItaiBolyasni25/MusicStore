"use strict";

document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementsByTagName("SELECT").length > 2) {
        document.addEventListener("click", function () {
            var box = document.getElementsByTagName("SELECT")[2];
            box.addEventListener("click", function () {
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
    }
});

