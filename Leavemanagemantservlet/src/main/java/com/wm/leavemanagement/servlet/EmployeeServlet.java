package com.wm.leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.service.impl.EmployeeServiceImp;
import com.wm.leavemanagement.service.EmployeeService;
import com.wm.leavemanagement.util.UserDetails;
import com.wm.leavemanagement.util.GsonConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/leave/Emp")
public class EmployeeServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServlet.class);
    EmployeeService employeeService = new EmployeeServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int userId = UserDetails.getUserId(req);
        Gson gson = GsonConfig.createGson();
        String jsonEmployee = null;
        String profileValue = req.getParameter("profile");

        if (profileValue == null) {
            int employeeId = Integer.parseInt(req.getParameter("employeeId"));
            try {
                logger.info("Getting employees details with id: {}", userId);
                Employee employee = employeeService.getById(employeeId);
                jsonEmployee = gson.toJson(employee);
                logger.debug(jsonEmployee);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.println(jsonEmployee);
            } catch (Exception e) {
                logger.debug(e.toString());
            }
        } else {
            try {
                logger.debug("Getting employee by id: {}", userId);
                Employee employee = employeeService.getById(userId);
                jsonEmployee = gson.toJson(employee);
                PrintWriter out = resp.getWriter();
                out.println(jsonEmployee);
                resp.setContentType("application/json");
            } catch (loginNotFoundException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

}