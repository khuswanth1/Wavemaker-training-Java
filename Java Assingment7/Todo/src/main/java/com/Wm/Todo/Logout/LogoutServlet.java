package com.Wm.Todo.Logout;

import com.Wm.Todo.util.CookieHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String cookieName = "my_auth_cookie";

        try {
            if (session != null) {
                session.invalidate();
                Cookie cookie = new Cookie(cookieName, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                logger.info("User session and cookie invalidated successfully");
                response.sendRedirect("Login.html");
            }
        } catch (Exception e) {
            logger.error("Error during logout", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}

