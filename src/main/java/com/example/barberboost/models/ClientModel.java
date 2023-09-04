package com.example.barberboost.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
public class ClientModel implements Serializable {
    private static final long serialVersionUID  = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<AppointmentModel> appointments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loyalty_plan_id")
    private LoyaltyPlanModel loyaltyPlan;

    @Column(nullable = false)
    private int loyaltyAmount;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cellPhone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private Boolean isDelete;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public UUID getId() {
        return id;
    }

    public int getLoyaltyAmount() {
        return loyaltyAmount;
    }

    public void setLoyaltyAmount(int loyaltyAmount) {
        this.loyaltyAmount = loyaltyAmount;
    }

    public LoyaltyPlanModel getLoyaltyPlan() {
        return loyaltyPlan;
    }

    public void setLoyaltyPlan(LoyaltyPlanModel loyaltyPlan) {
        this.loyaltyPlan = loyaltyPlan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }


    public List<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModel> appointments) {
        this.appointments = appointments;
    }
}
