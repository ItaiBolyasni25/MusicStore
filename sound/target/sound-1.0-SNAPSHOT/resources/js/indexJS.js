"use strict"
var sildeshow;
$(document).ready(function () {
    var element = document.querySelector('.slideshows');
     sildeshow = bamboo(element, 'roll',{
        speed: 3100,
        autoFitImg: false,
         hideArrow: true

    });
    
});
function stopSlide(){
    sildeshow.stop();
}
function playSlide(){
    sildeshow.run();

}