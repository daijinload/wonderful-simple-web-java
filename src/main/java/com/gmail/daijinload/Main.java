package com.gmail.daijinload;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

public class Main implements AutoCloseable, Runnable {

    private static final ClassLoader LOADER = Main.class.getClassLoader();

    private static final URL MAIN_URL = Main.class.getProtectionDomain()
            .getCodeSource().getLocation();

    private static Server server = null;
    private static WebAppContext context = null;

    public static void main(String[] args) throws Exception {
        // web.xmlはダミーでおいてあるだけで、空っぽのファイル
        Optional<URL> url = Optional.ofNullable(LOADER.getResource("WEB-INF/web.xml"));
        try (Main main = new Main(url)) {
            main.run();
        }
    }

    public Main(Optional<URL> warFile) {
        this.server = new Server(8080);
        String war = warFile.map(u -> MAIN_URL.toExternalForm()).orElse("src/main/webapp");

        Configuration[] configurations = {
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration(),
                new JettyWebXmlConfiguration()
        };

        context = new WebAppContext();
        context.setWar(war);
        context.setContextPath("/");
        if (!warFile.isPresent()) {
            context.getMetaData()
                    .setWebInfClassesDirs(
                            Arrays.asList(Resource.newResource(MAIN_URL)));
        }
        context.setConfigurations(configurations);
    }

    @Override
    public void run() {
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        server.stop();
    }

}

