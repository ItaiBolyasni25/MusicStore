/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Controller.SelectedAlbum;
import com.mycompany.Controller.SelectedTrack;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.SongParser;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author maian
 */
@Ignore
@RunWith(Arquillian.class)
public class SelectedItemTest {

    @Deployment
    public static WebArchive createDeployment() {
        /*final File[] dependencies = Maven.resolver()
                .loadPomFromFile("pom.xml")
                //.resolve("mysql:mysql-connector-java"
                //.resolve("org.assertj:assertj-core")
                .withTransitivity()
                .asFile();
        ;*/
        final WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addPackage(DAO.class.getPackage())
                .addPackage(SelectedTrack.class.getPackage())
                .addPackage(EntityModel.class.getPackage())
                .addPackage(Track.class.getPackage())
                .addPackage(SongParser.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml");//.addAsLibraries(dependencies);
        return webArchive;
    }
    @Inject
    private SongParser songparser;
    @Inject
    private DAO dao;
    private Track track;
    private Album album;
    @Inject
    SelectedTrack selected;
    @Inject
    SelectedAlbum selectedAlbum;

    @Before
    public void setUpSelectedTrackTest() throws IOException, ParseException {
        track = dao.find(new Track(), "title = '2 Kids'").get(0);
        selected.setTrack(track);
    }

    @Test
    public void getSelectedTrackTest() {
//        Assert.assertEquals("2 Kids", selected.getSelectedTrack().getTitle());
    }

//    @Test
//    public void getSimilarTrackTest() {
//        List<String> str = new ArrayList<>();
//        str.add("Alaska");
//        str.add("All I need");
//        str.add("Bad Liar");
//        track = dao.find(new Track(), "title = '2 Kids'").get(0);
//        selected.setTrack(track);
//        List<Track> acutualTrack = selected.getSimilarTrack();
//        List<String> actual = new ArrayList<>();
//        for (Track tr : acutualTrack) {
//            actual.add(tr.getTitle());
//        }
//        Assert.assertArrayEquals(str.toArray(), actual.toArray());
//    }
//
//    @Before
//    public void setUpSelectedAlbumTest() throws IOException, ParseException {
//        album = dao.find(new Album(), "title = 'A Real Good Kid [Explicit]'").get(0);
//        selectedAlbum.setSelectedAlbum(album);
//    }
//
//    @Test
//    public void getSelectedAlbumTest() {
//        Assert.assertEquals("A Real Good Kid [Explicit]", selectedAlbum.getSelectedAlbum().getTitle());
//    }
//
//    @Test
//    public void getSimilarAlbumTest() {
//        List<String> str = new ArrayList<>();
//        str.add("Heard It In A Past Life [Explicit]");
//        str.add("Mint");
//        str.add("Native Tongue");
//        album = dao.find(new Album(), "title = 'A Real Good Kid [Explicit]'").get(0);
//        selectedAlbum.setSelectedAlbum(album);
//        List<Album> acutualAlbum = selectedAlbum.getSimilarAlbum();
//        List<String> actual = new ArrayList<>();
//        for (Album al : acutualAlbum) {
//            actual.add(al.getTitle());
//        }
//        Assert.assertArrayEquals(str.toArray(), actual.toArray());
//    }
}
