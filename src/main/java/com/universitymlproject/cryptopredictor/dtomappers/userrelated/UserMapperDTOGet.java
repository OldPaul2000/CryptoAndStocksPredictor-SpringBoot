package com.universitymlproject.cryptopredictor.dtomappers.userrelated;

import com.universitymlproject.cryptopredictor.dto.userrelated.RoleDTOGet;
import com.universitymlproject.cryptopredictor.dto.userrelated.UserDTOGet;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperDTOGet {

    public UserDTOGet toDTO(User user){
        return new UserDTOGet(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream()
                        .map(role -> {
                            return new RoleDTOGet(role.getRole());
                        }).toList());
    }

}
