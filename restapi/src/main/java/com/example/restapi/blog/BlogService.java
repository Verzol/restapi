package com.example.restapi.blog;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.restapi.user.Role;
import com.example.restapi.user.User;
import com.example.restapi.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class BlogService {
  private final BlogRepository blogRepo;
  private final UserRepository userRepo;

  public BlogResponse toResp(Blog b) {
    return new BlogResponse(b.getId(), b.getTitle(), b.getContent(),
        b.getAuthor().getId(), b.getAuthor().getUsername(),
        b.getCreatedAt(), b.getUpdatedAt());
  }

  public BlogResponse create(User author, BlogCreateUpdateRequest req) {
    var b = Blog.builder()
        .title(req.title())
        .content(req.content())
        .author(author)
        .build();
    blogRepo.save(b);
    return toResp(b);
  }

  public List<BlogResponse> myBlogs(Long userId) {
    return blogRepo.findByAuthorId(userId).stream().map(this::toResp).toList();
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<BlogResponse> listAllForAdmin() {
    return blogRepo.findAll().stream().map(this::toResp).toList();
  }

  public BlogResponse getByIdWithAccess(Long id, User requester) {
    var b = blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    if (!isOwnerOrAdmin(b, requester)) throw new RuntimeException("Forbidden");
    return toResp(b);
  }

  public BlogResponse update(Long id, User requester, BlogCreateUpdateRequest req) {
    var b = blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    if (!isOwnerOrAdmin(b, requester)) throw new RuntimeException("Forbidden");
    b.setTitle(req.title());
    b.setContent(req.content());
    blogRepo.save(b);
    return toResp(b);
  }

  public void delete(Long id, User requester) {
    var b = blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    if (!isOwnerOrAdmin(b, requester)) throw new RuntimeException("Forbidden");
    blogRepo.delete(b);
  }

  private boolean isOwnerOrAdmin(Blog b, User requester) {
    if (requester.getRole() == Role.ADMIN) return true;
    return b.getAuthor().getId().equals(requester.getId());
  }
}
