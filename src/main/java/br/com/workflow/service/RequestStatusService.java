package br.com.workflow.service;

import br.com.workflow.entity.RequestStatus;

public class RequestStatusService {
    public static boolean isValid(String name){
        try{
            RequestStatus.valueOf(name);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}
