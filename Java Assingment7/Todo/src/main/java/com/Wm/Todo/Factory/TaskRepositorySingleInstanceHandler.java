package com.Wm.Todo.Factory;



import com.Wm.Todo.Repository.Imp.TaskRepositoryImp;
import com.Wm.Todo.Repository.TaskRepository;

import java.sql.SQLException;

public class TaskRepositorySingleInstanceHandler {
    private TaskRepositorySingleInstanceHandler() {
    }

    private static volatile TaskRepository inDbRepository = null;

    public static TaskRepository getInDatabaseTaskRepositoryInstance() throws SQLException {
        if (inDbRepository == null) {
            synchronized (TaskRepositorySingleInstanceHandler.class) {
                if (inDbRepository == null) {
                    inDbRepository = new TaskRepositoryImp();
                }
            }
        }
        return inDbRepository;
    }

    public static TaskRepository getInDBTaskRepositoryInstance() {
        return null;
    }
}
