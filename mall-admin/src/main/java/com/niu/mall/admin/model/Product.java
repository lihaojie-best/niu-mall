package com.niu.mall.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    //Attribute
    @ApiModelProperty(value = "Attribute.产品id")
    private Long productId;
    @ApiModelProperty(value = "Attribute.颜色")
    private String productColour;
    @ApiModelProperty(value = "Attribute.产品类型")
    private String productType;

    @ApiModelProperty(value = "attribute.库存")
    private int productStock;

    //basic
    @ApiModelProperty(value = "basic.商品名")
    private String productName;
    @ApiModelProperty(value = "basic.价格")
    private int productPrice;
    @ApiModelProperty(value = "basic.销量")
    private int productSalesVolume;
    //details
    @ApiModelProperty(value = "details.生产公司")
    private String productCompany;
    @ApiModelProperty(value = "details.生产日期")
    private Date productDate =new Date();
    public Product() {
    }

    public Product(Long productId, String productColour, String productType, int productStock, String productName, int productPrice, int productSalesVolume, String productCompany, Date date) {
        this.productId = productId;
        this.productColour = productColour;
        this.productType = productType;
        this.productStock = productStock;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSalesVolume = productSalesVolume;
        this.productCompany = productCompany;
        this.productDate=date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productColour='" + productColour + '\'' +
                ", productType='" + productType + '\'' +
                ", productStock=" + productStock +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productSalesVolume=" + productSalesVolume +
                ", productCompany='" + productCompany + '\'' +
                ", date=" + productDate +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductColour() {
        return productColour;
    }

    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public Date getDate() {
        return productDate;
    }

    public void setDate(Date date) {
        this.productDate = date;
    }
}
