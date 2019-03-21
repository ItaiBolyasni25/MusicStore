/** 
 *  Requests RSS feed to rss2json API 
 */
function news() {
    //https://rss2json.com/docs API used
    //Feed to parse
    var feed = "https://rss.cbc.ca/lineup/arts.xml";
    //Do Ajax request to get RSS feed 
    $.ajax({
        url: 'https://api.rss2json.com/v1/api.json',
        method: 'GET',
        dataType: 'json',
        data: {
            rss_url: feed
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

$(document).ready(function () {
    //Call news function to request the latest news of the moment when page is loaded
    news();
});
