package com.gmail.daijinload;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
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

//        webapp.setConfigurations(new Configuration[]{
//                new WebXmlConfiguration(),
//                new AnnotationConfiguration(),
//                new WebInfConfiguration()
//        });

        webapp.setConfigurations(new Configuration[]{
            new AnnotationConfiguration(),
            new WebInfConfiguration(),
            new WebXmlConfiguration(),
            new MetaInfConfiguration(),
            new FragmentConfiguration(),
            new EnvConfiguration(),
            new PlusConfiguration(),
            new JettyWebXmlConfiguration()
        });

        server.setHandler(webapp);

        server.start();
        //server.join();

        Assertions.assertEquals(this.get("http://localhost:8080/hello"), "Hello Servlet!!");
        Assertions.assertEquals(this.get("http://localhost:8080/text-file.txt"), "Hello file!!");
        Assertions.assertEquals(this.get("http://localhost:8080/hello.jsp"), "Hello JSP!!");
        Assertions.assertEquals(this.get("http://localhost:8080/el.jsp"), "8");

        server.stop();
    }

    private String get(String url) throws IOException {
        String result;
        HttpURLConnection connEl = (HttpURLConnection) URI.create(url).toURL().openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connEl.getInputStream(), StandardCharsets.UTF_8))) {
            result = reader.readLine();
        }
        connEl.disconnect();
        return result;
    }
}



