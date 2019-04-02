/** 
 *  Requests RSS feed to rss2json API 
 */
function news(newsfeed) {

    //https://rss2json.com/docs API used
    //Feed to parse
    var feed = "https://www.cbc.ca/cmlink/rss-arts";
    //Do Ajax request to get RSS feed 
    $.ajax({
        url: 'https://api.rss2json.com/v1/api.json',
        method: 'GET',
        dataType: 'json',
        data: {
            rss_url: feed,
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
    var listItem = $(".band").children(); 
    if (response.status != 'ok') {
        throw response.message;
    }
    //Loop through most recent items of the RSS news feed
    for (var i = 0; i < 7; i++)
    {
        var item = response.items[i];
        var link = $(listItem[i]).children();
        console.log($(link).children().get(0));
        $(link).attr("href", item.link);
        $($(link).children().get(0)).css('background-image', 'url(' + item.thumbnail + ')');
        $($($(link[0]).children()).children().get(0)).text(item.title);
        $($($(link[0]).children()).children().get(1)).text(item.author);
    }
    //Initialize slide show of news 

}
