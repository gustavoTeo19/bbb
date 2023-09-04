package com.example.barberboost.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ClientDto {

    @NotBlank
    private String name;

    @NotBlank
    private String cellPhone;

    @NotBlank
    private String password;

    @NotBlank
    private String email;


}
