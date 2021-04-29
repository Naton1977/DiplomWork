package org.example.controller;

import org.example.domain.entity.DiaryUser;
import org.example.service.ProductService;
import org.example.domain.dto.UserDto;
import org.example.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/registerPage")
public class RegisterPageController {

    private final ProductService productService;


    public RegisterPageController(ProductService productService) {
        this.productService = productService;
    }


    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(new PasswordValidator());
    }


    @GetMapping
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registerPage";
    }

    @PostMapping("/save")
    public String saveUserData(@Validated @ModelAttribute UserDto userDto, BindingResult bindingResult) throws SQLException {
        System.out.println(userDto);
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }
        productService.saveNewUser(userDto);

        return "redirect:/diaryPage";
    }

}
