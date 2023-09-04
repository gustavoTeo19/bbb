package com.example.barberboost.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppointmentDto {

//    private UUID clienteId;

    private String clientEmail;
    private UUID barberId;
    private LocalDateTime appoitmentDate;

    private List<UUID> services;

//    @Override
//    public String toString() {
//        return "AppointmentDto{" +
//                "clienteId=" + clienteId +
//                ", barberId=" + barberId +
//                ", appoitmentDate=" + appoitmentDate +
//                ", services=" + services +
//                '}';
//    }
}
