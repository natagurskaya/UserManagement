package com.gurskaya.userManagement.servlets;

import com.gurskaya.userManagement.entity.User;
import com.gurskaya.userManagement.service.userServiceImpl.UserService;
import com.gurskaya.userManagement.templater.PageGenerator;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById(Integer.parseInt(req.getParameter("id")));

        Map<String, Object> pageVariables = new HashMap<>();

        pageVariables.put("user", user);
        pageVariables.put("id", req.getParameter("id"));

        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("updateUser.html", pageVariables);

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(page);
    }
}
