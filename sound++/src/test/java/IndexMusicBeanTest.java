/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.Controller.IndexMusicBean;
import com.mycompany.Persistence.DAO;
import java.io.File;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author maian
 */
@RunWith(Arquillian.class)
public class IndexMusicBeanTest {
    @Deployment
public static WebArchive createDeployment() {
final File[] dependencies = Maven.resolver()
.loadPomFromFile("pom.xml")
.importRuntimeDependencies()
 .resolve()
.withoutTransitivity()
.asFile();
final WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
.setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
.addPackage(IndexMusicBean.class.getPackage())
.addPackage(DAO.class.getPackage())
.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
.addAsWebInfResource(
new File("src/main/setup/glassfish-resources.xml"),
"glassfish-resources.xml")
.addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
"META-INF/persistence.xml")
.addAsLibraries(dependencies);
        return webArchive;
}
   @Inject
    IndexMusicBean foo;
@Test
public void test1() {
}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
