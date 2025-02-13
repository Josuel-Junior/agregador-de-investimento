package com.desafio.agregadordeinvestimento.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_stock")
public class Stock {

    @Id
    @Column(name ="stockId")
    private String stockId;

    @Column(name ="scription")
    private String description;

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }

    public Stock() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
