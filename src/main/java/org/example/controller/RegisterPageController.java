package org.example.controller;

import org.example.domain.entity.DiaryUser;
import org.example.service.ProductService;
import org.example.domain.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/registerPage")
public class RegisterPageController {
    private final ProductService productService;

    public RegisterPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String registerPage() {
        return "registerPage";
    }

    @PostMapping("/save")
    public String saveUserData(UserDto userDto, Model model) throws SQLException {
        if(!userDto.getUserPassword().equals(userDto.getConfirmPassword())){
            model.addAttribute("passwordNoConfirm", 1);
            return "registerPage";
        }
        DiaryUser diaryUser = new DiaryUser();
        diaryUser.setUserLogin(userDto.getUserLogin());
        diaryUser.setUserPassword(userDto.getUserPassword());
        diaryUser.setUserEmail(userDto.getUserEmail());
        productService.addNewUser(diaryUser);
        return "redirect:/";
    }

}
