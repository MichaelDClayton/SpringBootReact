package com.springbootreact.exception;

public class StudentAlreadyExistException extends RuntimeException {

    public StudentAlreadyExistException(String message)  {
        super(message);
    }
}
