package com.example.assignmentofsecurity1.Controller;

import com.example.assignmentofsecurity1.Api.ApiResponse;
import com.example.assignmentofsecurity1.Model.User;
import com.example.assignmentofsecurity1.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
   private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user)
    {
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("the user is registered"));
    }

    @GetMapping("/logout")
    public ResponseEntity logout()
    {
        return ResponseEntity.status(200).body("Log out");
    }
}
