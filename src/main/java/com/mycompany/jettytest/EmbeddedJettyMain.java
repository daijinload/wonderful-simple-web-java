/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jettytest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * メインクラス（普通にこの）
 * 
 * テスト動作時は、下記curlを叩く
 * 
 * curl -v http://localhost:8080/
 * curl -v http://localhost:8080/it/aaa
 * curl -v http://localhost:8080/fr/aaa
 * curl -v -X POST http://localhost:8080/
 */
public class EmbeddedJettyMain {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
//        ServletContextHandler handler = new ServletContextHandler(server, "/example");
//        handler.addServlet(ExampleServlet.class, "/");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");

        server.start();
        server.join();
    }

}
