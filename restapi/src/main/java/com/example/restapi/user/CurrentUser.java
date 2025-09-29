package com.example.restapi.user;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CurrentUser {
  private CurrentUser() {}
  public static String username() {
    Authentication a = SecurityContextHolder.getContext().getAuthentication();
    return a == null ? null : a.getName();
  }
}
