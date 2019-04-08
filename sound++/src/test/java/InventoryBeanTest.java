
import com.mycompany.Controller.InventoryBean;
import com.mycompany.Controller.ReviewBean;
import com.mycompany.Controller.SelectedAlbum;
import com.mycompany.Controller.SelectedTrack;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.SongParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author aantoine97
 */
@Ignore
@RunWith(Arquillian.class)
public class InventoryBeanTest {

    @Inject
    DAO dao;

    @Resource
    UserTransaction transaction;

    @Resource(name = "java:app/jdbc/Songstore")
    DataSource ds;
    
    @Inject
    SongParser songparser;

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
                .addPackage(ReviewBean.class.getPackage())
                .addPackage(EntityModel.class.getPackage())
                .addPackage(Track.class.getPackage())
                .addPackage(SelectedAlbum.class.getPackage())
                .addPackage(SelectedTrack.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(SongParser.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml")
                .addAsResource("MusicStore_create.sql")
                .addAsResource("dataPoints.csv");//.addAsLibraries(dependencies);
        return webArchive;
    }
    
    @Before
    public void seedDatabase() throws IOException, ParseException, SQLException {
        final String seedDataScript = loadAsString("MusicStore_create.sql");
        try (Connection connection = ds.getConnection()) {
            for (String statement : splitStatements(new StringReader(
                    seedDataScript), ";")) {
                connection.prepareStatement(statement).execute();
            }
          
        } catch (SQLException e) {
            throw new RuntimeException("Failed seeding database", e);
        }
          songparser.readCSVFile(Thread.currentThread()
               .getContextClassLoader().getResourceAsStream("dataPoints.csv"));
    }

    /**
     * The following methods support the seedDatabse method
     */
    private String loadAsString(final String path) {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(path)) {
            return new Scanner(inputStream).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close input stream.", e);
        }
    }

    private List<String> splitStatements(Reader reader,
            String statementDelimiter) {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final StringBuilder sqlStatement = new StringBuilder();
        final List<String> statements = new LinkedList<>();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || isComment(line)) {
                    continue;
                }
                sqlStatement.append(line);
                if (line.endsWith(statementDelimiter)) {
                    statements.add(sqlStatement.toString());
                    sqlStatement.setLength(0);
                }
            }
            return statements;
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing sql", e);
        }
    }

    private boolean isComment(final String line) {
        return line.startsWith("--") || line.startsWith("//")
                || line.startsWith("/*");
    }


    @Test
    public void testAddTrack1() {
        InventoryBean adder = new InventoryBean(dao);
        adder.setAlbumName("");
        adder.setArtist("Random artist");
        adder.setTrackTitle("Some title");
        adder.setPlayLength("4:01");
        adder.setTrackGenre("Rock");
        adder.setTrackListPrice(2.00);
        adder.setTrackSalePrice(10);
        
        adder.addTrack();
        Track latest = dao.findAll(new Track()).get(dao.findAll(new Track()).size() - 1);
        Assert.assertTrue("Some title".equals(latest.getTitle()) && "4:01".equals(latest.getPlay_length()));
    }
    
    @Test
    public void testAddTrack2() {
        InventoryBean adder = new InventoryBean(dao);
        adder.setAlbumName("Kamikaze");
        adder.setArtist("Eminem");
        adder.setTrackTitle("title");
        adder.setPlayLength("3:01");
        adder.setTrackGenre("Rock");
        adder.setTrackListPrice(2.00);
        adder.setTrackSalePrice(10);
        
        adder.addTrack();
        Track latest = dao.findAll(new Track()).get(dao.findAll(new Track()).size() - 1);
        Assert.assertTrue("title".equals(latest.getTitle()) && "3:01".equals(latest.getPlay_length()));
    }
    
    @Test
    public void testAddAlbum1() {
        InventoryBean adder = new InventoryBean(dao);
        adder.setAlbumTitle("Some album");
        adder.setArtist("Me");
        adder.setReleaseDate(new Date());
        adder.setRecordingLabel("Some label");
        adder.setNumberSongs(6);
        adder.setAlbumListPrice(2.00);
        adder.setAlbumSalePrice(10);
        adder.setAlbumGenre("Rap");
        
        adder.addAlbum();
        Album latest = dao.findAll(new Album()).get(dao.findAll(new Album()).size() - 1);
        Assert.assertTrue("Some album".equals(latest.getTitle()) && "Some label".equals(latest.getLabel()));
    }
    
    @Test
    public void testAddAlbum2() {
        InventoryBean adder = new InventoryBean(dao);
        adder.setAlbumTitle("Some other album");
        adder.setArtist("Eminem");
        adder.setReleaseDate(new Date());
        adder.setRecordingLabel("SlimShadyRecords");
        adder.setNumberSongs(6);
        adder.setAlbumListPrice(2.00);
        adder.setAlbumSalePrice(10);
        adder.setAlbumGenre("Rap");
        
        adder.addAlbum();
        Album latest = dao.findAll(new Album()).get(dao.findAll(new Album()).size() - 1);
        Assert.assertTrue("Some other album".equals(latest.getTitle()) && "SlimShadyRecords".equals(latest.getLabel()));
    }
    
    @Test
    public void testEditTrack() {
        InventoryBean adder = new InventoryBean(dao);
        adder.setTrackListPrice(2.00);
        adder.setTrackSalePrice(10);
        
        adder.editTrack(3);
        Track latest = dao.read(new Track(), 3).get(0);
        Assert.assertTrue(latest.getList_price() == 2.00 && latest.getSale_price() == 10);
    }
    
    @Test
    public void testEditAlbum() {
        InventoryBean adder = new InventoryBean(dao);       
        adder.setAlbumListPrice(2.00);
        adder.setAlbumSalePrice(10);
        
        adder.editAlbum(1);
        Album latest = dao.read(new Album(), 1).get(0);
        Assert.assertTrue(latest.getList_Price() == 2.00 && latest.getSale_price() == 10);
    }
    
    @Test
    public void testDeleteTrack() {
        InventoryBean adder = new InventoryBean(dao);
        int lastTrackId = dao.findAll(new Track()).get(dao.findAll(new Track()).size() - 1).getId();
        
        adder.deleteTrack(lastTrackId);
        Track track = dao.read(new Track(), lastTrackId).get(0);
        Assert.assertTrue(track.isRemoval_status());
    }
    
    @Test
    public void testDeleteAlbum() {
        InventoryBean adder = new InventoryBean(dao);
        int lastAlbumId = dao.findAll(new Album()).get(dao.findAll(new Album()).size() - 1).getId();
        
        adder.deleteAlbum(lastAlbumId);
        Album album = dao.read(new Album(), lastAlbumId).get(0);
        Assert.assertTrue(album.isRemoval_status());
    }
}
