package com.cdac.dto;

import com.cdac.entities.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRespDTO {
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private UserRole role;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}