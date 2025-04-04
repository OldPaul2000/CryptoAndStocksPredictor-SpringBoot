package com.universitymlproject.cryptopredictor.dto.userrelated;

import java.util.List;

public record UserDTOGet(
        long userId,
        String username,
        List<RoleDTOGet> roles
) {
}
