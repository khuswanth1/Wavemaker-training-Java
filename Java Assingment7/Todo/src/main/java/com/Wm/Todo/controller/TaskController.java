package com.Wm.Todo.controller;

import com.google.gson.Gson;
import com.Wm.Todo.config.GsonConfig;
import com.Wm.Todo.Exception.ErrorResponse;
import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.Task;
import com.Wm.Todo.Service.TaskService;
import com.Wm.Todo.Service.UserCookieService;
import com.Wm.Todo.Service.Imp.TaskServiceImp;
import com.Wm.Todo.Service.Imp.UserCookieServiceImp;
import com.Wm.Todo.util.CookieHandler;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private static TaskService taskService = null;
    private static UserCookieService userCookieService = null;
    private static Gson gson = null;

    public TaskController() {
    }

    @Override
    public void init(ServletConfig config) {
        try {
            taskService = new TaskServiceImp();
            userCookieService = new UserCookieServiceImp();
            gson = GsonConfig.createGson();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doGet(req, resp);
//
//        resp.getWriter().print("hello");
//    }

        @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String jsonResponse = null;
        List<Task> taskList = null;
        Task task = null;
        String taskId = httpServletRequest.getParameter("taskId");
        String priorityFilter = httpServletRequest.getParameter("priority");
        String sortDueDateTime = httpServletRequest.getParameter("sortDueDateTime");
        String sortPriority = httpServletRequest.getParameter("sortPriority");
        String cookieValue = CookieHandler.getCookieValueByCookieName("my_auth_cookie", httpServletRequest);
        if (cookieValue != null) {
            try {
                int userId = userCookieService.getUserIdByCookieValue(cookieValue);
                if (userId != -1) {
                    if (taskId != null) {
                        task = taskService.getTaskById(Integer.parseInt(taskId));
                        jsonResponse = gson.toJson(task);
                    } else if (priorityFilter != null && !priorityFilter.equals("all")) {
                        taskList = taskService.getAllTasksByPriority(userId, priorityFilter);
                        jsonResponse = gson.toJson(taskList);
                    } else if (sortPriority != null && !sortPriority.equals("none")) {
                        taskList = taskService.getAllTasksByPriorityOrder(userId, sortPriority);
                        jsonResponse = gson.toJson(taskList);
                    } else if (sortDueDateTime != null && !sortDueDateTime.equals("none")) {
                        taskList = taskService.getAllTasksByDueDateTime(userId, sortDueDateTime);
                        jsonResponse = gson.toJson(taskList);
                    } else {
                        taskList = taskService.getAllTasks(userId);
                        jsonResponse = gson.toJson(taskList);
                      httpServletResponse.getWriter().print(jsonResponse);
                    }
                } else {
                    ErrorResponse errorResponse = new ErrorResponse("Invalid User Found, Access Denied", HttpServletResponse.SC_BAD_REQUEST);
                    jsonResponse = gson.toJson(errorResponse);
                    httpServletResponse.setStatus(400);
                    sendResponse(httpServletResponse, jsonResponse);
                }
            } catch (ServerUnavilableException e) {
                logger.error("Error fetching Task details ", e);
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
                jsonResponse = gson.toJson(errorResponse);
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse("Server Error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setStatus(500);
                jsonResponse = gson.toJson(errorResponse);
            } finally {
                sendResponse(httpServletResponse, jsonResponse);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String jsonResponse = null;
        Task task = null;
        int userId = -1;
        Task addedTask = null;
        BufferedReader bufferedReader = null;
        String cookieValue = CookieHandler.getCookieValueByCookieName("my_auth_cookie", httpServletRequest);
        if (cookieValue != null) {
            try {
                userId = userCookieService.getUserIdByCookieValue(cookieValue);
                if (userId != -1) {
                    bufferedReader = httpServletRequest.getReader();
                    task = gson.fromJson(bufferedReader, Task.class);
                    task.setUserId(userId);
                    addedTask = taskService.addTask(task);
                    jsonResponse = gson.toJson(addedTask);
                } else {
                    ErrorResponse errorResponse = new ErrorResponse("Invalid User Found, Access Denied", HttpServletResponse.SC_BAD_REQUEST);
                    jsonResponse = gson.toJson(errorResponse);
                    httpServletResponse.setStatus(400);
                    sendResponse(httpServletResponse, jsonResponse);
                }
            } catch (ServerUnavilableException | IOException e) {
                logger.error("Error fetching Task details ", e);
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
                jsonResponse = gson.toJson(errorResponse);
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse("Server Error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setStatus(500);
                jsonResponse = gson.toJson(errorResponse);
            } finally {
                sendResponse(httpServletResponse, jsonResponse);
            }
        }
    }


    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String jsonResponse = null;
        Task updatedTask = null;
        Task task = null;
        int taskId = Integer.parseInt(httpServletRequest.getParameter("taskId"));
        BufferedReader bufferedReader = null;
        String cookieValue = CookieHandler.getCookieValueByCookieName("my_auth_cookie", httpServletRequest);
        if (cookieValue != null) {
            try {
                int userId = userCookieService.getUserIdByCookieValue(cookieValue);
                if (userId != -1) {
                    bufferedReader = httpServletRequest.getReader();
                    task = gson.fromJson(bufferedReader, Task.class);
                    task.setTaskId(taskId);
                    updatedTask = taskService.updateTask(task);
                    task = null;
                    jsonResponse = gson.toJson(updatedTask);
                } else {
                    ErrorResponse errorResponse = new ErrorResponse("Invalid User Found, Access Denied", HttpServletResponse.SC_BAD_REQUEST);
                    jsonResponse = gson.toJson(errorResponse);
                    httpServletResponse.setStatus(400);
                    sendResponse(httpServletResponse, jsonResponse);
                }
            } catch (ServerUnavilableException | IOException e) {
                logger.error("Error fetching Task details ", e);
                ErrorResponse errorResponse = new ErrorResponse("Unable to Update Task", HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse = gson.toJson(errorResponse);
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse("Server Error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setStatus(500);
                jsonResponse = gson.toJson(errorResponse);
            } finally {
                sendResponse(httpServletResponse, jsonResponse);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String jsonResponse = null;
        Task task = null;
        int taskId = Integer.parseInt(httpServletRequest.getParameter("taskId"));
        String cookieValue = CookieHandler.getCookieValueByCookieName("my_auth_cookie", httpServletRequest);
        if (cookieValue != null) {
            try {
                int userId = userCookieService.getUserIdByCookieValue(cookieValue);
                if (userId != -1) {
                    task = taskService.deleteTaskById(taskId);
                    jsonResponse = gson.toJson(task);
                } else {
                    ErrorResponse errorResponse = new ErrorResponse("Invalid User Found, Access Denied", HttpServletResponse.SC_BAD_REQUEST);
                    jsonResponse = gson.toJson(errorResponse);
                    httpServletResponse.setStatus(400);
                    sendResponse(httpServletResponse, jsonResponse);
                }
            } catch (ServerUnavilableException e) {
                logger.error("Error fetching Task details ", e);
                ErrorResponse errorResponse = new ErrorResponse("Unable to Delete Task", HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse = gson.toJson(errorResponse);
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse("Server Error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setStatus(500);
                jsonResponse = gson.toJson(errorResponse);
            } finally {
                sendResponse(httpServletResponse, jsonResponse);
            }
        }
    }

    private void sendResponse(HttpServletResponse httpServletResponse, String jsonResponse) {
        PrintWriter printWriter = null;
        try {
            logger.info("Preparing response to send back to client");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            printWriter = httpServletResponse.getWriter();
            printWriter.print(jsonResponse);
            printWriter.flush();
            logger.info("Response successfully sent back to client");
        } catch (IOException e) {
            logger.error("Error writing response back to client", e);
            ErrorResponse errorResponse = new ErrorResponse("Internal server error", 500);
            jsonResponse = gson.toJson(errorResponse);
            httpServletResponse.setStatus(500);
            printWriter.print(jsonResponse);
            printWriter.flush();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Server Error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setStatus(500);
            jsonResponse = gson.toJson(errorResponse);
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