package com.example.assignmentofsecurity1.Repository;

import com.example.assignmentofsecurity1.Model.Blog;
import com.example.assignmentofsecurity1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
   Blog findBlogById(Integer id);
   List<Blog> findBlogByUser(User user);

   Blog findBlogByTitle(String title);
}
