package com.example.restapi.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
   List<Blog> findByAuthorId(Long authorId);
}
