package com.cdac.service;

import com.cdac.custom_exceptions.InvalidCredentialsException;
import com.cdac.custom_exceptions.UserAlreadyExistsException;
import com.cdac.custom_exceptions.UserNotFoundException;
import com.cdac.dto.AuthRequest;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.UserReqDTO;
import com.cdac.dto.UserRespDTO;
import com.cdac.entities.UserEntity;
import com.cdac.repository.UserRepository;
import com.cdac.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public AuthResponse registerUser(UserReqDTO userReqDTO) {
        if (userRepository.existsByEmail(userReqDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + userReqDTO.getEmail() + " already exists");
        }
        
        UserEntity user = new UserEntity();
        user.setName(userReqDTO.getName());
        user.setEmail(userReqDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userReqDTO.getPassword()));
        user.setContactNo(userReqDTO.getContactNo());
        user.setRole(userReqDTO.getRole());
        
        UserEntity savedUser = userRepository.save(user);
        
        String token = jwtUtils.generateToken(savedUser);
        
        return new AuthResponse(token, "Bearer", savedUser.getEmail(), 
                              savedUser.getRole().name(), savedUser.getName());
    }
    
    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserEntity user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            
            String token = jwtUtils.generateToken(userDetails);
            
            return new AuthResponse(token, "Bearer", user.getEmail(), 
                                  user.getRole().name(), user.getName());
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }
    
    @Override
    public UserRespDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return convertToUserRespDTO(user);
    }
    
    @Override
    public UserRespDTO getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return convertToUserRespDTO(user);
    }
    
    @Override
    public List<UserRespDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToUserRespDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserRespDTO updateUser(Long id, UserReqDTO userReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        
        user.setName(userReqDTO.getName());
        user.setContactNo(userReqDTO.getContactNo());
        user.setRole(userReqDTO.getRole());
        
        UserEntity updatedUser = userRepository.save(user);
        return convertToUserRespDTO(updatedUser);
    }
    
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidCredentialsException("Old password is incorrect");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    private UserRespDTO convertToUserRespDTO(UserEntity user) {
        UserRespDTO dto = new UserRespDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setContactNo(user.getContactNo());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}