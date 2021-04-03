package org.example.controller;


import org.decimal4j.util.DoubleRounder;
import org.example.domain.entity.DailyDietaryRation;
import org.example.domain.entity.DiaryUser;
import org.example.service.ProductService;
import org.example.domain.dto.*;
import org.example.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/product")
public class RestApiController {
    private final ProductService productService;

    @Autowired
    public RestApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductTransfer>> productsTabContentJson() throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ProductTransfer> productTransfers = productService.productsTabContentJson(principal);
        if (productTransfers.size() > 0) {
            return ResponseEntity.ok(productTransfers);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserProductJsonTransfer createProduct(@RequestBody UserProductJsonTransfer userProductJsonTransfer) throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.changeProductData(userProductJsonTransfer.getNewProduct(), userProductJsonTransfer.getProductParameter(), principal);
        return userProductJsonTransfer;
    }

    @PostMapping(value = "/create/newProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewProduct(@RequestBody NewProductDto newProductDto) throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.createNewProduct(principal, newProductDto);
    }


    @PostMapping(value = "/create/newProductToTheDailyDiet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTheProductToTheDailyDiet(@RequestBody NewProductTheDailyDiet newProductTheDailyDiet) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.addNewRecordFromDailyDiaryRationTable(newProductTheDailyDiet, principal);
    }


    @GetMapping("/allRation/{path}")
    public ResponseEntity<List<UserDailyDietaryRationTransfer>> allDailyRations(@PathVariable int path) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserDailyDietaryRationTransfer> userDailyDietaryRationTransferList = productService.allDailyRations(principal, path);
        return ResponseEntity.ok(userDailyDietaryRationTransferList);
    }

    @PostMapping(value = "/calorieContent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUserDailyCalorieContent(@Validated @RequestBody DailyDietaryRation dailyDietaryRation, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            productService.addCalorieContentFromUser(principal, dailyDietaryRation.getCalorieContent());
        }
    }

    @GetMapping("/specified/{date}")
    public ResponseEntity<List<UserDailyDietaryRationTransfer>> findProductsOnTheSpecifiedDate(@PathVariable String date) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserDailyDietaryRationTransfer> userDailyDietaryRationTransferList = productService.findProductsOnTheSpecifiedDate(principal, date);
        return ResponseEntity.ok(userDailyDietaryRationTransferList);
    }


    @PostMapping(value = "/productRecipe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveProductRecipe(@Validated @RequestBody ProductRecipeTransfer productRecipeTransfer, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            productService.addNewRecipe(principal, productRecipeTransfer);
        }
    }

    @PostMapping(value = "/saveRecipe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRecipe(@Validated @RequestBody RecipeTransfer recipeTransfer, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            productService.updateRecipe(principal, recipeTransfer);
        }
    }

    @GetMapping(value = "/allRecipe")
    public ResponseEntity<List<AllRecipeListTransfer>> allRecipeSend() throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AllRecipeListTransfer> recipeList = productService.createAllRecipeList(principal);
        if (recipeList.size() > 0) {
            return ResponseEntity.ok(recipeList);
        }
        return ResponseEntity.notFound().build();
    }

}









