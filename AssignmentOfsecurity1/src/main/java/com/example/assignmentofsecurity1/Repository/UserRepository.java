package com.example.assignmentofsecurity1.Repository;

        import com.example.assignmentofsecurity1.Model.User;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
}