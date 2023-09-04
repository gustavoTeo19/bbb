package com.example.barberboost.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_APPOINTMENT")
public class AppointmentModel implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel cliente;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberModel barber;

    @ManyToMany
    @JoinTable(name = "appointment_service",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceModel> services;


    @Column(nullable = false)
    private LocalDateTime appoitmentDate;

    public LocalDateTime getAppoitmentDate() {
        return appoitmentDate;
    }

    public void setAppoitmentDate(LocalDateTime appoitmentDate) {
        this.appoitmentDate = appoitmentDate;
    }

    //    @Temporal(TemporalType.TIME)
//    private
    @Column(nullable = false)
    private Boolean isDelete;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(name = "total")
    private Double total;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientModel getCliente() {
        return cliente;
    }

    public void setCliente(ClientModel cliente) {
        this.cliente = cliente;
    }

    public BarberModel getBarber() {
        return barber;
    }

    public void setBarber(BarberModel barber) {
        this.barber = barber;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> service) {
        this.services = services;
    }
}
