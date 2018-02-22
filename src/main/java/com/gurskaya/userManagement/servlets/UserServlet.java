package com.gurskaya.userManagement.servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gurskaya.userManagement.entity.User;
import com.gurskaya.userManagement.service.userServiceImpl.UserService;
import com.gurskaya.userManagement.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.*;

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

        String jsonString = getJsonString(req);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

        int id = jsonObject.get("id").getAsInt();
        user.setId(id);

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

        String jsonString = getJsonString(req);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

        int id = jsonObject.get("id").getAsInt();
        String firstName = jsonObject.get("firstName").getAsString();
        String lastName = jsonObject.get("lastName").getAsString();
        double payment = jsonObject.get("payment").getAsDouble();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPayment(payment);

        userService.update(user);

        resp.sendRedirect("/user");
    }

    private String getJsonString (HttpServletRequest req){

        StringBuilder result = new StringBuilder();

        if (req.getContentLength() > 0) {
            String line;
            try {
                BufferedReader reader = new BufferedReader(req.getReader());
                while ((line = reader.readLine()) != null)
                    result.append(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}

