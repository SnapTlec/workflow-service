package br.com.workflow.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestService {
    public String getMessage(){
        return "Workflow Service is Running ...";
    }
}
