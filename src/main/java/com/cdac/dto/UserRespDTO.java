package com.cdac.dto;

import com.cdac.entities.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRespDTO extends BaseDTO {
    private String name;
    private String email;
    private String contactNo;
    private UserRole role;
    private boolean enabled;
}