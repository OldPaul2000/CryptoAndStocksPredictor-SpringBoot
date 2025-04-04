package com.universitymlproject.cryptopredictor.dtomappers.userrelated;

import com.universitymlproject.cryptopredictor.dto.userrelated.LoginResponseDTO;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import org.springframework.stereotype.Service;

@Service
public class LoginResponseMapper {

    private RoleDTOMapper roleMapper;

    public LoginResponseMapper(RoleDTOMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public LoginResponseDTO toDTO(User user){
        return new LoginResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getJwt().getJwt(),
                user.getRoles().stream().map(role -> {
                    return roleMapper.toDTO(role);
                }).toList()
        );
    }

}
