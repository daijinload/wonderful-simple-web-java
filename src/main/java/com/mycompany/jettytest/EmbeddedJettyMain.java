/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jettytest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * メインクラス（普通にこの）
 * 
 * テスト動作時は、下記curlを叩く
 * 
 * curl -v http://localhost:7070/example/
 */
public class EmbeddedJettyMain {

    public static void main(String[] args) throws Exception {
        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/example");
        handler.addServlet(ExampleServlet.class, "/");
        server.start();
        
        
    }

}
