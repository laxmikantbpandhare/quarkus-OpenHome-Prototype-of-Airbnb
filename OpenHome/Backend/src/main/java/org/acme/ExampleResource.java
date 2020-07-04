package org.acme;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class ExampleResource {


    private final ExampleService exampleService;

    public ExampleResource(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
//    @Produces(MediaType.TEXT_PLAIN)
    public Example hello(@RequestParam(defaultValue = "world") String name) {
        return exampleService.greet(name);
    }
}