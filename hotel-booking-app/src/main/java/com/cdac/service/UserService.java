package com.cdac.service;

import com.cdac.dto.AuthRequest;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;

import java.util.List;

public interface UserService {
    
    AuthResponse registerUser(UserReqDTO userReqDTO);
    
    AuthResponse loginUser(AuthRequest authRequest);
    
    UserRespDTO getUserById(Long id);
    
    UserRespDTO getUserByEmail(String email);
    
    List<UserRespDTO> getAllUsers();
    
    UserRespDTO updateUser(Long id, UserReqDTO userReqDTO);
    
    void deleteUser(Long id);
    
    void changePassword(Long userId, String oldPassword, String newPassword);
}