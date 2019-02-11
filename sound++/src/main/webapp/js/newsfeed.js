function news(){
  $(document).ready(function() {
	//feed to parse
	var feed = "https://rss.cbc.ca/lineup/arts.xml";
	
	$.ajax(feed, {
		accepts:{
			xml:"application/xml"
		},
		dataType:"rss-xml",
                crossDomain: true,
		success:function(data) {
			//Credit: http://stackoverflow.com/questions/10943544/how-to-parse-an-rss-feed-using-javascript

			$(data).find("item").each(function () { // or "item" or whatever suits your feed
				var el = $(this);
				console.log("------------------------");
				console.log("title      : " + el.find("title").text());
				console.log("link       : " + el.find("link").text());
				console.log("description: " + el.find("description").text());
			});
	

		}	
	});
	
});
 
}



$(document).ready(function(){
  $('.multiple-items').slick({
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 3,
    accessibility: true,
    autoplay: true,
    autoplaySpeed: 3000,
    prevArrow: "#prev",
    nextArrow: "#next"
  });
  
  news();
});