/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Controller.AlbumTrackBean;
import com.mycompany.Controller.SearchBean;
import com.mycompany.Model.Album;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.SongParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.junit.Before;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author maian
 */
@Ignore
@RunWith(Arquillian.class)
public class SearchBeanTest {

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
                .addPackage(EntityModel.class.getPackage())
                .addPackage(Album.class.getPackage())
                .addPackage(Track.class.getPackage())
                .addPackage(AlbumTrackBean.class.getPackage())
                .addPackage(SongParser.class.getPackage())
                .addPackage(DataSource.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml").addAsResource("MusicStore_create.sql")
                 .addAsResource("dataPoints.csv")
                ;//.addAsLibraries(dependencies);
        return webArchive;
    }

    @Inject
    private DAO dao;
    @Inject
    private SearchBean search;
    @Inject
    private SongParser songparser;
    private String pattern;
     @Resource(name = "java:app/jdbc/Songstore")
    private DataSource ds; 

    @Test
    public void patternAlbumChanged() throws ParseException {
        pattern = "H";
        List<String> expectedAlbum = new ArrayList<>();
        List<String> actualAlbum = new ArrayList<>();
        expectedAlbum.add("Heard It In A Past Life [Explicit]");
        search.setPattern(pattern);
        search.updateView();
        for (Album al : search.getAlbums()) {
            actualAlbum.add(al.getTitle());
        }
        Assert.assertArrayEquals(expectedAlbum.toArray(), actualAlbum.toArray());
    }

    @Test
    public void patternTrackChanged() throws ParseException {
        pattern = "H";
        List<String> expectedTrack = new ArrayList<>();
        List<String> actualTrack = new ArrayList<>();
        expectedTrack.add("Havana (featuring Young Thug)");
        expectedTrack.add("Homesick");
       search.setPattern(pattern);
       search.updateView();
       for(Track al : search.getTracks()){
           actualTrack.add(al.getTitle());
       }
       Assert.assertArrayEquals(expectedTrack.toArray(), actualTrack.toArray());
       }
     @Before
    public void seedDatabase() throws IOException, ParseException {
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
    }
}
