package com.mycompany.Utilities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Banner;
import com.mycompany.Model.News;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 1633867
 */
@ApplicationScoped
@Named("SongParser")
public class SongParser implements Serializable {

    @Inject
    private DAO dao;
    Album album;
    Artist artist;
    private boolean isLoaded = false;

    public void onLoad() throws Exception {
        if (!isIsLoaded()) {
            readCSVFile();
            this.addNewsFeed();
            this.addAds();
        }
    }

    public boolean isIsLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    private void albumParser(String[] splittedCsv) throws ParseException {
        List<Artist> artists = null;
        artists = dao.find(new Artist(), "name = '" + splittedCsv[3].trim() + "'");
        if (artists.size() < 1) {
            artist = new Artist();
            artist.setName(splittedCsv[3].trim());
            artist.setImage(splittedCsv[21].trim());
            artists.add(artist);
        }

        if (album != null && !album.getTitle().equals(splittedCsv[0].trim()) || album == null) {
            List<Album> albums = dao.find(new Album(), "title = '" + splittedCsv[0] + "'");
            if (albums.size() < 1) {
                album = new Album();
                album.setTitle(splittedCsv[0].trim());
                Date javaDate = new Date();
                album.setReleasedate(newDateFormat(splittedCsv[12]));
                album.setArtists(artists);
                album.setAddedDate(new java.sql.Date(javaDate.getTime()));
                album.setCost(Double.parseDouble(splittedCsv[17]));
                album.setList_price(Double.parseDouble(splittedCsv[16]));
                album.setSale_price(0);
                album.setRemoval_status(false);
                album.setRemoval_date(null);
                album.setNumberofsong(Integer.parseInt(splittedCsv[20]));
                album.setImage(splittedCsv[8]);
                album.setGenre(splittedCsv[18]);
                album.setLabel(splittedCsv[19].trim());
                dao.write(album);
            }
        }
        if (album != null) {
            Track track = new Track();
            track.setSelection_number(Integer.parseInt(splittedCsv[6]));
            track.setTitle(splittedCsv[2]);
            track.setSongwriter(splittedCsv[4]);
            String[] play_length = splittedCsv[5].split(":");
            track.setPlay_length(Integer.parseInt(play_length[0]) + ":" + "" + Integer.parseInt(play_length[1]));
            track.setGenre(splittedCsv[7]);

            track.setAlbum(album);
            track.setCost(Double.parseDouble(splittedCsv[9]));
            track.setList_price(Double.parseDouble(splittedCsv[10]));
            track.setSale_price(0);
            Date javaDate = new Date();
            track.setDate_added(new java.sql.Date(javaDate.getTime()));
            track.setIndividual(!(splittedCsv[13]).equals("Album"));
            track.setRemoval_status(false);
            track.setRemoval_date(null);
            dao.write(track);
        }

    }

    private void readCSVFile() throws IOException, ParseException {
        //You need to change your path here, I will ask Dan on Friday .
        InputStream inputStream
                = getClass().getClassLoader().getResourceAsStream("dataPoints.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int counter = 0;
        while ((line = reader.readLine()) != null) {
            counter++;
            if (counter > 1) {
                String[] splittedCsv = line.split(",");
                if (splittedCsv[0].contains("'")) {
                    splittedCsv[0] = splittedCsv[0].replace("'", "''");
                }
                albumParser(splittedCsv);
            }
        }
    }

    private java.sql.Date newDateFormat(String date) throws ParseException {
        String newString = "";
        String[] elements = date.split("/");
        newString += elements[2].trim() + "-" + elements[0].trim() + "-" + elements[1].trim();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newdate = sdf1.parse(newString);
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime());
        return sqlDate;
    }

    private void addNewsFeed() {
        News news = new News("https://globalnews.ca/entertainment/feed/", "1");
        dao.write(news);
    }
    
    private void addAds(){
        Banner banner = new Banner();
        banner.setBanner("ads.png");
        banner.setUsed("1");
        dao.write(banner);
    }
}
