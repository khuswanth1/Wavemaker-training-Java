package com.wm.Leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.Leavemanagement.Employee.Employee;
import com.wm.Leavemanagement.Employee.login;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.Imp.EmployeeServiceImp;
import com.wm.Leavemanagement.Imp.LoginService;
import com.wm.Leavemanagement.Imp.LoginServiceImp;
import com.wm.Leavemanagement.Service.EmployeeService;
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
        login reqLogin = gson.fromJson(reader, login.class);
        PrintWriter printWriter = resp.getWriter();
        if (reqLogin == null) {
            logger.error("Invalid login request data");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid login data");
            return;
        }
        login login = null;
        try {
            login = loginService.getByUserName(reqLogin.getUserName());
            logger.debug("Login.." + login);
        } catch (LoginNotFoundException | SQLException e) {
            logger.error(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                printWriter.println(String.format("{\"error\": \"%s\"}", "Password is Incorrect"));
            }
        } catch (Exception e) {
            logger.error("Error during login: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            printWriter.println(String.format("{\"error\": \"%s\"}", e.getMessage()));
        }
    }
}
