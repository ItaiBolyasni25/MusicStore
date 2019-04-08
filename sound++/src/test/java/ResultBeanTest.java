/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Controller.AlbumTrackBean;
import com.mycompany.Controller.ResultBean;
import com.mycompany.Controller.SearchBean;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Album;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
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

/**
 *
 * @author maian
 */
public class ResultBeanTest {

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
                .addPackage(ResultBean.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml");//.addAsLibraries(dependencies);
        return webArchive;
    }

    @Inject
    private DAO dao;
    @Inject
    private ResultBean search;
    private String pattern;
    private String filter;

}
