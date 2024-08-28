package com.Wm.Todo.login;

import com.google.gson.Gson;
import com.Wm.Todo.Exception.ErrorResponse;
import com.Wm.Todo.pack.usernameandpassword;
import com.Wm.Todo.Service.UserCookieService;
import com.Wm.Todo.Service.UserService;
import com.Wm.Todo.Service.Imp.UserCookieServiceImp;
import com.Wm.Todo.Service.Imp.UserServiceImp;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private static UserService userService = null;
    private static Gson gson = null;
    private static UserCookieService userCookieService = null;

    @Override
    public void init(ServletConfig config) {
        try {
            gson = new Gson();
            userService = new UserServiceImp();
            userCookieService = new UserCookieServiceImp();
        } catch (SQLException e) {
            logger.error("Exception : ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Login Request Received");
        ErrorResponse errorResponse = null;
        String jsonResponse = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        usernameandpassword usernamePassword = new usernameandpassword();
        usernamePassword.setUsername(username);
        usernamePassword.setPassword(password);
        try {
            logger.info("UserName : {}", usernamePassword.getUsername());
            int userId = userService.authenticateUser(usernamePassword);
            if (userId != -1) {
                String cookieValue = UUID.randomUUID().toString();
                String cookieName = "my_auth_cookie";
                Cookie cookie = new Cookie(cookieName, cookieValue);

                userCookieService.addCookie(cookieValue, userId);

                logger.info("Random cookie generated for new user : {}", cookieValue);
                HttpSession userSession = request.getSession(true);
                userSession.setAttribute(cookieValue, userId);
                logger.info("User session set for new user");
                logger.info("User cookie added successfully: username: {} and cookie: {}", usernamePassword.getUsername(), cookieValue);
                response.addCookie(cookie);

                logger.info("User cookie added successfully to browser: {}", cookie);
                response.sendRedirect("index.html");
            } else {
                errorResponse = new ErrorResponse("Invalid Username and Password", 401);
                jsonResponse = gson.toJson(errorResponse);
                logger.error("Invalid user found: Username: {} and Password: {}", usernamePassword.getUsername(), usernamePassword.getPassword());
            }
        } catch (Exception e) {
            errorResponse = new ErrorResponse("Error while logging in: " + e.getMessage(), 500);
            jsonResponse = gson.toJson(errorResponse);
            logger.error("Error occurred while trying to log in", e);
        } finally {
            sendResponse(response, jsonResponse);
        }
    }

    private void sendResponse(HttpServletResponse response, String jsonResponse) {
        PrintWriter printWriter = null;
        try {
            logger.info("Preparing response to send back to client");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
            printWriter.print(jsonResponse);
            printWriter.flush();
            logger.info("Response successfully sent back to client");
        } catch (IOException e) {
            logger.error("Error writing response back to client", e);
            ErrorResponse errorResponse = new ErrorResponse("Internal server error", 500);
            jsonResponse = gson.toJson(errorResponse);
            response.setStatus(500);
            printWriter.print(jsonResponse);
            printWriter.flush();
        } finally {
            closePrintWriter(printWriter);
        }
    }

    private void closePrintWriter(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
