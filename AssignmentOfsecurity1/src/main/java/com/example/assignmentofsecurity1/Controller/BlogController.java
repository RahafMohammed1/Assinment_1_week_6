package com.example.assignmentofsecurity1.Controller;

import com.example.assignmentofsecurity1.Api.ApiResponse;
import com.example.assignmentofsecurity1.Model.Blog;
import com.example.assignmentofsecurity1.Model.User;
import com.example.assignmentofsecurity1.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get-all")
    public ResponseEntity getAllBlog()
    {
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }
    @GetMapping("/get")
    public ResponseEntity getAllMyBlog(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(blogService.getAllMyBlog(user.getId()));
    }
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user,@PathVariable String title)
    {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }
    @GetMapping("/get-by-id/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user,@PathVariable Integer blog_id)
    {
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blog_id));
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog)
    {
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("the blog is added"));
    }
    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog, @PathVariable Integer blog_id)
    {
        blogService.updateBlog(user.getId(),blog,blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("the blog is updated"));
    }
    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer blog_id)
    {
        blogService.deleteBlog(user.getId(),blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("the blog is deleted"));
    }
}
