package com.wm.Leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.Leavemanagement.Employee.Leave;
import com.wm.Leavemanagement.Imp.EmployeeServiceImp;
import com.wm.Leavemanagement.Imp.LeaveService;
import com.wm.Leavemanagement.Imp.LeaveServiceImp;
import com.wm.Leavemanagement.Service.EmployeeService;

import com.wm.Leavemanagement.util.GsonConfig;
import com.wm.Leavemanagement.util.UserDetails;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

@WebServlet("/leave/employeeLeaves")
public class TeamLeaveServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TeamLeaveServlet.class);
    EmployeeService employeeService = new EmployeeServiceImp();
    LeaveService leaveService = new LeaveServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int userId = UserDetails.getUserId(req);
        Gson gson = GsonConfig.createGson();
        String jsonEmployee = null;

        try {
            logger.debug("Getting employees details with id: {}", userId);
            List<Leave> employeeLeaveList = leaveService.getEmployeeLeavesByManagerId(userId);
            jsonEmployee = gson.toJson(employeeLeaveList);
            logger.debug(jsonEmployee);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println(jsonEmployee);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }

}