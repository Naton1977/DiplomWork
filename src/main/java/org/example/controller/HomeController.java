package org.example.controller;

import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String goHome() {
        return "home";
    }


    @GetMapping("/login")
    public String goLogin() {
        return "loginPage";
    }


    @GetMapping("/diaryPage")
    public String goDiaryPage() {
        return "diaryPage";
    }


    @GetMapping("/article/{id}")
    public String article(@PathVariable int id) {
        if (id == 1) {
            return "articlePage1";
        }
        if (id == 2){
            return "articlePage2";
        }
            return "home";
    }


}
