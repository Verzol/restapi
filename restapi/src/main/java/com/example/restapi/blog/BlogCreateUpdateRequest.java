package com.example.restapi.blog;
import jakarta.validation.constraints.NotBlank;

public record BlogCreateUpdateRequest(
  @NotBlank String title,
  @NotBlank String content
) {}