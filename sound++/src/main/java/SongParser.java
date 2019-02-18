/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Model.Album;
import com.mycompany.Persistence.DAO;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1633867
 */
public class SongParser {

    private final static Logger LOG = LoggerFactory.getLogger(SongParser.class); 
    private static DAO dao;
    public static void main(String[] args) throws IOException, ParseException {
      
       dao = new DAO();
       new SongParser().readCSVFile();
    }

    private void albumParser(String[] splittedCsv) throws ParseException  {
        List<Artist> artists = dao.find(new Artist(), "name = '" + splittedCsv[3] + "'");
        Artist artist = new Artist();
        if (artists.size() < 1) {
            artist.setName(splittedCsv[3]);
        } else {
            artist = artists.get(0);
        }
        artists.add(0, artist);
        Album album = new Album();
        List<Album> albums = dao.find(new Album(), "title = '" + splittedCsv[1] + "'");
        if (albums.size() < 1) {
            album.setTitle(splittedCsv[1].trim());
            Date javaDate = new Date();
            album.setReleasedate(newDateFormat(splittedCsv[2]));
            album.setArtists(artists);
            album.setLabel(splittedCsv[4].trim());
            album.setAddedDate(new java.sql.Date(javaDate.getTime()));
            album.setCost(Double.parseDouble(splittedCsv[7]));
            album.setListprice(Double.parseDouble(splittedCsv[8]));
            album.setSaleprice(0);
            album.setRemovalstatus(false);
            album.setRemovaldate(null);
            album.setNumberofsong(Integer.parseInt(splittedCsv[5]));
            album.setImage(splittedCsv[12]);
            album.setGenre(splittedCsv[13]);
        } else {
            album = albums.get(0);
        }
        dao.write(album);
    }

    private  void trackParser(String[] splittedCsv) {

        Track track = new Track();
        track.setSelection_number(Integer.parseInt(splittedCsv[6]));
        track.setTitle(splittedCsv[2].trim());
        track.setSongwriter(splittedCsv[4]);
        String[] play_length = splittedCsv[5].split(":");
        track.setPlay_length(Integer.parseInt(play_length[0]) + ":" + "" + Integer.parseInt(play_length[1]));
        track.setGenre(splittedCsv[7]);
        track.setAlbum(dao.find(new Album(), "title = '" + splittedCsv[0].trim() + "'").get(0));
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

    private void readCSVFile() throws IOException, ParseException {
        Path p = Paths.get("albumData.csv");
        List<String> list = Files.readAllLines(p, StandardCharsets.UTF_8);
        String[] splittedCsv = null;
        for (int i = 1; i < list.size(); i++) {
            splittedCsv = list.get(i).split(",");
            if (splittedCsv[0].contains("'")) {
                splittedCsv[0] = splittedCsv[0].replace("'", "''");
            }
            albumParser(splittedCsv);
        }
        p = Paths.get("trackData.csv");
        list = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (int i = 1; i < list.size(); i++) {
            splittedCsv = list.get(i).split(",");
            if (splittedCsv[0].contains("'")) {
                splittedCsv[0] = splittedCsv[0].replace("'", "''");
            }
            trackParser(splittedCsv);
        }
    }
     private java.sql.Date newDateFormat(String date) throws ParseException{
        String newString = "";
        String[] elements = date.split("/");
        newString+= elements[2] + "-" + elements[0] + "-" +elements[1];
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newdate = sdf1.parse(newString);
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime()); 
        return sqlDate;
    }

}
