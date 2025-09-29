package com.example.restapi.blog;
import java.time.Instant;

public record BlogResponse(
  Long id, String title, String content, Long authorId, String authorUsername,
  Instant createdAt, Instant updatedAt
) {}
