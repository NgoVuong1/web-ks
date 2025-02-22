package com.project.util;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private HttpSession session;

    public void set(String key, String value) {
        session.setAttribute(key, value);
    }

    public String get(String key) {
        return (String) session.getAttribute(key);
    }

    public void remove(String key) {
        session.removeAttribute(key);
    }
}