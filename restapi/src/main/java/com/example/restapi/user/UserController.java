package com.example.restapi.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserRepository userRepo;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserResponse> listAll() { return userService.listAll(); }

  @GetMapping("/{id}")
  public UserResponse getOne(@PathVariable Long id) {
    String uname = CurrentUser.username();
    return userService.getById(id, uname);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void delete(@PathVariable Long id) { userService.deleteById(id); }

  @GetMapping("/me")
  public UserResponse me() {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    return new UserResponse(u.getId(), u.getUsername(), (Role) u.getRole());
  }
}