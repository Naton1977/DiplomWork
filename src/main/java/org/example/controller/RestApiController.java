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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserProductJsonTransfer createProduct(@RequestBody UserProductJsonTransfer userProductJsonTransfer) throws SQLException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.changeProductData(userProductJsonTransfer.getNewProduct(), userProductJsonTransfer.getProductParameter(), principal);
        return userProductJsonTransfer;
    }

    @PostMapping(value = "/create/newProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewProduct(@RequestBody NewProductDto newProductDto) throws SQLException {
        boolean isPresent = false;
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
                Set<Product> productSet = diaryUser.getProductSet();
                for (Product prod : productSet) {
                    if (prod.getProductName().equals(product.getProductName())) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    Product product1 = productService.addNewProduct(product);
                    product.setProductId(product1.getProductId());
                    diaryUser.addProductSet(product);
                    productService.updateUser(diaryUser);
                }
            }
        } catch (Exception e) {

        }
    }


    @PostMapping(value = "/create/newProductToTheDailyDiet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTheProductToTheDailyDiet(@RequestBody NewProductTheDailyDiet newProductTheDailyDiet) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.addNewRecordFromDailyDiaryRationTable(newProductTheDailyDiet, principal);
    }


    @GetMapping("/allRation/{path}")
    public ResponseEntity<List<UserDailyDietaryRationTransfer>> allDailyRations(@PathVariable int path) {
        List<String> userDateList = new ArrayList<>();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<DailyDietaryRation> dietaryRationList = productService.createUserProductDailyDiaryRationList(principal);
        Date now = new Date();
        List<UserDailyDietaryRationTransfer> dailyDietaryRationTransfers = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = formatDate.format(now);
        for (int i = 0; i < dietaryRationList.size() - 1; i++) {
            String userDate = formatDate.format(dietaryRationList.get(i).getDateAdded());
            String userDateFuture = formatDate.format(dietaryRationList.get(i + 1).getDateAdded());
            if (i == 0) {
                userDateList.add(userDate);
            }

            if (!userDate.equals(userDateFuture)) {
                userDateList.add(userDateFuture);
            }
        }
        if (path < 0) {
            path = 0;
        }
        if (userDateList.size() > 0) {
            if (dateNow.equals(userDateList.get(0))) {
                path += 1;
            }
        }

        if (path > (userDateList.size() - 1)) {
            path = (userDateList.size() - 1);
        }

        for (int i = 0; i < dietaryRationList.size(); i++) {
            double calorieContent = (dietaryRationList.get(i).getProductWeight() * dietaryRationList.get(i).getCalorieContent() / 100);
            UserDailyDietaryRationTransfer userDailyDietaryRationTransfer = new UserDailyDietaryRationTransfer();
            String userDate = formatDate.format(dietaryRationList.get(i).getDateAdded());
            if (userDate.equals(userDateList.get(path))) {
                if (path != (userDateList.size() - 1)) {
                    break;
                }
            }
            userDailyDietaryRationTransfer.setId(dietaryRationList.get(i).getId());
            userDailyDietaryRationTransfer.setDateAdded(dietaryRationList.get(i).getDateAdded().toString());
            userDailyDietaryRationTransfer.setProductProteins(Double.toString(DoubleRounder.round(dietaryRationList.get(i).getProductProteins(), 2)));
            userDailyDietaryRationTransfer.setProductFats(Double.toString(DoubleRounder.round(dietaryRationList.get(i).getProductFats(), 2)));
            userDailyDietaryRationTransfer.setProductTitle(dietaryRationList.get(i).getProductTitle());
            userDailyDietaryRationTransfer.setProductCarbohydrates(Double.toString(DoubleRounder.round(dietaryRationList.get(i).getProductCarbohydrates(), 2)));
            userDailyDietaryRationTransfer.setCalorieContent(Double.toString(DoubleRounder.round(calorieContent, 2)));
            userDailyDietaryRationTransfer.setProductWeight(Double.toString(DoubleRounder.round(dietaryRationList.get(i).getProductWeight(), 2)));
            dailyDietaryRationTransfers.add(userDailyDietaryRationTransfer);
        }
        return ResponseEntity.ok(dailyDietaryRationTransfers);
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
        List<DailyDietaryRation> dietaryRationList = productService.createUserProductDailyDiaryRationList(principal);
        List<UserDailyDietaryRationTransfer> dailyDietaryRationTransfers = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-M-yyyy");
        for (DailyDietaryRation ration : dietaryRationList) {
            String userDate = formatDate.format(ration.getDateAdded());
            if (userDate.equals(date)) {
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
        }
        return ResponseEntity.ok(dailyDietaryRationTransfers);
    }
}









