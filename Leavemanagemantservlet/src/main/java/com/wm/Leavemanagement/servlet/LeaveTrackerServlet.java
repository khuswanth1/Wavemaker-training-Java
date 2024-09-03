package com.wm.Leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.Leavemanagement.Imp.LeaveService;
import com.wm.Leavemanagement.Imp.LeaveServiceImp;
import com.wm.Leavemanagement.util.UserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.wm.Leavemanagement.Employee.LeaveTracker;

import com.wm.Leavemanagement.util.GsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/leave/leaveTracker")
public class LeaveTrackerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LeaveTrackerServlet.class);
    LeaveService leaveService = new LeaveServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String appliedBy = req.getParameter("appliedBy");
        Gson gson = GsonConfig.createGson();
        String jsonEmployee = null;
        if(appliedBy == null) {
            int userId = UserDetails.getUserId(req);
            try {
                logger.info("Getting employee leave tracker of logged in user with id: {}", userId);
                List<LeaveTracker> leaveTrackerList = leaveService.getLeavesTrackerByUserId(userId);
                jsonEmployee = gson.toJson(leaveTrackerList);
                logger.debug(jsonEmployee);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.println(jsonEmployee);
            } catch (Exception e) {
                logger.debug(e.toString());
            }
        } else {
            int employeeId  = Integer.parseInt(appliedBy);

            try {
                logger.info("Getting employee leave tracker details with id: {}", employeeId);
                List<LeaveTracker> leaveTrackerList = leaveService.getLeavesTrackerByUserId(employeeId);
                jsonEmployee = gson.toJson(leaveTrackerList);

                logger.debug(jsonEmployee);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.println(jsonEmployee);
            } catch (Exception e) {
                logger.debug(e.toString());
            }
        }
    }
}