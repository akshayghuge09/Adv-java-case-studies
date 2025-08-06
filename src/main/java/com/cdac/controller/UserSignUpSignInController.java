package com.cdac.controller;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.AuthRequest;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserSignUpSignInController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> registerUser(@Valid @RequestBody UserReqDTO userReqDTO) {
        AuthResponse authResponse = userService.registerUser(userReqDTO);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", authResponse));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = userService.loginUser(authRequest);
        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    }
    
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'HOTEL_OWNER', 'ADMIN')")
    public ResponseEntity<ApiResponse<UserRespDTO>> getCurrentUserProfile(@RequestParam String email) {
        UserRespDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(ApiResponse.success("User profile retrieved successfully", user));
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserRespDTO>>> getAllUsers() {
        List<UserRespDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }
    
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserRespDTO>> getUserById(@PathVariable Long id) {
        UserRespDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }
    
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserRespDTO>> updateUser(@PathVariable Long id, 
                                                             @Valid @RequestBody UserReqDTO userReqDTO) {
        UserRespDTO updatedUser = userService.updateUser(id, userReqDTO);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedUser));
    }
    
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }
    
    @PostMapping("/change-password")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'HOTEL_OWNER', 'ADMIN')")
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestParam Long userId,
                                                            @RequestParam String oldPassword,
                                                            @RequestParam String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully"));
    }
}