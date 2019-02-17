package com.mycompany.Utilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.Model.Track;
import com.mycompany.Model.Album;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 1633867
 */
public class SongParser {

    public static void main(String[] args) throws IOException {

        Path p = Paths.get("dataPoints.csv");
        List<String> list = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (int i = 1; i < list.size(); i++) {
            String[] splittedCsv = list.get(i).split(",");
            /*
                0 - album title
                1 - album number
                2 - track title
                3 - artist
                4 - songwriter
                5 - play length
                6 - selection number
                7 - category
                8 - album cover img
                9 - cost price
                10 - list price
                11 - sale price
                12 - date entered
                13 - sold as 
                14 - removal status
                15 - removal date
             */
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("songstore");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("FROM Album a WHERE a.title = '" + splittedCsv[0] + "'", Album.class);
            List<Album> albums = (List<Album>)query.getResultList();
            Album album = new Album();
            if (albums.size() < 1) {
                album.setTitle(splittedCsv[0]);
                album.setReleaseDate(new Timestamp(System.currentTimeMillis()));
                album.setLabel(splittedCsv[3]);
                album.setAddedDate(new Timestamp(System.currentTimeMillis()));
                album.setCost(20);
                album.setListPrice(20);
                album.setSalePrice(0);
                album.setRemovalStatus(false);
                album.setRemovalDate(null);
                album.setImage(null);
            } else {
                album = albums.get(0);
            }
            
            Track track = new Track();
            track.setSelection_number(Integer.parseInt(splittedCsv[6]));
            track.setTitle(splittedCsv[2]);
            track.setSongwriter(splittedCsv[4]);
            track.setPlay_length(null);
            track.setGenre(splittedCsv[7]);
            track.setAlbum(album);
            track.setCost(Double.parseDouble(splittedCsv[9]));
            track.setList_price(Double.parseDouble(splittedCsv[10]));
            track.setSale_price(0);
            track.setDate_added(new Timestamp(System.currentTimeMillis()));
            track.setIndividual(!(splittedCsv[13]).equals("Album"));
            track.setRemoval_status(false);
            track.setRemoval_date(null);
            
            em.persist(track);
            em.getTransaction().commit();

        }

    }
}
