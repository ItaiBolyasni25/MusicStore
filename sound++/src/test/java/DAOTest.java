/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Model.Album;
import com.mycompany.Interface.EntityModel;
import com.mycompany.Persistence.DAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author maian
 */
@RunWith(Arquillian.class)
public class DAOTest {

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
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml")
                ;//.addAsLibraries(dependencies);
        return webArchive;
    }
    
    @Inject
    DAO dao;

    @Test
    public void testReadMethod() {
        System.out.println("hey");
        List<Album> albums = dao.read(new Album(), 1);
        Assert.assertEquals("Assume Form", albums.get(0).getTitle());
    }

}
