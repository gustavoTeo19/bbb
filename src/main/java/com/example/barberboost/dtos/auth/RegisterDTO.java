package com.example.barberboost.dtos.auth;

import com.example.barberboost.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role, String cellphone) {
}
