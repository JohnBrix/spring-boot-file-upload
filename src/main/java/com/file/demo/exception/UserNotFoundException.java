package com.file.demo.exception;

import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(HttpStatus notFound, List<Message> messages, URI path) {
        super(messages, path);
    }
}
