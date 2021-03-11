package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bodyParameters")
public class BodyParameters {

    @CrossOrigin
    @GetMapping
    public String index() {
        return "bodyParameters";
    }

}
