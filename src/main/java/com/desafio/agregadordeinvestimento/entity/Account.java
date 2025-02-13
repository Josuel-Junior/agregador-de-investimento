package com.desafio.agregadordeinvestimento.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @Column(name = "account")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column(name = "description")
    private String description;

    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }

    public Account() {
    }


    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
