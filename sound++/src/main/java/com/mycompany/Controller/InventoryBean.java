package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String artist;
    private String playLength;
    private String trackGenre;
    private double trackListPrice;
    private double trackSalePrice;

    // Album
    private String albumTitle;
    private Date releaseDate;
    private String recordingLabel;
    private int numberSongs;
    private double albumListPrice;
    private double albumSalePrice;
    private Part image;
    private String albumGenre;

    private DAO dao;

    @Inject
    public InventoryBean(DAO dao) {
        this.dao = dao;
    }

    public InventoryBean() {
    }

    public String addTrack() {
        try {
            List<Artist> artists = new ArrayList<>();
            Artist newArtist = new Artist();
            Album single = new Album();
            newArtist.setName(artist);

            artists.add(newArtist);

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            Track track = new Track();

            if (!albumName.isEmpty()) {
                List<Album> albums = dao.customFindDB(new Album(), "select t from Album t inner join t.artists a where a.name = '" + artist + "' and t.title = '" + albumName + "'");
                if (!albums.isEmpty()) {
                    track.setAlbum(albums.get(0));
                }
            } else {
                if (dao.find(new Artist(), "name = '" + artist + "'").isEmpty()) {
                    single.setArtists(artists);
                }
                single.setTitle(trackTitle);
                single.setReleasedate(sqlDate);
                single.setAddedDate(sqlDate);
                single.setLabel(" ");
                single.setNumberofsong(1);
                single.setCost(trackListPrice - (trackListPrice * (trackSalePrice / 100)));
                single.setList_price(trackListPrice);
                single.setSale_price(trackSalePrice);
                single.setGenre(trackGenre);

                dao.write(single);

                if (!dao.find(new Artist(), "name = '" + artist + "'").isEmpty()) {
                    Artist existing = dao.find(new Artist(), "name = '" + artist + "'").get(0);
                    List<Album> allAlbums = dao.findAll(new Album());
                    Album justAdded = allAlbums.get(allAlbums.size() - 1);
                    List<Artist> albumArtists = new ArrayList<>();
                    albumArtists.add(existing);
                    justAdded.setArtists(albumArtists);
                    dao.updateEntity(justAdded);
                }

                track.setAlbum(single);
            }

            track.setTitle(trackTitle);
            track.setSongwriter(artist);
            track.setPlay_length(playLength);
            track.setGenre(trackGenre);
            track.setCost(trackListPrice - (trackListPrice * (trackSalePrice / 100)));
            track.setList_price(trackListPrice);
            track.setSale_price(trackSalePrice);
            track.setDate_added(sqlDate);
            track.setIndividual(true);
            dao.write(track);
            success = true;
            fail = false;
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage() + "\n\n");
            e.printStackTrace();
            success = false;
            fail = true;
        }

        return "manager/inventory.xhtml";
    }

    public String addAlbum() {
        try {
            Album album = new Album();

            List<Artist> artists = new ArrayList<>();

            if (dao.find(new Artist(), "name = '" + artist + "'").isEmpty()) {
                Artist newArtist = new Artist();
                newArtist.setName(artist);

                artists.add(newArtist);
                album.setArtists(artists);
            }

            album.setTitle(albumTitle);
            album.setReleasedate(new java.sql.Date(releaseDate.getTime()));
            album.setAddedDate(new java.sql.Date(date.getTime()));
            album.setLabel(recordingLabel);
            album.setNumberofsong(numberSongs);
            album.setCost(albumListPrice - (albumListPrice * (albumSalePrice / 100)));
            album.setList_price(albumListPrice);
            album.setSale_price(albumSalePrice);
            if (image != null) {
                album.setImage("assets/album_covers/" + image.getSubmittedFileName());
            }
            album.setGenre(albumGenre);
            
            if (!dao.customFindDB(new Album(), "select t from Album t inner join t.artists a where a.name = '" + artist + "' "
                    + "and t.title = '" + albumName + "'").isEmpty()) 
                throw new Exception();

            dao.write(album);

            if (!dao.find(new Artist(), "name = '" + artist + "'").isEmpty()) {
                Artist existing = dao.find(new Artist(), "name = '" + artist + "'").get(0);
                List<Album> allAlbums = dao.findAll(new Album());
                Album justAdded = allAlbums.get(allAlbums.size() - 1);
                List<Artist> albumArtists = new ArrayList<>();
                albumArtists.add(existing);
                justAdded.setArtists(albumArtists);
                dao.updateEntity(justAdded);
            }

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
        return "inventory.xhtml";
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
