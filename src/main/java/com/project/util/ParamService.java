package com.project.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {

    @Autowired
    private HttpServletRequest request;

    /**
     * Gets a string parameter from the request, or returns the default value if not present.
     * @param name the name of the parameter
     * @param defaultValue the default value to return if parameter is not present
     * @return the string value of the parameter, or the default value
     */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Gets an integer parameter from the request, or returns the default value if not present or invalid.
     * @param name the name of the parameter
     * @param defaultValue the default value to return if parameter is not present or invalid
     * @return the integer value of the parameter, or the default value
     */
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Gets a double parameter from the request, or returns the default value if not present or invalid.
     * @param name the name of the parameter
     * @param defaultValue the default value to return if parameter is not present or invalid
     * @return the double value of the parameter, or the default value
     */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null && !value.isEmpty()) ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Gets a boolean parameter from the request, or returns the default value if not present.
     * @param name the name of the parameter
     * @param defaultValue the default value to return if parameter is not present
     * @return the boolean value of the parameter, or the default value
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Gets a Date parameter from the request, or returns null if the value is not present or invalid.
     * @param name the name of the parameter
     * @param pattern the date format pattern to use
     * @return the parsed Date object, or null if the parameter is not valid
     */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value == null || value.isEmpty()) return null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    /**
     * Saves a file uploaded in a multipart request to the specified path.
     * @param file the file to be saved
     * @param path the directory path where the file will be saved
     * @return the File object representing the saved file, or null if the file is empty
     */
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            String filePath = path + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return new File(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }
}