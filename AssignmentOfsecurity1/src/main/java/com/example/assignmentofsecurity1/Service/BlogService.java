package com.example.assignmentofsecurity1.Service;

import com.example.assignmentofsecurity1.Api.ApiException;
import com.example.assignmentofsecurity1.Model.Blog;
import com.example.assignmentofsecurity1.Model.User;
import com.example.assignmentofsecurity1.Repository.BlogRepository;
import com.example.assignmentofsecurity1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.IntegralTimestampaddFunction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public List<Blog> getAllMyBlog(Integer user_id) {
        User user = userRepository.findUserById(user_id);
        return blogRepository.findBlogByUser(user);

    }

    public Blog getBlogByTitle(Integer user_id,String title) {
       Blog blog=blogRepository.findBlogByTitle(title);
       if(blog==null)
       {
           throw new ApiException("the blog not found");
       }
        User user = userRepository.findUserById(user_id);
        if (blog.getUser().getId() != user_id) {
            throw new ApiException("this blog not belong to you");
        }
        return blog;
    }
    public Blog getBlogById(Integer user_id,Integer blog_id) {
        Blog blog=blogRepository.findBlogById(blog_id);
        if(blog==null)
        {
            throw new ApiException("the blog not found");
        }
        User user = userRepository.findUserById(user_id);
        if (blog.getUser().getId() != user_id) {
            throw new ApiException("this blog not belong to you");
        }
        return blog;
    }

    public void addBlog(Integer user_id, Blog blog) {
        User user = userRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id, Blog blog, Integer blog_id) {
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("the blog not found");
        }
        User user = userRepository.findUserById(user_id);
        if (blog1.getUser().getId() != user_id) {
            throw new ApiException("this blog not belong to you");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer user_id, Integer blog_id) {
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("the blog not found");
        }
        User user = userRepository.findUserById(user_id);
        if (blog1.getUser().getId() != user_id) {
            throw new ApiException("this blog not belong to you");
        }
        blogRepository.delete(blog1);
    }

}
