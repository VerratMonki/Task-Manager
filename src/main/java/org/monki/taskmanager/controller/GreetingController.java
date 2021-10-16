package org.monki.taskmanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class GreetingController {

    @GetMapping
    public String greeting(@RequestParam(defaultValue = "username") String username) {
        return "Hello " + username;
    }

    @PutMapping
    public String setName(@RequestBody String username){
        return "Hello " + username;
    }

}
