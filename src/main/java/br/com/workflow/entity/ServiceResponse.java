package br.com.workflow.entity;

import java.util.List;

public class ServiceResponse<T> {
    private ResponseStatus status;
    private List<String> messages;
    private T data;

    public static <T> ServiceResponse<T> success(T data){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.SUCCESS;
        response.messages = null;
        response.data = data;

        return response;
    }

    public static <T> ServiceResponse<T> error(String message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.ERROR;
        response.messages.add(message);
        response.data = null;
        
        return response;
    }

    public ResponseStatus getStatus(){
        return status;
    }
}
