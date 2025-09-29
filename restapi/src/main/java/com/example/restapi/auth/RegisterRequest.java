package com.example.restapi.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
  @NotBlank String username,
  @NotBlank String password,
  String role
) {}
