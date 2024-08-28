package com.db.util;

import java.io.File;
import java.io.IOException;

import com.db.Exception.FileCreationException;

public class FileCreation {
    public static File createFileIfNotExists(String FILE_PATH) throws FileCreationException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new FileCreationException("Failed to create a new file at path: " + FILE_PATH);
                }
            } catch (IOException exception) {
                throw new FileCreationException("An error occurred while creating the file at path: " + FILE_PATH);
            }
        }
        return file;
    }
}