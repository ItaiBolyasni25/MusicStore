package com.mycompany.Controller;

import com.mycompany.Model.News;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gabriela
 */
@Named(value = "newsBean")
@SessionScoped
public class NewBean implements Serializable{

    @Inject
    private DAO DAO;
        
    private String feed;

    public List<String> getRecommendations() {
        List<String> news = new ArrayList<String>();
        news.add("Global News : https://globalnews.ca/entertainment/feed/");
        news.add("CTV News : https://www.ctvnews.ca/rss/ctvnews-ca-entertainment-public-rss-1.822292");
        news.add("NY Times : http://rss.nytimes.com/services/xml/rss/nyt/Music.xml");
        return news;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }
    
    public String newfeed(){
        List<News> currentRss = DAO.find(new News(), "used LIKE '1'");
        for(int i = 0 ; i < currentRss.size(); i++){
            currentRss.get(i).setUsed("0");
            DAO.updateEntity(currentRss.get(i));
        }
        News newRss = new News(feed, "1");
        DAO.write(newRss);
        return "manager/surveymngt.xhtml";
    }
    
    public String getCurrentFeed(){
        News news = DAO.find(new News(), "used LIKE '1'").get(0);
        return news.getFeed();
    }
    
    public List<News> getNewsFeeds(){
        return DAO.findAll(new News());
    }
    
    
}
