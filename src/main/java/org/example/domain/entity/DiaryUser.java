package org.example.domain.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class DiaryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_login", unique = true)
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_role")
    private String role;

    @ManyToMany
    @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "user_daily_dietary_ration",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recording_id"))
    private Set<DailyDietaryRation> dailyDietaryRations = new HashSet<>();


    public DiaryUser() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public void addProductSet(Product product) {
        productSet.add(product);
    }

    public void addDailyDietaryRations(DailyDietaryRation dailyDietaryRation) {
        dailyDietaryRations.add(dailyDietaryRation);
    }

    public Set<DailyDietaryRation> getDailyDietaryRations() {
        return dailyDietaryRations;
    }

    public void setDailyDietaryRations(Set<DailyDietaryRation> dailyDietaryRations) {
        this.dailyDietaryRations = dailyDietaryRations;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
