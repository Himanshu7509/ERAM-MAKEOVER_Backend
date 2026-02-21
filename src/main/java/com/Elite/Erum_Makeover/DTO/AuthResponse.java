package com.Elite.Erum_Makeover.DTO;

import com.Elite.Erum_Makeover.Model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private Role role;
}