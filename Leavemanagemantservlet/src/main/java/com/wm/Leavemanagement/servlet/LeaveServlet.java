package com.wm.Leavemanagement.servlet;

import com.google.gson.Gson;
import com.wm.Leavemanagement.Employee.Leave;
import com.wm.Leavemanagement.Employee.LeaveEmp;
import com.wm.Leavemanagement.Imp.LeaveService;
import com.wm.Leavemanagement.Imp.LeaveServiceImp;
import com.wm.Leavemanagement.model.LeaveRequest;

import com.wm.Leavemanagement.util.GsonConfig;
import com.wm.Leavemanagement.util.UserDetails;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/leave/Leavamangementservlet")
public class LeaveServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LeaveServlet.class);
    LeaveService leaveService = new LeaveServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int userId = UserDetails.getUserId(req);
        logger.debug("user id" + userId);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Gson gson = GsonConfig.createGson();
        String jsonLeave = null;
        BufferedReader reader = null;
        try {
            reader = req.getReader();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        LeaveRequest leaveRequest = gson.fromJson(reader, LeaveRequest.class);
        leaveRequest.setAppliedBy(userId);
        logger.debug("Leave request: {}", leaveRequest);
        try {
            logger.debug("Adding leave_management to table with details: {}", leaveRequest);
            LeaveEmp leaveEmp = leaveService.create(leaveRequest);
            logger.debug("Output:" + leaveEmp);
            jsonLeave = gson.toJson(leaveEmp);
            resp.setContentType("application/json");
            out.println(jsonLeave);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            out.println(String.format("{\"error\": \"%s\"}", e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        int approved = Integer.parseInt(req.getParameter("approve"));
        logger.debug("Approved value: {}", approved);
        if (approved == 1) {
            try {
                logger.debug("making leave approved with details Id: {}", leaveId);
                leaveService.approveLeave(leaveId);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.println("Approved successfully");
            } catch (Exception e) {
                logger.error(e.toString());
            }
        } else {
            try {

                logger.debug("Making leave reject with details Id: {}", leaveId);
                leaveService.rejectLeave(leaveId);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.println("Rejected successfully");
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int userId = UserDetails.getUserId(req);
        Gson gson = GsonConfig.createGson();
        String jsonLeave = null;

        try {
            logger.info("Getting leave_management details with id: {}", userId);
            List<Leave> leaveList = leaveService.getMyLeavesByUserId(userId);
            jsonLeave = gson.toJson(leaveList);
            logger.debug(jsonLeave);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println(jsonLeave);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }
}