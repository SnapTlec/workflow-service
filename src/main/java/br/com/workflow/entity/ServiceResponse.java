package br.com.workflow.entity;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> {
    private ResponseStatus status;
    private List<Message> messages = new ArrayList<>();
    private T data;

    public static <T> ServiceResponse<T> success(T data){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.SUCCESS;
        response.messages = null;
        response.data = data;

        return response;
    }

    public static <T> ServiceResponse<T> success(T data, Message message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.SUCCESS;
        response.messages.add(message);
        response.data = data;

        return response;
    }

    public static <T> ServiceResponse<T> success(T data, List<Message> message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.SUCCESS;
        response.messages = message;
        response.data = data;

        return response;
    }

    public static <T> ServiceResponse<T> error(Message message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.ERROR;
        response.messages.add(message);
        response.data = null;
        
        return response;
    }

        public static <T> ServiceResponse<T> error(List<Message> message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.ERROR;
        response.messages.addAll(message);
        response.data = null;
        
        return response;
    }

    public  static <T> ServiceResponse<T> warning(Message message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.WARNING;
        response.messages.add(message);
        response.data = null;
        
        return response;
    }
    public  static <T> ServiceResponse<T> warning(T data, Message message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.WARNING;
        response.messages.add(message);
        response.data = null;
        
        return response;
    }
    public  static <T> ServiceResponse<T> warning(T data, List<Message> message){
        ServiceResponse<T> response = new ServiceResponse<>();
        response.status = ResponseStatus.WARNING;
        response.messages.addAll(message);
        response.data = data;
        
        return response;
    }


    public ResponseStatus getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public List<Message> getMessages(){
        return messages;
    }
}
