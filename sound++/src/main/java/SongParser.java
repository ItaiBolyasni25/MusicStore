

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1633867
 */
public class SongParser {

    private final static Logger LOG = LoggerFactory.getLogger(SongParser.class);

    public static void main(String[] args) throws IOException, ParseException {
       readCSVFile("dataPoints.csv");
    }
    private static void populateDatabase(String[] splittedCsv) throws ParseException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("songstore");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("FROM Album a WHERE a.title = '" + splittedCsv[0] + "'", Album.class);
            List<Album> albums = (List<Album>) query.getResultList();

           Album album = new Album();
            if (albums.size() < 1) {
                album.setTitle(splittedCsv[0]);
                Date javaDate = new Date();
                album.setReleaseDate(newDateFormat(splittedCsv[12]));
                album.setLabel(splittedCsv[3]);
                album.setAddedDate(new java.sql.Date(javaDate.getTime()));
                album.setCost(Double.parseDouble(splittedCsv[17]));
                album.setListPrice(Double.parseDouble(splittedCsv[16]));
                album.setSalePrice(0.0);
                album.setRemovalStatus(false);
                album.setRemovalDate(null);
                album.setImage(splittedCsv[8]);
            } else {
                album = albums.get(0);
            }
            Track track = new Track();
            track.setSelection_number(Integer.parseInt(splittedCsv[6]));
            track.setTitle(splittedCsv[2]);
            track.setSongwriter(splittedCsv[4]); 
            String[] play_length = splittedCsv[5].split(":"); 
            track.setPlay_length(Integer.parseInt(play_length[0]) + ":" + ""+Integer.parseInt(play_length[1]));
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

            em.persist(track);
            em.getTransaction().commit();

        }
    private static void readCSVFile(String filename) throws IOException, ParseException{
       Path p = Paths.get(filename);
        List<String> list = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (int i = 1; i < list.size(); i++) {
           String[] splittedCsv = list.get(i).split(",");   
             populateDatabase(splittedCsv);
        }
    }
    private static java.sql.Date newDateFormat(String date) throws ParseException{
        String newString = "";
        String[] elements = date.split("/");
        newString+= elements[2] + "-" + elements[0] + "-" +elements[1];
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newdate = sdf1.parse(newString);
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime()); 
        return sqlDate;
    }
}
