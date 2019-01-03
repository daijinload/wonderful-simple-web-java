package app.sample;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class AppTest {
    @Test
    public void test() throws Exception {
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

        HttpURLConnection conn = (HttpURLConnection) URI.create("http://localhost:8080/hello").toURL().openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            Assertions.assertEquals(reader.readLine(), "Hello Servlet!!");
        }
        conn.disconnect();

        HttpURLConnection connPlainText = (HttpURLConnection) URI.create("http://localhost:8080/text-file.txt").toURL().openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connPlainText.getInputStream(), StandardCharsets.UTF_8))) {
            Assertions.assertEquals(reader.readLine(), "Hello Jetty!!");
        }
        connPlainText.disconnect();

        server.stop();
    }
}
