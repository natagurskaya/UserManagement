package com.study.lab;

import com.study.lab.dao.UserDao;
import com.study.lab.dao.jdbc.UserDaoJdbc;
import com.study.lab.service.UserService;
import com.study.lab.servlets.AddUserServlet;
import com.study.lab.servlets.UserServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {

        UserDao userDao = new UserDaoJdbc();
        UserService userService = new UserService(userDao);
        UserServlet userServlet = new UserServlet(userService);
        AddUserServlet addUserServlet= new AddUserServlet(userService);

        HandlerList handlerList = new HandlerList();
        ResourceHandler resourceHandler = new ResourceHandler();

        resourceHandler.setResourceBase("templates/lab");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(userServlet), "/user");
        context.addServlet(new ServletHolder(addUserServlet), "/addUser");
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
    }
}
