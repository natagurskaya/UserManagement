package com.study.lab.servlets;

import com.study.lab.entity.User;
import com.study.lab.service.UserService;
import com.study.lab.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String htmlPage = "page.html";

        List<User> users = userService.getAll();
        pageVariables.put("users", users);

        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage(htmlPage, pageVariables);

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        String id = req.getPathInfo().substring(1);
        user.setId(Integer.parseInt(id));
        userService.delete(user);

        resp.sendRedirect("/user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();

        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPayment(Double.parseDouble(req.getParameter("payment")));

        userService.add(user);

        resp.sendRedirect("/user");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        String id = req.getPathInfo().substring(1);
        user.setId(Integer.parseInt(id));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPayment(Double.parseDouble(req.getParameter("payment")));

        userService.update(user);

        resp.sendRedirect("/user");
    }
}

