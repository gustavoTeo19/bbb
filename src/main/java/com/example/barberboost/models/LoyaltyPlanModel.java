package com.example.barberboost.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_LOYALTY_PLAN")
public class LoyaltyPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany(mappedBy = "loyaltyPlan")
    private List<ClientModel> clients;

    @Column(nullable = false)
    private int necessaryAmount;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Boolean isDelete;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ClientModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientModel> clients) {
        this.clients = clients;
    }

    public int getNecessaryAmount() {
        return necessaryAmount;
    }

    public void setNecessaryAmount(int necessaryAmount) {
        this.necessaryAmount = necessaryAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
