package demo;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.webapp.*;
import org.eclipse.jetty.server.Server;


public class Main {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("src/test/webapp");

        webapp.setConfigurations(new Configuration[]{
                new WebXmlConfiguration(),
                new AnnotationConfiguration(),
                new WebInfConfiguration()
        });

        server.setHandler(webapp);

        server.start();
    }
}