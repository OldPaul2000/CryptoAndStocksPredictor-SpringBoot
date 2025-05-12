package com.universitymlproject.cryptopredictor.dto.userrelated;

import java.util.List;

public record LoginResponseDTO(
        Long userId,
        String username,
        String jwt,
        List<RoleDTOGet> roles
) {


}
