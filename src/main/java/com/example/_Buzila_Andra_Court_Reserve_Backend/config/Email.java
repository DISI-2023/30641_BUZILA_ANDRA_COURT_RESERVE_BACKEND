package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import java.util.Map;

public class Email
{
    //Format: to, from, subject, content;
    String to;
    String from;
    String subject;
    String content;

    private Map< String, Object > model;

    //Getters, Setters:
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}

