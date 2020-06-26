package org.acme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final String message;

    public ExampleService(@Value("${example.message}") String message) {
        this.message = message;
    }


    public Example greet(String name){
        return  new Example(message + " " + name);
    }
}
