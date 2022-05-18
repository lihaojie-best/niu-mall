package com.niu.mall.admin.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class Product implements Serializable {

    @ApiModelProperty(value = "产品id")
    private Long productId;
    @ApiModelProperty(value = "商品名")
    private String productName;
    @ApiModelProperty(value = "颜色")
    private String productColour;
    @ApiModelProperty(value = "生产公司")
    private String productCompany;
    @ApiModelProperty(value = "产品类型")
    private String productType;
    @ApiModelProperty(value = "价格")
    private int productPrice;
    @ApiModelProperty(value = "销量")
    private int productSalesVolume;
    @ApiModelProperty(value = "库存")
    private int productStock;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productColour='" + productColour + '\'' +
                ", productCompany='" + productCompany + '\'' +
                ", productType='" + productType + '\'' +
                ", productPrice=" + productPrice +
                ", productSalesVolume=" + productSalesVolume +
                ", productStock=" + productStock +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColour() {
        return productColour;
    }

    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductSalesVolume() {
        return productSalesVolume;
    }

    public void setProductSalesVolume(int productSalesVolume) {
        this.productSalesVolume = productSalesVolume;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public Product(Long productId, String productName, String productColour, String productCompany, String productType, int productPrice, int productSalesVolume, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productColour = productColour;
        this.productCompany = productCompany;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productSalesVolume = productSalesVolume;
        this.productStock = productStock;
    }
}
