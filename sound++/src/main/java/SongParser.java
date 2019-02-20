
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 1633867
 */
@SessionScoped
@Named("SongParser")
public class SongParser implements Serializable {

    @Inject
    private DAO dao;

    public void onLoad() throws Exception {
        readCSVFile();
    }

    private void albumParser(String[] splittedCsv) throws ParseException {
        List<Artist> artists = dao.find(new Artist(), "name = '" + splittedCsv[3] + "'");
        Artist artist = new Artist();
        if (artists.size() < 1) {
            artist.setName(splittedCsv[3]);
        } else {
            artist = artists.get(0);
        }
        artists.add(0, artist);
        Album album = new Album();
        List<Album> albums = dao.find(new Album(), "title = '" + splittedCsv[0] + "'");
        if (albums.size() < 1) {
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
        } else {
            album = albums.get(0);
        }
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

    private void readCSVFile() throws IOException, ParseException {
        Path p = Paths.get("C:\\Users\\Gabriela\\Desktop\\csdmusicstore\\sound++\\src\\main\\resources\\dataPoints.csv");
        List<String> list = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (int i = 1; i < list.size(); i++) {
            String[] splittedCsv = list.get(i).split(",");
            if (splittedCsv[0].contains("'")) {
                splittedCsv[0] = splittedCsv[0].replace("'", "''");
            }
            albumParser(splittedCsv);
        }
    }

    private java.sql.Date newDateFormat(String date) throws ParseException {
        String newString = "";
        String[] elements = date.split("/");
        newString += elements[2] + "-" + elements[0] + "-" + elements[1];
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newdate = sdf1.parse(newString);
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime());
        return sqlDate;
    }

}
