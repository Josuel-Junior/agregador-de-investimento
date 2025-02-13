package com.desafio.agregadordeinvestimento.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billingaddress")
public class BillingAdress {

    @Id
    @Column(name = "account_id")
    private UUID uuid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "street")
    private String street;


    @Column(name = "number")
    private Integer number;


    public BillingAdress(UUID uuid, String street, Integer number) {
        this.uuid = uuid;
        this.street = street;
        this.number = number;
    }

    public BillingAdress() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
