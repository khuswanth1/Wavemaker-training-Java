package com.wm.leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.pojo.Login;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.service.impl.EmployeeServiceImp;
import com.wm.leavemanagement.service.LoginService;
import com.wm.leavemanagement.service.impl.LoginServiceImp;
import com.wm.leavemanagement.service.EmployeeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    LoginService loginService = new LoginServiceImp();
    EmployeeService employeeService = new EmployeeServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println("in controller : " + stringBuilder.toString());
        Login reqLogin = gson.fromJson(stringBuilder.toString(), Login.class);
        PrintWriter printWriter = resp.getWriter();
        System.out.println(reqLogin);
        if (reqLogin == null) {
            logger.error("Invalid login request data");
            logger.error("{\"message\": \"Invalid login request data\"}");
            return;
        }
        Login login = null;
        try {
            System.out.println(reqLogin.getUserName());
            login = loginService.getByUserName(reqLogin.getUserName());
            System.out.println(login);
            logger.debug("Login.." + login);
        } catch (loginNotFoundException | SQLException e) {
            logger.error(e.getMessage());
            System.out.println(e);
            //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String errorJson = String.format("{\"error\": \"%s\"}", "Invalid Username");
            printWriter.println(errorJson);
            return;
        }
        try {
            if (login.getPassword().equals(reqLogin.getPassword())) {
                UUID uuid = UUID.randomUUID();
                String cookieValue = uuid.toString();
                logger.debug("Password matched..");
                HttpSession httpSession = req.getSession(true);
                Cookie cookie = new Cookie("loginCookie", cookieValue);
                httpSession.setAttribute("loginCookie", cookieValue);
                cookie.setMaxAge(60 * 60);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                Employee employee = employeeService.getByUserName(reqLogin.getUserName());
                String employeeId = String.valueOf(employee.getId());
                logger.debug("Adding user cookie");
                Cookie userCookie = new Cookie("userCookie", employeeId);
                httpSession.setAttribute("userCookie", employeeId);
                userCookie.setMaxAge(10 * 60);
                userCookie.setHttpOnly(true);
                userCookie.setPath("/");
                resp.addCookie(cookie);
                resp.addCookie(userCookie);
                printWriter.println("Logged In successfully");
            } else {
                //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                printWriter.println(String.format("{\"error\": \"%s\"}", "Password is Incorrect"));
            }
        } catch (Exception e) {
            logger.error("Error during login: " + e.getMessage());
            //resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            printWriter.println(String.format("{\"error\": \"%s\"}", e.getMessage()));
        }


    }
}
