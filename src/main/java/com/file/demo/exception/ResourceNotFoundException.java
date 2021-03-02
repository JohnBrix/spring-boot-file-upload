package com.file.demo.exception;

import java.net.URI;
import java.util.List;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(List<Message> messages, URI path) {
        super(messages, path);
    }
}

