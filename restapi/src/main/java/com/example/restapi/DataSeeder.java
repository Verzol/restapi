package com.example.restapi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.restapi.user.Role;
import com.example.restapi.user.User;
import com.example.restapi.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component @RequiredArgsConstructor
public class DataSeeder {
  private final UserRepository userRepo;
  private final PasswordEncoder encoder;

  @Bean
  CommandLineRunner init() {
    return args -> {
      if (!userRepo.existsByUsername("admin")) {
        var admin = User.builder()
            .username("admin")
            .password(encoder.encode("admin123"))
            .role(Role.ADMIN)
            .build();
        userRepo.save(admin);
      }
      if (!userRepo.existsByUsername("alice")) {
        userRepo.save(User.builder()
            .username("alice").password(encoder.encode("alice123")).role(Role.USER).build());
      }
    };
  }
}
