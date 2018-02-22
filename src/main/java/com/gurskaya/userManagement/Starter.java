package com.gurskaya.userManagement;

import com.gurskaya.userManagement.dao.UserDao;
import com.gurskaya.userManagement.dao.jdbc.JdbcUserDao;
import com.gurskaya.userManagement.service.userServiceImpl.UserService;
import com.gurskaya.userManagement.servlets.AddUserPageServlet;
import com.gurskaya.userManagement.servlets.UpdateUserPageServlet;
import com.gurskaya.userManagement.servlets.UserServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {

        UserDao userDao = new JdbcUserDao();
        UserService userService = new UserService(userDao);
        UserServlet userServlet = new UserServlet(userService);
        AddUserPageServlet addUserPageServlet= new AddUserPageServlet();
        UpdateUserPageServlet updateUserPageServlet = new UpdateUserPageServlet(userService);

        HandlerList handlerList = new HandlerList();
        ResourceHandler resourceHandler = new ResourceHandler();

        resourceHandler.setResourceBase("templates/lab");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(userServlet), "/user/*");
        context.addServlet(new ServletHolder(addUserPageServlet), "/addUser");
        context.addServlet(new ServletHolder(updateUserPageServlet), "/updateUser/*");
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
    }
}
