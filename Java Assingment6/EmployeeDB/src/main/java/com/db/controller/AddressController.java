package com.db.controller;



import com.google.gson.Gson;
import com.db.com.pack.Address;
import com.db.com.pack.Employee;
import com.db.Service.AddressService;
import com.db.Service.Emp_service;
import com.db.Service.Emp.AddressServiceEmp;
import com.db.Service.Emp.EmployeeServiceEmp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/address")
public class AddressController extends HttpServlet {
    private static Emp_service  employeeService;
    private static AddressService addressService;
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private static boolean addressHeading;


    public void init() throws ServletException {
        int storageOption = 3;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empId = req.getParameter("empId");

        if (empId == null || empId.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Missing or invalid empId.");
            return;
        }

        try {
            int id = Integer.parseInt(empId);
            Address address = addressService.getAddressByEmpId(id);

            if (address == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writeResponse(resp, "address not found for empId.");
            } else {
                Gson gson = new Gson();
                String jsonAddress = gson.toJson(address);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter printWriter = resp.getWriter();
                printWriter.print(jsonAddress);
                printWriter.flush();
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Invalid empId format.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empIdStr = req.getParameter("empId");

        if (empIdStr == null || empIdStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Missing or invalid empId.");
            return;
        }

        try {
            int empId = Integer.parseInt(empIdStr);

            Address address = addressService.getAddressByEmpId(empId);
            if (address == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writeResponse(resp, "address not found.");
                return;
            }

            Address deleted = addressService.deleteAddressByEmpId(empId);
            if (deleted != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                writeResponse(resp, "address deleted successfully.");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writeResponse(resp, "Failed to delete address.");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(resp, "Invalid empId format.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeResponse(resp, "An error occurred while deleting the address.");
            logger.error("Error deleting address with empId " + empIdStr, e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String empIdString = request.getParameter("empId");
        if (empIdString == null || empIdString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(response, "Missing or invalid empId.");
            return;
        }
        int empId;
        try {
            empId = Integer.parseInt(empIdString);
            Employee employee = employeeService.getEmployeeById(empId);
            if (employee != null) {
                if (employee.getAddress() == null) {
                    Address address = getEmployeeAddress(request, response);
                    if (address != null) {
                        address.setEmpId(empId);
                        boolean added = addressService.addAddress(address);
                        if (added) {
                            writeResponse(response, "address added successfully.");

                        } else {
                            writeResponse(response, "Failed to add address.");
                        }

                    }

                } else {
                    writeResponse(response, "Employee address already exist for empId " + empIdString);
                }
            } else {
                writeResponse(response, "Employee not exist for empId " + empIdString);
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeResponse(response, "Invalid empId format.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeResponse(response, "An error occurred while update the address.");
            logger.error("Error update address with empId " + empIdString, e);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String empIdString = request.getParameter("empId");

        if (empIdString == null) {
            writeResponse(response, "Missing employee ID.");
            return;
        }
        int empId;
        try {
            empId = Integer.parseInt(empIdString);
        } catch (NumberFormatException e) {
            writeResponse(response, "Invalid employee ID format.");
            return;
        }

        Address address = addressService.getAddressByEmpId(empId);
        if (address != null) {
            String addressId = String.valueOf(address.getAddress());
            address = getEmployeeAddress(request, response);
            if (address != null) {
                address.setEmpId(empId);// Pass the existing address ID
                address.setAddress(addressId);
                Address updated = null;
                try {
                    updated = addressService.updateAddress(String.valueOf(address));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (updated != null) {
                    writeResponse(response, "address updated successfully.");
                } else {
                    writeResponse(response, "Failed to update address.");
                }


            }

        } else {
            writeResponse(response, "address not found.");
        }

    }


    private Address getEmployeeAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String pincodeStr = req.getParameter("pincode");

        if (state == null || city == null || pincodeStr == null) {
            writeResponse(resp, "Missing address parameters.");
            return null;
        }

        int pincode;
        try {
            pincode = Integer.parseInt(pincodeStr);
        } catch (NumberFormatException e) {
            writeResponse(resp, "Invalid pincode format.");
            return null;
        }

        if (pincode < 100000 || pincode > 999999) {
            writeResponse(resp, "Invalid pincode. It should be a 6-digit number.");
            return null;
        }

        Address address = new Address();
        address.setState(state);
        address.setCity(city);
        address.setPin(pincode);
        return address;
    }


    private static void writeResponse(HttpServletResponse resp, String message) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        out.println(message);
    }


}