package org.example.domain.dto;

public class ProductTransfer {

    private int productId;
    private String productName;
    private String proteins;
    private String fats;
    private String carbohydrates;
    private String calorieContent;

    public ProductTransfer(){

    }

    public ProductTransfer(int productId, String productName, String proteins, String fats, String carbohydrates, String calorieContent) {
        this.productId = productId;
        this.productName = productName;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calorieContent = calorieContent;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getCalorieContent() {
        return calorieContent;
    }

    public void setCalorieContent(String calorieContent) {
        this.calorieContent = calorieContent;
    }

    @Override
    public String toString() {
        return "ProductTransfer{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", proteins='" + proteins + '\'' +
                ", fats='" + fats + '\'' +
                ", carbohydrates='" + carbohydrates + '\'' +
                ", calorieContent='" + calorieContent + '\'' +
                '}';
    }
}
