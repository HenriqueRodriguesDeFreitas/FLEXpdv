package com.paulo.flexpdv.model;

import com.paulo.flexpdv.model.enums.UnitOfMeasure;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String barcode;

    @Column(name = "cost_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal salePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal stock = BigDecimal.ZERO;

    @Column()
    private boolean active = true;

    @Column(name = "stock_control")
    private boolean stockControl = true;

    @Column(name = "unit_of_measure", length = 2, nullable = false)
    private String unitOfMeasure;

    public Product(){}

    public Product(String name,
                   String barcode,
                   BigDecimal costPrice,
                   BigDecimal salePrice, BigDecimal stock, boolean stockControl) {
        this.name = name;
        this.barcode = barcode;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.stockControl = stockControl;
    }

    public Product(UUID id, String name, String barcode, BigDecimal costPrice, BigDecimal salePrice, BigDecimal stock, boolean active, boolean stockControl) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.active = active;
        this.stockControl = stockControl;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStockControl() {
        return stockControl;
    }

    public void setStockControl(boolean stockControl) {
        this.stockControl = stockControl;
    }
}
