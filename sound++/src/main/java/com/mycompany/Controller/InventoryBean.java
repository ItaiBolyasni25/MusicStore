package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author aantoine97
 */
@Named(value = "inventoryBean")
@SessionScoped
public class InventoryBean implements Serializable {

    private boolean success;
    private boolean fail;
    private final Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

    // Track
    private String albumName;
    private String trackTitle;
    private String songwriter;
    private String playLength;
    private String trackGenre;
    private double trackCost;
    private double trackListPrice;
    private double trackSalePrice;

    // Album
    private String albumTitle;
    private Date releaseDate;
    private String recordingLabel;
    private int numberSongs;
    private double albumCost;
    private double albumListPrice;
    private double albumSalePrice;
    private Part image;
    private String albumGenre;

    @Inject
    private DAO dao;

    public String addTrack() {

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Track track = new Track();

        if (!albumName.isEmpty()) {
            List<Album> albums = dao.find(new Album(), "title = '" + albumName + "'");
            if (!albums.isEmpty()) {
                track.setAlbum(albums.get(0));
            }
        } else {
            Album single = new Album();
            single.setTitle(trackTitle);
            single.setReleasedate(sqlDate);
            single.setAddedDate(sqlDate);
            single.setLabel(" ");
            single.setNumberofsong(1);
            single.setCost(trackCost);
            single.setList_price(trackListPrice);
            single.setSale_price(trackSalePrice);
            single.setGenre(trackGenre);

            dao.write(single);

            track.setAlbum(single);
        }

        track.setTitle(trackTitle);
        track.setSongwriter(songwriter);
        track.setPlay_length(playLength);
        track.setGenre(trackGenre);
        track.setCost(trackCost);
        track.setList_price(trackListPrice);
        track.setSale_price(trackSalePrice);
        track.setDate_added(sqlDate);
        track.setIndividual(true);
        dao.write(track);
        success = true;
        fail = false;

        return "manager/inventory.xhtml";
    }

    public String addAlbum() {
        try {
            Album album = new Album();
            album.setTitle(albumTitle);
            album.setReleasedate(new java.sql.Date(releaseDate.getTime()));
            album.setAddedDate(new java.sql.Date(date.getTime()));
            album.setLabel(recordingLabel);
            album.setNumberofsong(numberSongs);
            album.setCost(albumCost);
            album.setList_price(albumListPrice);
            album.setSale_price(albumSalePrice);
            album.setImage(saveAlbumCover(image));
            album.setGenre(albumGenre);

            //dao.write(album);
            success = true;
            fail = false;
        } catch (Exception e) {
            success = false;
            fail = true;
        }
        return "manager/inventory.xhtml";
    }

    public void editTrack(int track_id) {
        try {
            Track updated = dao.read(new Track(), track_id).get(0);
            updated.setList_price(trackListPrice);
            updated.setSale_price(trackSalePrice);
            updated.setCost(trackListPrice - (trackListPrice * (trackSalePrice / 100)));
            dao.updateEntity(updated);

            success = true;
            fail = false;
        } catch (Exception e) {
            success = false;
            fail = true;
        }
    }

    public void editAlbum(int album_id) {
        try {
            Album updated = dao.read(new Album(), album_id).get(0);
            updated.setList_price(albumListPrice);
            updated.setSale_price(albumSalePrice);
            updated.setCost(albumListPrice - (albumListPrice * (albumSalePrice / 100)));
            dao.updateEntity(updated);

            success = true;
            fail = false;
        } catch (Exception e) {
            success = false;
            fail = true;
        }
    }

    public void deleteTrack(int track_id) {
        try {
            Track updated = dao.read(new Track(), track_id).get(0);
            updated.setRemoval_status(true);
            updated.setRemoval_date(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            dao.updateEntity(updated);

            success = true;
            fail = false;
        } catch (Exception e) {
            success = false;
            fail = true;
        }
    }

    public void deleteAlbum(int album_id) {
        try {
            Album updated = dao.read(new Album(), album_id).get(0);
            updated.setRemoval_status(true);
            updated.setRemoval_date(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            dao.updateEntity(updated);

            success = true;
            fail = false;
        } catch (Exception e) {
            success = false;
            fail = true;
        }
    }

    public String backToInventory() {
        return "manager/inventory.xhtml";
    }

    // ---------- Helper methods ---------- //
    public String saveAlbumCover(Part album) {
        try {
            InputStream in = album.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024]; // 1024 is buffer size
            int len;
            while ((len = in.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

            byte[] allBytes = os.toByteArray();

            String randomFileName = java.util.UUID.randomUUID().toString();
            String extension = album.getSubmittedFileName().substring(album.getSubmittedFileName().lastIndexOf("."));
            String pathToSave = "assets/album_covers/" + randomFileName + extension;
            String filePath = "C:\\Users\\austi\\Desktop\\SchoolStuff\\JavaServerSide\\Project\\csdmusicstore\\sound++\\src\\main\\webapp\\" + pathToSave;
            
            System.out.println("---\n\n " + filePath + "\n\n---");

            Files.write(Paths.get(filePath), allBytes, StandardOpenOption.CREATE);
            return pathToSave;
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n\n");
            e.printStackTrace();
            return "";
        }
    }

    // ---------- Getters and setters ---------- //
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getSongwriter() {
        return songwriter;
    }

    public void setSongwriter(String songwriter) {
        this.songwriter = songwriter;
    }

    public String getPlayLength() {
        return playLength;
    }

    public void setPlayLength(String playLength) {
        this.playLength = playLength;
    }

    public String getTrackGenre() {
        return trackGenre;
    }

    public void setTrackGenre(String trackGenre) {
        this.trackGenre = trackGenre;
    }

    public double getTrackCost() {
        return trackCost;
    }

    public void setTrackCost(double trackCost) {
        this.trackCost = trackCost;
    }

    public double getTrackListPrice() {
        return trackListPrice;
    }

    public void setTrackListPrice(double trackListPrice) {
        this.trackListPrice = trackListPrice;
    }

    public double getTrackSalePrice() {
        return trackSalePrice;
    }

    public void setTrackSalePrice(double trackSalePrice) {
        this.trackSalePrice = trackSalePrice;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordingLabel() {
        return recordingLabel;
    }

    public void setRecordingLabel(String recordingLabel) {
        this.recordingLabel = recordingLabel;
    }

    public int getNumberSongs() {
        return numberSongs;
    }

    public void setNumberSongs(int numberSongs) {
        this.numberSongs = numberSongs;
    }

    public double getAlbumCost() {
        return albumCost;
    }

    public void setAlbumCost(double albumCost) {
        this.albumCost = albumCost;
    }

    public double getAlbumListPrice() {
        return albumListPrice;
    }

    public void setAlbumListPrice(double albumListPrice) {
        this.albumListPrice = albumListPrice;
    }

    public double getAlbumSalePrice() {
        return albumSalePrice;
    }

    public void setAlbumSalePrice(double albumSalePrice) {
        this.albumSalePrice = albumSalePrice;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }
}
