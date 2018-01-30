package com.study.lab.servlets;

import com.study.lab.entity.User;
import com.study.lab.service.UserService;
import com.study.lab.templater.PageGenerator;
import org.eclipse.jetty.server.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet {
    private UserService userService;

    public AddUserServlet(UserService userService) {
        this.userService = userService;
    }

    private String page = "addUser.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();


        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage(this.page, pageVariables);

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();

        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPayment(Double.parseDouble(req.getParameter("payment")));


        userService.add(user);


        resp.sendRedirect("/addUser");
    }

}
