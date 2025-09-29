package com.example.restapi.user;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepo;

  @PreAuthorize("hasRole('ADMIN')")
  public List<UserResponse> listAll() {
    return userRepo.findAll().stream()
        .map(u -> new UserResponse(u.getId(), u.getUsername(), (Role) u.getRole()))
        .toList();
  }

  @PreAuthorize("hasRole('ADMIN') or #id == principal.username") // principal.username là username. Ta xử lý get-by-id bên dưới
  public UserResponse getById(Long id, String requesterUsername) {
    var u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    if (!requesterUsername.equals(u.getUsername()) &&
        !userRepo.findByUsername(requesterUsername).orElseThrow().getRole().equals(Role.ADMIN)) {
      throw new RuntimeException("Forbidden");
    }
    return new UserResponse(u.getId(), u.getUsername(), (Role) u.getRole());
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteById(Long id) {
    userRepo.deleteById(id);
  }

  public User getByUsername(String username) {
    return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("Not found"));
  }
}
