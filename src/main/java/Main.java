
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class Main {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
//        webapp.setWar("src/main/webapp");
        webapp.setResourceBase("src/main/webapp");

        webapp.setConfigurations(new Configuration[]{
                new WebXmlConfiguration(),
                new AnnotationConfiguration(),
                new WebInfConfiguration()
        });

        server.setHandler(webapp);

        server.start();
    }
}