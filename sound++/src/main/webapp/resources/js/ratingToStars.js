"use strict";

document.addEventListener("DOMContentLoaded", function () {
    var ratings = document.getElementsByClassName("rating");
    if (ratings !== null) {
        for (var i = 0; i < ratings.length; i++) {
            var rating = ratings[i];
            var value = rating.innerHTML;
            var labels = [];

            for (var j = 0; j < 5; j++) {
                var label = document.createElement("label");
                label.setAttribute("class", "fa fa-star");
                labels.push(label);
            }

            for (var j = 0; j < value; j++) {
                labels[i].style.color = "gold";
            }

            rating.innerHTML = labels;
        }
    }
});