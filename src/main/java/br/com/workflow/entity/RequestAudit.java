package br.com.workflow.entity;

import java.util.Date;

public class RequestAudit {
    public int id;
    public Date eventDate;
    public User user;
    public EventType eventType;
    public Request request;
    public String fieldName;
    public String oldValue;
    public String newValue;
}
