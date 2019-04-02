/** 
 *  Requests RSS feed to rss2json API 
 */
function news(newsfeed) {
    
    $('.index').slick({
        infinite: true,
        speed: 300,
        fade: true,
        cssEase: 'linear',
        autoplay: true,
        arrows: false,
        dots: true,
        dotsClass: 'dots'
    });

    //https://rss2json.com/docs API used
    //Do Ajax request to get RSS feed 
    $.ajax({
        url: 'https://api.rss2json.com/v1/api.json',
        method: 'GET',
        dataType: 'json',
        data: {
            rss_url: newsfeed,
            api_key: "ahtjmmolm5ip6nd4rucf2d1hxdtuh2usxwgswjg3"
        }
    }).done(handleResponse);
}

/**
 * Handle response of RSS feed and initalize slick slide show
 * @param {JSON} response
 */
function handleResponse(response) {
    //Throw error if response not catched
    if (response.status != 'ok') {
        throw response.message;
    }
    //Loop through most recent items of the RSS news feed
    for (var i = 0; i < 9; i++)
    {
        var item = response.items[i];
        //Create html elements to hold the information about each article
        var $new = $("<div>", {"class": "card mb-3 cards"});
        var $header = $("<h3>", {"class": "card-header"}).append(item.title);
        var $body = $("<div>", {"class": "card-body"});
        var $description = $("<p>", {"class": "card-text"}).append(item.description);
        var $link = $("<a>", {"class": "card-link"}).attr("href", item.link).append("link");
        var $footer = $("<div>", {"class": "card-footer text-muted"}).append(item.pubDate);
        var $img = $("<img>", {"class": "newsimg"}).attr('src', item.enclosure.link);


        //Append elements to its correspondent parent
        $body.append($description);
        $body.append($link);
        $new.append($header);
        $new.append($body);
        $new.append($footer);
        $("#newsfeed").append($new);
    }
    //Initialize slide show of news 
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
}
