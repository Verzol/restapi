package com.example.restapi.auth;
import com.example.restapi.security.JwtService;
import com.example.restapi.user.Role;
import com.example.restapi.user.User;
import com.example.restapi.user.UserRepository;
import com.example.restapi.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authManager;
  private final JwtService jwt;
  private final UserRepository userRepo;
  private final PasswordEncoder encoder;

  @PostMapping("/register")
  public UserResponse register(@Valid @RequestBody RegisterRequest req) {
    if (userRepo.existsByUsername(req.username())) {
      throw new RuntimeException("Username already exists");
    }
    Role role = (req.role() == null) ? Role.USER : Role.valueOf(req.role().toUpperCase());
    var u = User.builder()
        .username(req.username())
        .password(encoder.encode(req.password()))
        .role(role)
        .build();
    userRepo.save(u);
    return new UserResponse(u.getId(), u.getUsername(), (Role) u.getRole());
  }

  @PostMapping("/login")
  public JwtResponse login(@Valid @RequestBody LoginRequest req) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
    var u = userRepo.findByUsername(req.username()).orElseThrow();
    return new JwtResponse(jwt.generateToken(u.getUsername(), u.getRole().name()));
  }
}