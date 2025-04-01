package com.universitymlproject.cryptopredictor.dtomappers.userrelated;

import com.universitymlproject.cryptopredictor.dto.userrelated.RoleDTOGet;
import com.universitymlproject.cryptopredictor.model.userrelated.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleDTOMapper {

    public RoleDTOGet toDTO(Role role){
        return new RoleDTOGet(role.getRole());
    }

}
