package com.example.restapi.user;

import com.example.restapi.blog.Blog;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "users")
public class User {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Blog> blogs = new ArrayList<>();
}
