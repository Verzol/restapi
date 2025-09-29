package com.example.restapi.blog;
import com.example.restapi.user.CurrentUser;
import com.example.restapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {
  private final BlogService blogService;
  private final UserRepository userRepo;

  // ADMIN xem tất cả
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<BlogResponse> allForAdmin() { return blogService.listAllForAdmin(); }

  // USER xem bài của chính mình
  @GetMapping("/me")
  public List<BlogResponse> myBlogs() {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    return blogService.myBlogs(u.getId());
  }

  // Xem chi tiết 1 bài nếu là owner hoặc admin
  @GetMapping("/{id}")
  public BlogResponse getOne(@PathVariable Long id) {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    return blogService.getByIdWithAccess(id, u);
  }

  @PostMapping
  public BlogResponse create(@Valid @RequestBody BlogCreateUpdateRequest req) {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    return blogService.create(u, req);
  }

  @PutMapping("/{id}")
  public BlogResponse update(@PathVariable Long id, @Valid @RequestBody BlogCreateUpdateRequest req) {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    return blogService.update(id, u, req);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    var u = userRepo.findByUsername(CurrentUser.username()).orElseThrow();
    blogService.delete(id, u);
  }
}
