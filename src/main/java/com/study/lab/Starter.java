package com.study.lab;

import com.study.lab.dao.UserDao;
import com.study.lab.dao.jdbc.UserDaoJdbc;
import com.study.lab.servlets.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        UserDao userDao = new UserDaoJdbc();
        ServiceLocator.register(UserDao.class, userDao);

        UserServlet userServlet = new UserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(userServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
