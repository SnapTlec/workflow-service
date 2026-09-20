package br.com.workflow.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.workflow.entity.Message;

public class RequestResponseDTO<T> {
    public List<Message> messages = new ArrayList<>();
    public T data;

    
}
