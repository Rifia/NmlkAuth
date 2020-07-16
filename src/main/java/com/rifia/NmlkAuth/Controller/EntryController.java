package com.rifia.NmlkAuth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class EntryController {
    @GetMapping
    public String helloMessage(){
        return "hello";
    }

}
