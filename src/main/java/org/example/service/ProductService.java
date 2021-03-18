package org.example.service;


import org.example.domain.dto.NewProductTheDailyDiet;
import org.example.domain.entity.BodyParameters;
import org.example.domain.entity.DailyDietaryRation;
import org.example.domain.entity.DiaryUser;
import org.example.domain.entity.Product;
import org.example.repository.BodyParametersRepository;
import org.example.repository.DailyDietaryRationRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DailyDietaryRationRepository dietaryRationRepository;
    private final BodyParametersRepository bodyParametersRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, DailyDietaryRationRepository dietaryRationRepository, BodyParametersRepository bodyParametersRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.dietaryRationRepository = dietaryRationRepository;
        this.bodyParametersRepository = bodyParametersRepository;
    }

    @Transactional
    public void changeProductData(String newProduct, String productParameter, UserDetails principal) throws SQLException {
        DiaryUser diaryUser = userRepository.userByLogin(principal.getUsername());
        if (diaryUser != null) {

            String columnNumber = productParameter.substring(6, 7);
            int columnNumberInt = 0;
            try {
                columnNumberInt = Integer.parseInt(columnNumber);
            } catch (Exception e) {

            }
            int tdIndex = productParameter.indexOf("td");
            String productId = productParameter.substring(16, tdIndex);
            int productIdInt = 0;
            try {
                productIdInt = Integer.parseInt(productId);
            } catch (Exception e) {

            }

            Set<Product> products = diaryUser.getProductSet();
            for (Product product : products) {
                if (product.getProductId() == productIdInt) {
                    if (columnNumberInt < 6 && productIdInt > 0) {
                        Product prod = productRepository.getOne(productIdInt);
                        Set<DiaryUser> diaryUserSet = prod.getUserSet();
                        if (columnNumberInt == 1) {
                            prod.setProductName(newProduct);
                            productRepository.saveAndFlush(prod);
                        }
                        if (columnNumberInt == 2) {
                            try {
                                prod.setProteins(Double.parseDouble(newProduct));
                                productRepository.saveAndFlush(prod);
                            } catch (Exception e) {

                            }
                        }
                        if (columnNumberInt == 3) {
                            try {
                                prod.setFats(Double.parseDouble(newProduct));
                                productRepository.saveAndFlush(prod);
                            } catch (Exception e) {

                            }
                        }
                        if (columnNumberInt == 4) {
                            try {
                                prod.setCarbohydrates(Double.parseDouble(newProduct));
                                productRepository.saveAndFlush(prod);
                            } catch (Exception e) {

                            }
                        }
                        if (columnNumberInt == 5) {
                            try {
                                prod.setCalorieContent(Double.parseDouble(newProduct));
                                productRepository.saveAndFlush(prod);
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }
        }
    }

    public List<Product> findAllProduct() throws SQLException {
        return productRepository.findAll();
    }

    @Transactional
    public void addNewUser(DiaryUser diaryUser) throws SQLException {
        userRepository.save(diaryUser);
    }

    @Transactional
    public Product addNewProduct(Product product) throws SQLException {
        return productRepository.save(product);
    }

    public DiaryUser findUserById(Integer id) throws SQLException {
        return userRepository.getOne(id);
    }

    @Transactional
    public void updateUser(DiaryUser diaryUser) throws SQLException {
        userRepository.saveAndFlush(diaryUser);
    }

    public void deleteProductById(Integer id) throws SQLException {
        productRepository.deleteById(id);
    }

    @Transactional
    public DiaryUser findUserByLogin(String login) {
        DiaryUser diaryUser = userRepository.userByLogin(login);
        if (diaryUser != null) {
            Set<Product> productSet = diaryUser.getProductSet();
            for (Product product : productSet) {
                product.getProductName();
                break;
            }
            return diaryUser;
        }
        return null;
    }

    @Transactional
    public void addNewRecordFromDailyDiaryRationTable(NewProductTheDailyDiet newProductTheDailyDiet, UserDetails principal) {
        DiaryUser diaryUser = userRepository.userByLogin(principal.getUsername());
        if (diaryUser != null) {
            Set<Product> products = diaryUser.getProductSet();
            for (Product product : products) {
                if (product.getProductId() == newProductTheDailyDiet.getProductId()) {
                    Product product1 = productRepository.getOne(product.getProductId());
                    DailyDietaryRation dietaryRation = new DailyDietaryRation();
                    dietaryRation.setDateAdded(newProductTheDailyDiet.getTime());
                    dietaryRation.setProductTitle(product1.getProductName());
                    dietaryRation.setProductProteins(product1.getProteins());
                    dietaryRation.setProductFats(product1.getFats());
                    dietaryRation.setProductCarbohydrates(product1.getCarbohydrates());
                    dietaryRation.setCalorieContent(product1.getCalorieContent());
                    dietaryRation.setProductWeight(newProductTheDailyDiet.getWeight());
                    dietaryRation.addDailyDiaryUserSet(diaryUser);
                    dietaryRationRepository.save(dietaryRation);
                }
            }
        }
    }

    @Transactional
    public List<DailyDietaryRation> createUserProductDailyDiaryRationList(UserDetails principal) {
        DiaryUser diaryUser = userRepository.userByLogin(principal.getUsername());
        Set<DailyDietaryRation> dailyDietaryRationSet = diaryUser.getDailyDietaryRations();
        return dailyDietaryRationSet.stream().sorted(Comparator.comparing(DailyDietaryRation::getDateAdded)).collect(Collectors.toList());
    }

    @Transactional
    public void saveBodyParameters(BodyParameters bodyParameters, UserDetails principal) {
        DiaryUser diaryUser = userRepository.userByLogin(principal.getUsername());
        bodyParameters.addDiaryUserSet(diaryUser);
        bodyParametersRepository.save(bodyParameters);
    }

    @Transactional
    public void addCalorieContentFromUser(UserDetails principal, Double calorieContent) {
        DiaryUser diaryUser = userRepository.userByLogin(principal.getUsername());
        diaryUser.setCalorieContent(calorieContent);
        userRepository.saveAndFlush(diaryUser);
    }


}
