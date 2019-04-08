
import com.mycompany.Controller.ReviewBean;
import com.mycompany.Controller.SelectedAlbum;
import com.mycompany.Controller.SelectedTrack;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Review;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
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
public class ReviewBeanTest {

    @Inject
    DAO dao;

    @Resource
    UserTransaction transaction;

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
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml");//.addAsLibraries(dependencies);
        return webArchive;
    }

    @Test
    public void testSaveReview() {
        User user = new User("Bob", "Bob", "something", "blahblah", "Mr");

        if (dao.find(new User(), "email = 'something'").isEmpty()) {
            dao.write(user);
        }

        Track track = dao.findAll(new Track()).get(0);
        SelectedTrack selected = new SelectedTrack(dao);
        selected.setSelectedTrack(track);

        ReviewBean reviewer = new ReviewBean(dao, selected, null);
        reviewer.setTrackOrAlbum("track");
        reviewer.setRating(3);
        reviewer.setText("AMAZING SONG");

        reviewer.saveReview(user);
        List<Review> reviews = dao.findAll(new Review());
        Review latest = reviews.get(reviews.size() - 1);
        Assert.assertEquals("AMAZING SONG", latest.getText());
    }

    @Test
    public void approveReview() {
        ReviewBean reviewer = new ReviewBean();
        reviewer.setDao(dao);
        Review firstUnapproved = dao.find(new Review(), "isApproved = 0").get(0);

        reviewer.approveReview(firstUnapproved.getReview_id());
        Review shouldBeApproved = dao.read(new Review(), firstUnapproved.getReview_id()).get(0);
        Assert.assertTrue(shouldBeApproved.getIsApproved() == true);
    }

    @Test
    public void deleteReview() {
        ReviewBean reviewer = new ReviewBean();
        reviewer.setDao(dao);
        List<Review> reviews = dao.findAll(new Review());
        int id = reviews.get(reviews.size() - 1).getReview_id();

        try {
            transaction.begin();
            reviewer.deleteReview(id);
            transaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(ReviewBeanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Review> shouldBeNull = dao.read(new Review(), id);
        Assert.assertTrue(shouldBeNull.isEmpty());
    }
}
