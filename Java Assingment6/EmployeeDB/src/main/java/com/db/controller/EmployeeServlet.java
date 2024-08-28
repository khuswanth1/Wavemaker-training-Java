package com.db.controller;



import com.google.gson.Gson;
import com.db.com.pack.Address;
import com.db.com.pack.Employee;
import com.db.Service.AddressService;
import com.db.Service.Emp_service;
import com.db.Service.Emp.AddressServiceEmp;
import com.db.Service.Emp.EmployeeServiceEmp;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private static Emp_service employeeService;
    private static AddressService addressService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServlet.class);

    @Override
    public void init() {
        int storageOption = 3; // here storageOption 3 is InDbstorage
        employeeService = new EmployeeServiceEmp(storageOption);
        addressService = new AddressServiceEmp(storageOption) {

            @Override
            public Address getAddressById(int id) {
                return null;
            }
            @Override
            public boolean addAddressToEmp(int empId, String address) {
                return false;
            }
        };
        logger.info("Created EmployeeService and AddressService instances.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String actionPerformed = req.getParameter("action");

        if ("getEmployeeById".equals(actionPerformed)) {
            getEmployeeById(req, resp);
        } else if ("getAllEmployees".equals(actionPerformed)) {
            getAllEmployees(resp);
        } else if ("getEmployeeByEmail".equals(actionPerformed)) {
            getEmployeeByEmail(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Invalid action parameter.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String empIdStr = req.getParameter("empId");
        if (empIdStr == null || empIdStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Missing or invalid empId.");
            return;
        }

        try {
            int empId = Integer.parseInt(empIdStr);

            Employee employee = employeeService.getEmployeeById(empId);
            if (employee == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writeResponse(resp, "Employee not found.");
                return;
            }

            boolean deleted = employeeService.deleteEmployeeByEmpId(empId);
            if (deleted) {
                resp.setStatus(HttpServletResponse.SC_OK);
                writeResponse(resp, "Employee deleted successfully.");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writeResponse(resp, "Failed to delete employee.");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Invalid empId format.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeResponse(resp, "An error occurred while deleting the employee.");
            logger.error("Error deleting employee with empId " + empIdStr, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        Gson gson = new Gson();
        Employee employee = gson.fromJson(req.getReader(), Employee.class);

        if (employee != null) {
            int empId = employeeService.addEmployee(employee);
            if (empId != -1) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writeResponse(resp, gson.toJson("Employee added successfully with ID: " + empId));
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writeResponse(resp, gson.toJson("Failed to add employee."));
            }
        } else {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, gson.toJson("Invalid employee data."));
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("empId");
        if (idStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "{\"error\":\"Missing employee ID.\"}");
            return;
        }

        int empId;
        try {
            empId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "{\"error\":\"Invalid employee ID format.\"}");
            return;
        }

        Employee existingEmployee = employeeService.getEmployeeById(empId);
        if (existingEmployee == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writeResponse(resp, "{\"error\":\"Employee not found.\"}");
            return;
        }


        Gson gson = new Gson();
        Employee updatedEmployee = gson.fromJson(req.getReader(), Employee.class);

        if (updatedEmployee == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "{\"error\":\"Invalid employee data.\"}");
            return;
        }

        updatedEmployee.setEmpId(empId);

        if (updatedEmployee.getAddress() != null) {
            String updatedAddress = updatedEmployee.getAddress();

            Address existingAddress = addressService.getAddressById(empId);
            if (existingAddress != null) {
                try {

                    addressService.updateAddress(updatedAddress);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                addressService.addAddressToEmp(empId, updatedAddress);
            }
        }



        // Update the employee
        boolean updated = employeeService.updateEmployee(updatedEmployee);
        if (updated) {
            resp.setStatus(HttpServletResponse.SC_OK);
            writeResponse(resp, gson.toJson("Employee updated successfully."));
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeResponse(resp, gson.toJson("Failed to update employee."));
        }
    }

    private void getEmployeeById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            writeResponse(resp, "Missing employee ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Employee employee = employeeService.getEmployeeById(id);

            if (employee != null) {
                Gson gson = new Gson();
                String jsonEmployee = gson.toJson(employee);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter printWriter = resp.getWriter();
                printWriter.print(jsonEmployee);
                printWriter.flush();
            } else {
                writeResponse(resp, "Employee not found.");
            }
        } catch (NumberFormatException e) {
            writeResponse(resp, "Invalid employee ID format.");
        }
    }

    private void getAllEmployees(HttpServletResponse resp) throws IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            writeResponse(resp, "No employees found.");
        } else {
            for (Employee emp : employees) {
                Gson gson = new Gson();
                String jsonEmployee = gson.toJson(emp);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter printWriter = resp.getWriter();
                printWriter.print(jsonEmployee);
                printWriter.flush();
            }
        }
    }


    private void getEmployeeByEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        if (email == null) {
            writeResponse(resp, "Missing email parameter.");
            return;
        }

        Employee employee = employeeService.getEmployeeByEmail(email);
        if (employee != null) {
            Gson gson = new Gson();
            String jsonEmployee = gson.toJson(employee);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.print(jsonEmployee);
            printWriter.flush();
        } else {
            writeResponse(resp, "Employee not found for email: " + email);
        }
    }


    private void writeResponse(HttpServletResponse resp, String message) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        out.println(message);
    }
}