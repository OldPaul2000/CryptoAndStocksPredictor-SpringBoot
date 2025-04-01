package com.universitymlproject.cryptopredictor.dtomappers.userrelated;

import com.universitymlproject.cryptopredictor.dto.userrelated.LoginResponseDTO;
import com.universitymlproject.cryptopredictor.dto.userrelated.RoleDTOGet;
import com.universitymlproject.cryptopredictor.model.userrelated.Role;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginResponseDTOMapper {

    private RoleDTOMapper roleMapper;

    public LoginResponseDTOMapper(RoleDTOMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public LoginResponseDTO toDTO(User user, String jwt){
        return new LoginResponseDTO(
          user.getId(),
          user.getUsername(),
          user.getJwt().getJwt(),
          mapRoles(user.getRoles())
        );
    }

    private List<RoleDTOGet> mapRoles(List<Role> roles){
        return roles.stream().map(role -> {
            return roleMapper.toDTO(role);
        }).toList();
    }

}
