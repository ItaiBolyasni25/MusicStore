function news() {
    
    
    
        //https://rss2json.com/docs
    
    
        //feed to parse
        var feed = "https://rss.cbc.ca/lineup/arts.xml";

        $.ajax({
            url: 'https://api.rss2json.com/v1/api.json',
            method: 'GET',
            dataType: 'json',
            data: {
                rss_url: feed
            }
        }).done(function (response) {
            if (response.status != 'ok') {
                throw response.message;
            }

            console.log('====== ' + response.feed.title + ' ======');

            for (var i = 0; i < 9; i++) {

                var item = response.items[i];

                var $new = $("<div>", {"class": "card mb-3 cards"});
                var $header = $("<h3>", {"class": "card-header"}).append(item.title);
                var $body = $("<div>", {"class": "card-body"});
                //var $img = $("<img>", {"class": "imgNews"}).attr("src", item.thumbnail);
                var $description = $("<p>", {"class": "card-text"}).append(item.description);
                var $link = $("<a>", {"class": "card-link"}).attr("href", item.link).append("link");
                var $footer = $("<div>", {"class": "card-footer text-muted"}).append(item.pubDate);
                
                //$body.append($img);
                $body.append($description);
                $body.append($link);
                $new.append($header);
                $new.append($body);
                $new.append($footer);
                $("#newsfeed").append($new);


                console.log(item.title);
                console.log(item.pubDate);
                console.log(item.link);
                console.log(item.description);
                console.log(item.thumbnail);
                console.log("===========");
            }
            
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
        });
        
        
    
}


$(document).ready(function () {
    
    news();
    
    
    

    
});