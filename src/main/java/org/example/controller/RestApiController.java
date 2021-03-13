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
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
        DiaryUser diaryUser = productService.findUserByLogin(principal.getUsername());
        if (diaryUser != null) {
            Set<Product> productSet = diaryUser.getProductSet();
            List<Product> productList = productSet.stream().sorted(Comparator.comparing(Product::getProductName)).collect(Collectors.toList());
            List<ProductTransfer> productTransfers = new ArrayList<>();
            for (Product product : productList) {
                ProductTransfer productTransfer = new ProductTransfer();
                productTransfer.setProductId(product.getProductId());
                productTransfer.setProductName(product.getProductName());
                productTransfer.setProteins(Double.toString(DoubleRounder.round(product.getProteins(), 2)));
                productTransfer.setFats(Double.toString(DoubleRounder.round(product.getFats(), 2)));
                productTransfer.setCarbohydrates(Double.toString(DoubleRounder.round(product.getCarbohydrates(), 2)));
                productTransfer.setCalorieContent(Double.toString(DoubleRounder.round(product.getCalorieContent(), 2)));
                productTransfers.add(productTransfer);
            }
            System.out.println(productTransfers);
            return ResponseEntity.ok(productTransfers);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserProductJsonTransfer createProduct(@RequestBody UserProductJsonTransfer userProductJsonTransfer) throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.changeProductData(userProductJsonTransfer.getNewProduct(), userProductJsonTransfer.getProductParameter(), principal);
        return userProductJsonTransfer;
    }

    @PostMapping(value = "/create/newProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewProduct(@RequestBody NewProductDto newProductDto) throws SQLException {
        Product product = new Product();
        product.setProductName(newProductDto.getProductName());
        try {
            product.setProteins(Double.parseDouble(newProductDto.getProteins()));
            product.setFats(Double.parseDouble(newProductDto.getFats()));
            product.setCarbohydrates(Double.parseDouble(newProductDto.getCarbohydrates()));
            product.setCalorieContent(Double.parseDouble(newProductDto.getCalorieContent()));
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            DiaryUser diaryUser = productService.findUserByLogin(principal.getUsername());
            if (diaryUser != null) {
                Product product1 = productService.addNewProduct(product);
                product.setProductId(product1.getProductId());
                diaryUser.addProductSet(product);
                productService.updateUser(diaryUser);
            }
        } catch (Exception e) {

        }
    }


    @PostMapping(value = "/create/newProductToTheDailyDiet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTheProductToTheDailyDiet(@RequestBody NewProductTheDailyDiet newProductTheDailyDiet) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.addNewRecordFromDailyDiaryRationTable(newProductTheDailyDiet, principal);
    }


    @GetMapping("/allRation")
    public ResponseEntity<List<UserDailyDietaryRationTransfer>> allDailyRations() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<DailyDietaryRation> dietaryRationList = productService.createUserProductDailyDiaryRationList(principal);
        List<UserDailyDietaryRationTransfer> dailyDietaryRationTransfers = new ArrayList<>();
        for (DailyDietaryRation ration: dietaryRationList) {
            double calorieContent = (ration.getProductWeight() * ration.getCalorieContent() / 100);
            UserDailyDietaryRationTransfer userDailyDietaryRationTransfer = new UserDailyDietaryRationTransfer();
            userDailyDietaryRationTransfer.setId(ration.getId());
            userDailyDietaryRationTransfer.setDateAdded(ration.getDateAdded().toString());
            userDailyDietaryRationTransfer.setProductProteins(Double.toString(DoubleRounder.round(ration.getProductProteins(), 2)));
            userDailyDietaryRationTransfer.setProductFats(Double.toString(DoubleRounder.round(ration.getProductFats(), 2)));
            userDailyDietaryRationTransfer.setProductTitle(ration.getProductTitle());
            userDailyDietaryRationTransfer.setProductCarbohydrates(Double.toString(DoubleRounder.round(ration.getProductCarbohydrates(), 2)));
            userDailyDietaryRationTransfer.setCalorieContent(Double.toString(DoubleRounder.round(calorieContent, 2)));
            userDailyDietaryRationTransfer.setProductWeight(Double.toString(DoubleRounder.round(ration.getProductWeight(), 2)));
            dailyDietaryRationTransfers.add(userDailyDietaryRationTransfer);
        }
        System.out.println(dailyDietaryRationTransfers);
        return ResponseEntity.ok(dailyDietaryRationTransfers);
    }

}









