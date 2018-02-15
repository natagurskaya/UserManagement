package com.study.lab.servlets;

import com.study.lab.dao.jdbc.UserDaoJdbc;
import com.study.lab.entity.User;
import com.study.lab.service.UserService;
import com.study.lab.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserPageServlet extends HttpServlet {
    private UserService userService;

    public UpdateUserPageServlet(UserService userService) {
        this.userService = userService;
    }

    private String page = "updateUser.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter("id")));

        Map<String, Object> pageVariables = new HashMap<>();

        pageVariables.put("id", req.getParameter("id"));
        pageVariables.put("firstName", user.getFirstName());
        pageVariables.put("lastName", user.getLastName());
        pageVariables.put("payment", user.getPayment());

        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage(this.page, pageVariables);

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }

}
