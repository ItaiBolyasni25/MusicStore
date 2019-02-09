function news(){
  $.ajax({
          url: 'https://api.rss2json.com/v1/api.json',
          method: 'GET',
          dataType: 'json',
          data: {
              rss_url: encodeURI("https://abcnews.go.com/abcnews/entertainmentheadlines")
          }
  }).done(function (response) {
      if(response.status != 'ok'){ throw response.message; }

      console.log('====== ' + response.feed.title + ' ======');

      for(var i in response.items){
          var item = response.items[i];
          console.log(item.title);

      }
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